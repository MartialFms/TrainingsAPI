import { Component, OnInit } from '@angular/core';
import { environment } from 'src/environments/environment';
import { Training } from 'src/app/model/training';
import { CartService } from 'src/app/services/cart.service';
import { Router } from '@angular/router';
import { Category } from 'src/app/model/category';
import { ApiService } from 'src/app/services/api.service';

@Component({
  selector: 'app-trainings',
  templateUrl: './trainings.component.html',
  styleUrls: ['./trainings.component.css'],
})
export class TrainingsComponent implements OnInit {
  listTrainings: Training[] | undefined;
  categories: Category[] | undefined;
  error = null;
  title: string = '';
  catLink:number = 0;
  url:string = environment.host + "/trainingImage/";

  constructor(
    private cartService: CartService,
    private router: Router,
    private api: ApiService,

  ) {}

  ngOnInit(): void {
    this.getAllTrainings();
    this.getCategories();
  }

  getByCategories(id: number) {
    this.catLink = id;
    this.api.getByCategories(id).subscribe({
      next: (data) => (this.listTrainings = data, this.title = data[0].category.name),
      error: (err) => (this.error = err.message),
      complete: () => (this.error = null),
    });
  }

  getAllTrainings() {
    this.catLink = 0;
    this.api.getTrainings().subscribe({
      next: (data) => (this.listTrainings = data),
      error: (err) => (this.error = err.message),
      complete: () => (this.error = null),
    });
    this.title = 'Toutes les formations';
  }

  getCategories() {
    this.api.getCategories().subscribe({
      next: (data) => (this.categories = data),
      error: (err) => (this.error = err.message),
      complete: () => (this.error = null),
    });
  }

  addToCart(training: Training) {
    if (training.quantity > 0) {
      this.cartService.addTraining(training);
      this.router.navigateByUrl('cart');
    }
  }
}
