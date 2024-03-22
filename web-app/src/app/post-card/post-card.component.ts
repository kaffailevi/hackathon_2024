import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';


@Component({
  selector: 'app-post-card',
  standalone: true,
  imports: [],
  templateUrl: './post-card.component.html',
  styleUrl: './post-card.component.css'
})

export class PostCardComponent {
  post: Post = { user_id: "John Doe", image: "assets/lea.jpg", description: 'Post 1', date: new Date(1, 2, 3) };
}

interface Post {
  user_id: string;
  image: string;
  description: string;
  date: Date;
}
