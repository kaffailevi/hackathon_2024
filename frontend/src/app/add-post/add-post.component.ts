import {Component, Input} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";

@Component({
  selector: 'app-add-post',
  templateUrl: './add-post.component.html',
  styleUrls: ['./add-post.component.css']
})
export class AddPostComponent {
  showAddPost: boolean = false;
  newPostContent: string = '';

  constructor() { }

  toggleAddPostPopup() {
    this.showAddPost = !this.showAddPost;
    if (this.showAddPost) {
      // Disable scrolling on the background content when the pop-up is displayed
      document.body.style.overflow = 'hidden';
    } else {
      // Enable scrolling when the pop-up is closed
      document.body.style.overflow = '';
    }
  }

  addPost() {
    // Implement functionality to add a new post
    // This could involve sending an HTTP request to your server to save the new post data
    // After adding the post, close the pop-up window
    this.toggleAddPostPopup();
    // Reset new post content
    this.newPostContent = '';
  }


  // postForm: FormGroup;

  // constructor(private fb: FormBuilder /*, private postService: PostService*/) {
  //   this.postForm = this.fb.group({
  //     title: ['', Validators.required],
  //     content: ['', Validators.required],
  //     author: ['', Validators.required]
  //   });
  // }
  //
  // onSubmit(): void {
  //   if (this.postForm.valid) {
  //     const newPost = this.postForm.value;
  //     // this.postService.addPost(newPost);
  //     this.postForm.reset(); // Clear form fields after submission
  //     // Optionally provide feedback to the user (e.g., display a success message)
  //   }
  // }
}
