import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppComponent } from './app.component';
import {SideMenuComponent} from "./side-menu/side-menu.component";
import {PostCardComponent} from "./post-card/post-card.component";
import {NavbarComponent} from "./navbar/navbar.component";
import {HomeComponent} from "./home/home.component";
import {RouterOutlet} from "@angular/router";
import { FooterComponent } from './footer/footer.component';

@NgModule({
  declarations: [AppComponent, HomeComponent, NavbarComponent, SideMenuComponent, PostCardComponent, FooterComponent],
  imports: [
    BrowserModule,
    RouterOutlet
  ],
  providers: [],
  bootstrap: [AppComponent],
})
export class AppModule {}
