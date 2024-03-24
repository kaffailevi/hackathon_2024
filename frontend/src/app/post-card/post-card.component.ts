import {Component, Input} from '@angular/core';
import {PostService} from "../services/post.service";
import {AccountService} from "../services/account.service";


@Component({
  selector: 'app-post-card',
  templateUrl: './post-card.component.html',
  styleUrls: ['./post-card.component.css']
})

export class PostCardComponent {

  @Input() post: Post = {} as Post;
  public currentComment: string = '';

  constructor(private postService: PostService,
              private accountService: AccountService) {
  }

  addComment() {
    // this.postService.addComment().subscribe((response) => {
    //   this.post.comments = response;
    //   this.currentComment = '';
    // });

  }

  sendLike(id: number) {
    this.postService.likePost(id, Number(this.accountService.getUserId())).subscribe((response) => {
      this.post = response;
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
