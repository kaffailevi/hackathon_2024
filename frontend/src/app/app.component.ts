import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
// import {NavbarComponent} from "./navbar/navbar.component";
// import {SideMenuComponent} from "./side-menu/side-menu.component";
// import {PostCardComponent} from "./post-card/post-card.component";
// import {CommonModule} from "@angular/common";

@Component({
  selector: 'app-root',
  // standalone: true,
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'web-app';
  public sidebarShow: boolean = false;
}
