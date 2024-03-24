import {Component, OnInit} from '@angular/core';
import {PostService} from "../services/post.service";
import {AccountService} from "../services/account.service";

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  public posts:Post[] = [];
  public currentUser:{}[] =[];
  public description:string = '';

  constructor(private postService: PostService,
              private accountService: AccountService,) {
  }

  ngOnInit() {
    if (this.accountService.isLoggedIn()) {

      this.postService.getPosts(this.accountService.getUserId()).subscribe((response) => {
        this.posts = response;
        for (let i = 0; i < this.posts.length; i++) {
          let postId = this.posts[i].id;
          this.postService.loadComments(postId).subscribe((response) => {
            this.posts[i].comments = response;
            console.log(response);
          });
        }
        console.log(this.posts);
      });


    }


  }

  createPost() {
    let post = {
      description: this.description,
      userId: this.accountService.getUserId()}
    this.postService.createPost(post).subscribe((response) => {
      this.posts.push(response);
      this.description = '';
    });
  }
}


interface Post {
  id: number;
  description: string;
  userId: number;
  likes: number;
  comments: Comment[];
  username: string;
  imageUrl: string;
}
 interface Comment {
  id: number;
  answer: string;
  userId: number;
  postId: number;
  date: string;
  username: string;
 }
