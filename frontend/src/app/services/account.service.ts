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

  getUserId(): number {
    console.log('getUserId running...');
    const token = localStorage.getItem(this.TOKEN);
    if (token) {
      const jwtHelper = new JwtHelperService();
      const decodedToken = jwtHelper.decodeToken(token);
      console.log('Decoded Token:', decodedToken); // Log the decoded token object
      if (decodedToken && decodedToken.userid) { // Access the user ID from the 'jti' property
        console.log('User ID:', decodedToken.userid); // Log the user ID
        return Number(decodedToken.userid); // Return the user ID
      }
    }
    return -1;
  }

  getUserEmail(): string | null {
    console.log('getUserId running...');
    const token = localStorage.getItem(this.TOKEN);
    if (token) {
      const jwtHelper = new JwtHelperService();
      const decodedToken = jwtHelper.decodeToken(token);
      console.log('Decoded Token:', decodedToken); // Log the decoded token object
      if (decodedToken && decodedToken.email) { // Access the user ID from the 'jti' property
        console.log('User ID:', decodedToken.email); // Log the user ID
        return decodedToken.email; // Return the user ID
      }
    }
    return null;
  }

  logout() {
    localStorage.removeItem(this.TOKEN);
    location.reload();
  }

  public isLoggedIn(): boolean {
    const jwt = new JwtHelperService();
    const token = localStorage.getItem(this.TOKEN);
    return !jwt.isTokenExpired(token);
  }

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
