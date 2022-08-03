import { HttpClient, HttpResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { Category } from 'src/app/model/category';
import { Training } from 'src/app/model/training';
import { ApiService } from 'src/app/services/api.service';
import { AuthentificationService } from 'src/app/services/authentification.service';
import { environment } from 'src/environments/environment';
@Component({
  selector: 'app-training',
  templateUrl: './training.component.html',
  styleUrls: ['./training.component.css'],
})
export class TrainingComponent implements OnInit {
  error = null;
  myForm: FormGroup;
  training: Training | undefined;
  displayForm: boolean = false;
  status: boolean = false;
  isAdmin: boolean = false;
  title: string = 'Ajouter une formation';
  categories: Category[] | undefined;
  category: Category = new Category(0, '');
  url: string = environment.host + '/trainingImage/';
  file: File | undefined;

  constructor(
    private authService: AuthentificationService,
    private router: Router,
    private api: ApiService,
    private formBuilder: FormBuilder,
    private route: ActivatedRoute
  ) {
    this.myForm = this.formBuilder.group({
      id: [0, [Validators.required]],
      name: ['', [Validators.required]],
      description: ['', [Validators.required]],
      price: ['', [Validators.required]],
      category: [null, Validators.required],
      image:['']
    });
  }
  ngOnInit(): void {
    if (this.authService.isAdmin) {
      this.getCategories();
      let id = this.route.snapshot.params['id'];
      if (id > 0) {
        this.status = true;
        this.title = 'Modifier cette formation';
        this.api.getOneTraining(id).subscribe({
          next: (data) => {
            this.training = data;
            this.myForm.setValue({
              id: this.training.id,
              name: this.training.name,
              description: this.training.description,
              price: this.training.price,
              category: this.training.category.id,
              image: this.training.image
            });
          },
          error: (err) => (this.error = err),
        });
      }
    } else {
      this.router.navigateByUrl('/login');
    }
  }

  onSaveTraining(myForm: FormGroup) {
    if (myForm.valid) {
      const trainingData = new FormData();
      trainingData.append('image', this.file as Blob);
      this.training = new Training(
        myForm.value.id,
        myForm.value.name,
        myForm.value.description,
        myForm.value.price,
        1,
        this.training != null ? myForm.value.image : '', 
        new Category(myForm.value.category, '')
      );
      trainingData.append('training', JSON.stringify(this.training));
      if (this.status) {
        this.updateTraining(this.training.id, trainingData);
      } else {
        this.addTraining(trainingData);
      }
      console.log(trainingData)
    }
  }

  addTraining(trainingData:FormData) {
    this.api.add(trainingData).subscribe({
      next: (data) => console.log("ok"),
      error: (err) => (this.error = err.message),
      complete: () => this.router.navigateByUrl('admin'),
    });
  }

  updateTraining(trainingId:number, trainingData:FormData) {
    this.api.update(trainingId, trainingData).subscribe({
      next: (data) => console.log(data),
      error: (err) => (this.error = err.message),
      complete: () => this.router.navigateByUrl('admin'),
    });
  }

  getCategories() {
    this.api.getCategories().subscribe({
      next: (data) => (this.categories = data),
      error: (err) => (this.error = err.message),
      complete: () => (this.error = null),
    });
  }

  getOneCategory(id: number) {
    this.api.getOneCategory(id).subscribe({
      next: (data) => (this.category = data),
      error: (err) => (this.error = err.message),
      complete: () => (this.error = null),
    });
  }

  public onFileChanged(event: any) {
    this.file = event.target.files[0];
  }
}
