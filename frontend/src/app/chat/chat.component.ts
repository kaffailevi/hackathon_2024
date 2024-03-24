import {Component, OnInit} from '@angular/core';
import {AccountService} from "../services/account.service";
import {Chat, UserService} from "../services/user.service";

export interface User {
  "firstName": string,
  "lastName": string,
  "email": string,
  "age": number,
  "availableForHire": boolean,
  "rating": number,
  "id": number
}


export interface  Pet {

  id: 2,
  name: string,
  breed: string,
  birthDate: string,
  lastLocation_id: null,
  profilePictureName: string,
  friends: [],
  favouriteLocations: []
}


@Component({
  selector: 'app-chat',
  templateUrl: './chat.component.html',
  styleUrls: ['./chat.component.css']
})
export class ChatComponent implements OnInit {
  users: User[] = []
  sendUserId: number = 0;
  public chats: Chat[] = [];
  userId: number | undefined;
  message: string = '';
  pets: Pet[] = [];
  myPets: Pet[] = [];
  selectedPet :Pet = {} as Pet;
  constructor(private authService: AccountService,
              private userService: UserService) {
    if (this.authService.isLoggedIn()) {
      this.userId = this.authService.getUserId();
    }
  }

  ngOnInit() {
    if (this.authService.isLoggedIn()) {
      this.userService.getUsers().subscribe((response) => {
        this.users = response.filter((user) => user.id !== this.authService.getUserId())

        // this.users = this.users.map((user) => {
        //   user.avavilableForHire = Boolean(user.avavilableForHire);
        //   return user;
        // });
        console.log(this.users)
      });


    }
  }

  confChat(userId: number) {
    this.sendUserId = userId;
    this.userService.loadBetweenUsers(this.authService.getUserId(), userId).subscribe((response) => {
      this.chats = response;
    });
  }


  sendMessage() {
    let chat: Chat = {
      id: 0,
      message: this.message,
      sendUserId: this.authService.getUserId(),
      receiveUserId: this.sendUserId,
      date: new Date().toISOString().split('T')[0]
    }
    this.userService.sendChat(chat).subscribe((response) => {
      this.chats.push(response);
      this.message = '';
    });
  }

  loadPets(id: number) {
    this.pets = [];
    this.userService.loadPets(id).subscribe((response) => {
      this.pets = response;
      console.log(response)
    });
    this.userService.loadPets(this.authService.getUserId()).subscribe((response) => {
      this.myPets = response;
      console.log(response)
    });
  }

  loadPet(id: number  ) {
    this.selectedPet = this.pets.find((pet) => pet.id === id) as Pet;
    console.log(this.selectedPet)
  }

  addFriend(myPetId:number) {
    this.userService.addFriend(myPetId, this.selectedPet.id).subscribe((response) => {
      console.log(response);
    });
  }
}
