import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppComponent } from './app.component';
import {SideMenuComponent} from "./side-menu/side-menu.component";
import {PostCardComponent} from "./post-card/post-card.component";
import {NavbarComponent} from "./navbar/navbar.component";
import {HomeComponent} from "./home/home.component";
import {RouterModule, RouterOutlet} from "@angular/router";
import { FooterComponent } from './footer/footer.component';
import { LoginComponent } from './authentification/login/login.component';
import { RegisterComponent } from './authentification/register/register.component';
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import { AddPostComponent } from './add-post/add-post.component';
import {AppRoutingModule} from "./app-routing.module";
import {HttpClientModule} from "@angular/common/http";
import { UserProfileComponent } from './user-profile/user-profile.component';
import { FileUploadComponent } from './file-upload/file-upload.component';
import { ChatComponent } from './chat/chat.component';

@NgModule({
  declarations: [AppComponent, HomeComponent, NavbarComponent, SideMenuComponent, PostCardComponent, FooterComponent, LoginComponent, RegisterComponent, AddPostComponent, UserProfileComponent, FileUploadComponent, ChatComponent],
  imports: [
    AppRoutingModule,
    RouterModule,
    BrowserModule,
    RouterOutlet,
    ReactiveFormsModule,
    FormsModule,
    HttpClientModule
  ],
  providers: [],
  bootstrap: [AppComponent],
})
export class AppModule {}
