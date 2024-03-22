import { Component } from '@angular/core';
import {NavbarComponent} from "../navbar/navbar.component";
import {PostCardComponent} from "../post-card/post-card.component";
import {RouterOutlet} from "@angular/router";

@Component({
  selector: 'app-home',
  standalone: true,
    imports: [
        NavbarComponent,
        PostCardComponent,
        RouterOutlet
    ],
  templateUrl: './home.component.html',
  styleUrl: './home.component.css'
})
export class HomeComponent {

}
