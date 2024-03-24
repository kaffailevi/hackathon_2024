import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {AccountService} from "../services/account.service";
import { User } from '../chat/chat.component';

@Component({
  selector: 'app-user-profile',
  templateUrl: './user-profile.component.html',
  styleUrls: ['./user-profile.component.css']
})
export class UserProfileComponent implements OnInit{
  profileForm!: FormGroup;
  user: User = {}  as User; // Assuming userService has a method to get user data

  constructor(private formBuilder: FormBuilder, private accountService: AccountService) { }

  ngOnInit(): void {
    // Initialize profile form with user data
    // this.user = this.accountService.getUser(); // Assuming userService has a method to get user data

    this.profileForm = this.formBuilder.group({
      firstName: [this.user.firstName, Validators.required],
      lastName: [this.user.lastName, Validators.required],
      email: [this.user.email, [Validators.required, Validators.email]],
      age: [this.user.age],
      availableForHire: [this.user.availableForHire.toString()],
      userPets: [this.user.],
    });
  }

  onSave(): void {
    if (this.profileForm.valid) {
      const updatedUserData = this.profileForm.value;
      this.accountService.updateUser(updatedUserData); // Update user data on the server
    }
  }
}
