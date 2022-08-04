import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { User } from '../model/user';
import { TokenStorageService } from './token-storage.service';

@Injectable({
  providedIn: 'root',
})
export class AuthentificationService {
  user: User | undefined;
  currentUser: Map<string, String> | undefined;
  isLogged: boolean = false;
  isAdmin: boolean = false;

  constructor(
    private http: HttpClient,
    private tokenStorage: TokenStorageService
  ) {
    this.currentUser = new Map<string, String>();
  }

  //avec token ------------------------------
  loginTk(username: string, password: string): Observable<any> {

    let headerss = new HttpHeaders({
      'Content-Type': 'application/json',
      'Access-Control-Allow-Origin': 'http://localhost:4200',
      'Access-Control-Allow-Headers': 'Content-Type',
    });
    
    return this.http.post(
      'http://localhost:8080/api/signin',
      { username, password },
      {
        headers: headerss,
      }
    );
  }

  checkIfLogged(): boolean {
    if (this.tokenStorage.getToken()) {
      this.isLogged = true;
    }
    return this.isLogged;
  }

  checkIfAdmin(): boolean {
    if (this.tokenStorage.getUser().roles) {
      for (let r of this.tokenStorage.getUser().roles) {
        if (r.name == 'ADMIN') {
          this.isAdmin = true;
        }
      }
    }
    return this.isAdmin;
  }

  //disconnect user
  logout() {
    localStorage.removeItem('currentUser');
    this.isLogged = false;
    this.isAdmin = false;
  }

  getUser(): User {
    let currentUser = localStorage.getItem('currentUser');
    if (currentUser) {
      return JSON.parse(currentUser);
    }
    return new User(0, '', '', '', false, [], []);
  }
}
