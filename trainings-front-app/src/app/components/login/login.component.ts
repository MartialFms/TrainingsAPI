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
    private formBuilder: FormBuilder,
    private authService: AuthentificationService,
    private router: Router,
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
  }

  onSubmit(form: FormGroup) {
    if (form.valid) {
      this.authService.login(form.value.email, form.value.password);
      // this.authService.loginTest(form.value.email, form.value.password).subscribe({
      //   next: (data) => (console.log(data)),
      //   error: (err) => (console.log(err)),
      // });
      if (!this.authService.isLogged) {
        this.displayError = true;
      } else {
        this.router.navigateByUrl('cart');
      }
    }
  }

  onSubmitTk(form: FormGroup) {
    if (form.valid) {
      console.log(form.value.email + " --- " + form.value.password)
      this.authService.loginTk(form.value.email, form.value.password).subscribe(
        (data) => {
          console.log("------------" + data.token + " --")
          this.tokenStorage.saveToken(data.token);
          this.tokenStorage.saveUser(data);
          this.isLoggedIn = true;
          this.role = this.tokenStorage.getUser().role;
          console.log(' + + Reussite de connection avec Json token + +');

        },
        (err) => {
          this.displayError = true;
          console.log(' - - Erreur de connection avec Json token - -');
        }
      );
    }
  }
}
