import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {HomeComponent} from "./home/home.component";
import {LoginComponent} from "./authentification/login/login.component";
import {RegisterComponent} from "./authentification/register/register.component";

const routes: Routes = [
  {path: '', redirectTo: 'home', pathMatch: 'full'},
  {component: HomeComponent, path: 'home'},
  {component: LoginComponent, path: 'login'},
  {component: RegisterComponent, path: 'register'}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
