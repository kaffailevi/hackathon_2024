import { Component } from '@angular/core';
import {SideMenuComponent} from "../side-menu/side-menu.component";

@Component({
  selector: 'app-navbar',
  standalone: true,
  imports: [
    SideMenuComponent
  ],
  templateUrl: './navbar.component.html',
  styleUrl: './navbar.component.css'
})
export class NavbarComponent {
  isSideMenuOpen: boolean = false;
  sideMenuDisplay: string = 'none';
  showSideMenu() {
    this.isSideMenuOpen = !this.isSideMenuOpen;
    this.sideMenuDisplay = this.isSideMenuOpen ? 'block' : 'none';
  }
}
