import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {User} from "../chat/chat.component";

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private httpClient: HttpClient) { }

  getUsers() {
    return this.httpClient.get<User[]>('http://localhost:8080/spring-api/user/all');
  }

  sendChat(message:Chat) {
    return this.httpClient.post<Chat>('http://localhost:8080/spring-api/api/v1/chat/add',
      message
    );
  }

  loadBetweenUsers(userId_1:number,userId_2:number) {
    return this.httpClient.get<Chat[]>('http://localhost:8080/spring-api/api/v1/chat/chat_between/'+userId_1+'/'+userId_2);
  }

}
export interface Chat {
  id:number
  message:string
  sendUserId:number
  receiveUserId:number
  date:string
}
