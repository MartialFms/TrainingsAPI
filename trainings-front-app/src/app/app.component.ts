import { HttpEventType, HttpResponse } from '@angular/common/http';
import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { ApiService } from './services/api.service';
import { AuthentificationService } from './services/authentification.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css'],
})
export class AppComponent {
  title = 'trainings-front-app';
  username: string = '';
  cartSize: number = 0;

  constructor(
    private authService: AuthentificationService,
    private router: Router,
    private api: ApiService
  ) {}

  ngOnInit(): void {}

  //check if user is connected
  isLogged(): boolean {
    this.username = this.authService.getUser().username;
    return this.authService.isLogged ? true : false;
  }

  //check if user connected is admin
  isAdmin(): boolean {
    return this.authService.isAdmin ? true : false;
  }

  //to logout and redirect to form login
  logout() {
    this.authService.logout();
    this.router.navigateByUrl('login');
  }
}
