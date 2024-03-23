import { Component } from '@angular/core';
import {SideMenuComponent} from "../side-menu/side-menu.component";
import {Router} from "@angular/router";

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent {
  constructor(private router: Router) {}
  isSideMenuOpen: boolean = false;
  sideMenuDisplay: string = 'none';
  showSideMenu() {
    this.isSideMenuOpen = !this.isSideMenuOpen;
    this.sideMenuDisplay = this.isSideMenuOpen ? 'block' : 'none';
  }

  redirectToLogin() {
    console.log("Login");
    this.router.navigate(['/login']);
  }

  redirectToRegister() {
    console.log("Register");
    this.router.navigate(['/register']);
  }
}
