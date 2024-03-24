import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";

@Injectable({
  providedIn: 'root'
})
export class PostService {

  constructor(
    private http: HttpClient
  ) { }

  getPosts(userId: number) {
    return this.http.get<any>("http://localhost:8080/spring-api/posts/all/"+userId);
  }

  createPost(post: {description: string, userId: number}) {
    return this.http.post<any>("http://localhost:8080/spring-api/posts/post/add", post);
  }

  likePost(postId: number, userId: number) {
    return this.http.get<any>("http://localhost:8080/spring-api/posts/like/"+postId+"/"+userId);
  }

  loadComments(postId: number) {
    return this.http.get<any>("http://localhost:8080/spring-api/posts/"+postId+"/comments");
  }










}
