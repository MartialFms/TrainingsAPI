import { HttpEventType, HttpResponse } from '@angular/common/http';
import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { ApiService } from './services/api.service';
import { AuthentificationService } from './services/authentification.service';
import { TokenStorageService } from './services/token-storage.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css'],
})
export class AppComponent {
  title = 'trainings-front-app';
  username: string = '';
  cartSize: number = 0;
  isLoggedIn: boolean = false;
  role: string[] = [];
  hasRoleAdmin: boolean = false;

  constructor(
    private tokenStorage: TokenStorageService,
    private authService: AuthentificationService
  ) {}

  ngOnInit(): void {
    if (this.authService.checkIfLogged()) {
      this.isLoggedIn = true;
      this.username = this.tokenStorage.getUser().username;
    }

    if (this.authService.checkIfAdmin()) {
      this.hasRoleAdmin = true;
    }
  }

  logout() {
    this.tokenStorage.signOut();
    window.location.reload();
  }
}
