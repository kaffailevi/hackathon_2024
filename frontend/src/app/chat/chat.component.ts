import {Component, OnInit} from '@angular/core';
import {AccountService} from "../services/account.service";
import {Chat, UserService} from "../services/user.service";

export interface User {
  "firstName": string,
  "lastName": string,
  "email": string,
  "age": number,
  "avavilableForHire": boolean,
  "rating": number,
  "id": number
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
  constructor(private authService: AccountService,
              private userService: UserService) {
    if (this.authService.isLoggedIn()) {
      this.userId = this.authService.getUserId();
    }
  }

  ngOnInit() {
    if (this.authService.isLoggedIn()) {
      this.userService.getUsers().subscribe((response) => {
        this.users = response.filter((user) => user.id !== this.authService.getUserId());
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
}
