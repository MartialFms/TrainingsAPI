import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { User } from 'src/app/model/user';
import { AuthentificationService } from 'src/app/services/authentification.service';
import { TokenStorageService } from 'src/app/services/token-storage.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
})
export class LoginComponent implements OnInit {
  loginForm: FormGroup;
  displayError: boolean = false;
  isLoggedIn: boolean = false;
  role: string[] = [];

  constructor(
    private router : Router,
    private formBuilder: FormBuilder,
    private authService: AuthentificationService,
    private tokenStorage: TokenStorageService
  ) {
    this.loginForm = this.formBuilder.group({
      email: ['', Validators.required],
      password: ['', Validators.required],
    });
  }

  ngOnInit(): void {
    if (this.tokenStorage.getToken()) {
      this.isLoggedIn = true;
      this.role = this.tokenStorage.getUser().role;
    }

    if(this.authService.checkIfLogged()){
      this.router.navigateByUrl("/trainings");
    }
  }

  onSubmitTk(form: FormGroup) {
    if (form.valid) {
      this.authService.loginTk(form.value.email, form.value.password).subscribe(
        (data) => {
          this.tokenStorage.saveToken(data.accessToken);
          this.tokenStorage.saveUser(data.user);
          this.isLoggedIn = true;
          this.role = this.tokenStorage.getUser().role;
          window.location.reload(); 
        },
        (err) => {
          this.displayError = true;
        }
      );
    }
  }
}
