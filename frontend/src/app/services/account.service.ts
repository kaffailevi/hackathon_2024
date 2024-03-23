import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Router} from "@angular/router";
import {ApiEndpoints} from "./api.endpoints";
import {map, Observable} from "rxjs";
import {JwtHelperService} from "@auth0/angular-jwt";

@Injectable({
  providedIn: 'root'
})
export class AccountService {
  private readonly TOKEN = "token";

  constructor(private http: HttpClient) {

  }

  login(email: string, password: string) {
    const headers = new HttpHeaders({
      'Content-Type': 'application/json',
      Authorization: 'Basic ' + btoa(email + ':' + password),
    });
    return this.http.post<any>(ApiEndpoints.login, {}, {headers}).pipe(
      map((response) => {
        const result = response[this.TOKEN];
        if (result) {
          localStorage.setItem(this.TOKEN, result);
          return true;
        } else {
          return false;
        }
      })
    );
  }

  logout() {
    localStorage.removeItem(this.TOKEN);
    location.reload();
  }

  // isLoggedIn(): boolean {
  //   const jwt = new JwtHelperService();
  //   const token = localStorage.getItem(this.TOKEN);
  //   if(token !== null){
  //     this.http.post(ApiEndpoints.CHECK_TOKEN, {token: token}).subscribe(
  //       (value) => {
  //         if(value == false){
  //           this.logout();
  //         }
  //       }
  //       , error => {
  //         this.logout();
  //       }
  //     );
  //     return !jwt.isTokenExpired(token);}
  //   return false;
  // }

  register(user: any) {
    return this.http.post(ApiEndpoints.register, user);
  }

  getToken(): string {
    return localStorage.getItem(this.TOKEN) as string;
  }

  getDecodedToken() {
    const jwt = new JwtHelperService();
    return jwt.decodeToken(this.getToken());
  }




}
