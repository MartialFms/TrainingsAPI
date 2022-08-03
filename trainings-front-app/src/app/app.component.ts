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
    private authService: AuthentificationService,
    private router: Router,
    private api: ApiService,
    private tokenStorage: TokenStorageService
  ) {}

  ngOnInit(): void {
    if (this.tokenStorage.getToken()) {
      this.isLoggedIn = true;
      this.role = this.tokenStorage.getUser().role;
      this.isAdmin();
    }


  }

  //check if user is connected
  isLogged(): boolean {
    this.username = this.tokenStorage.getUser().username;
    return this.isLoggedIn;
  }

  //check if user connected is admin
  isAdmin(): boolean {
    // console.log(this.tokenStorage.getUser().roles.includes('ADMIN', 0));

    // console.log(this.tokenStorage.getUser().roles);

    for (let r of this.tokenStorage.getUser().roles) {
      if (r.name == 'ADMIN') {
        this.hasRoleAdmin = true;
      } 
    }

    // this.tokenStorage.getUser().roles.indexOf('ADMIN') !== -1
    //   ? (this.authService.isAdmin = true)
    //   : (this.authService.isAdmin = false);
    // console.log(this.authService.isAdmin);
    return this.hasRoleAdmin;
  }

  //to logout and redirect to form login
  logout() {
    this.authService.logout();
    this.router.navigateByUrl('login');
  }
}
