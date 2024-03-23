import { Component } from '@angular/core';
import {SideMenuComponent} from "../side-menu/side-menu.component";

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent {
  isSideMenuOpen: boolean = false;
  sideMenuDisplay: string = 'none';
  showSideMenu() {
    this.isSideMenuOpen = !this.isSideMenuOpen;
    this.sideMenuDisplay = this.isSideMenuOpen ? 'block' : 'none';
  }
}
