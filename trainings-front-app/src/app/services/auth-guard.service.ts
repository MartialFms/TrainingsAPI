import { Injectable } from '@angular/core';
import { CanActivate, Router } from '@angular/router';
import { AuthentificationService } from './authentification.service';

@Injectable({
  providedIn: 'root'
})
export class AuthGuardService implements CanActivate {
  constructor(public auth: AuthentificationService, public router: Router) {}

  canActivate(isAuth: any): boolean {
    if (!isAuth) {
      // alert('You are not allowed to view this page. Please login.');
      this.router.navigateByUrl('/login');
      return false;
    }
    return true;
  }
}
