import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { User } from '../model/user';

@Injectable({
  providedIn: 'root',
})
export class AuthentificationService {
  user: User | undefined;
  currentUser: Map<string, String> | undefined;
  isLogged: boolean = false;
  isAdmin: boolean = false;

  private users = [
    // { name: 'mohamed', password: '123', roles: ['USER'] },
    // { name: 'maryne', password: '123', roles: ['USER'] },
    // { name: 'del', password: '123', roles: ['ADMIN', 'USER'] },
    // { name: 'hugo', password: '123', roles: ['USER'] },
    {
      id: 1,
      email: 'j.delmerie@live.fr',
      username: 'del',
      password: '123',
      enable: true,
      roles: ['ADMIN', 'USER'],
    },
  ];

  constructor(private http: HttpClient) {
    this.currentUser = new Map<string, String>();
  }

  // //save current user in localstorage
  // saveCurrentUser(user: User) {
  //   localStorage.setItem('currentUser', JSON.stringify(user));
  // }

  // //check if user existe and login to order
  // login(email: string, password: string) {
  //   if (!this.isLogged) {
  //     for (let u of this.users) {
  //       if (email == u.email && password == u.password) {
  //         this.user = new User(
  //           u.id,
  //           u.username,
  //           window.btoa(u.password),
  //           u.email,
  //           u.enable,
  //           u.roles,
  //           []
  //         );
  //         this.saveCurrentUser(this.user);
  //         this.isLogged = true;

  //         for (let r of u.roles) {
  //           if (r == 'ADMIN') {
  //             this.isAdmin = true;
  //           }
  //         }
  //       }
  //     }
  //   }
  // }

  //avec token ------------------------------
  loginTk(username: string, password: string): Observable<any> {
    console.log(new HttpHeaders({ 'Content-Type': 'application/json' }));
    return this.http.post(
      'http://localhost:8080/api/signin',
      { username, password },
      {
        headers: new HttpHeaders({ 'Content-Type': 'application/json' }),
      }
    );
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
