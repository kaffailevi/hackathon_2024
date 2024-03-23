import {Component} from '@angular/core';
import {CommonModule} from '@angular/common';


@Component({
  selector: 'app-post-card',
  templateUrl: './post-card.component.html',
  styleUrls: ['./post-card.component.css']
})

export class PostCardComponent {
  // posts: Post[] = []; // Assuming you have an array of posts
  //
  // constructor() {
  //   // Initialize posts with showComments property
  //   this.posts = this.posts.map(post => {
  //     return { ...post, showComments: false };
  //   });
  // }
  // toggleComments(post: Post) {
  //   post.showComments = !post.showComments;
  // }
  post: Post = {
    user_profile: "assets/pp.jpg",
    user_id: "John Doe",
    image: "assets/dog.jpg",
    description: 'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. ',
    date: new Date(1, 2, 3),
    numberOfLikes: 10
  };
}

interface Post {
  user_profile: string;
  user_id: string;
  image: string;
  description: string;
  date: Date;
  numberOfLikes: number;
  showComments?: boolean;
}
