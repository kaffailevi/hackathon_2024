import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppComponent } from './app.component';
import {SideMenuComponent} from "./side-menu/side-menu.component";
import {PostCardComponent} from "./post-card/post-card.component";
import {NavbarComponent} from "./navbar/navbar.component";
import {HomeComponent} from "./home/home.component";
import {RouterOutlet} from "@angular/router";
import { FooterComponent } from './footer/footer.component';
import { LoginComponent } from './authentification/login/login.component';
import { RegisterComponent } from './authentification/register/register.component';
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import { AddPostComponent } from './add-post/add-post.component';

@NgModule({
  declarations: [AppComponent, HomeComponent, NavbarComponent, SideMenuComponent, PostCardComponent, FooterComponent, LoginComponent, RegisterComponent, AddPostComponent],
  imports: [
    BrowserModule,
    RouterOutlet,
    ReactiveFormsModule,
    FormsModule
  ],
  providers: [],
  bootstrap: [AppComponent],
})
export class AppModule {}
