import { Routes } from '@angular/router';
import {PostCardComponent} from "./post-card/post-card.component";
import {NavbarComponent} from "./navbar/navbar.component";
import {HomeComponent} from "./home/home.component";

export const routes: Routes = [
  {component: HomeComponent, path: 'home'}

];
