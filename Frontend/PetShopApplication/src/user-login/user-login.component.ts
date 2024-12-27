import { Component } from '@angular/core';
import { AuthenticationService } from '../services/auth-services.service';
import { User } from '../User';
import { FormsModule } from '@angular/forms';
import {CommonModule} from "@angular/common";
import { Router } from '@angular/router';
import { error } from 'node:console';

@Component({
  selector: 'app-user-login',
  imports: [FormsModule, CommonModule],
  templateUrl: './user-login.component.html',
  styleUrl: './user-login.component.css'
})
export class UserLoginComponent {
password: any;
username: any;
  constructor(private authenticationService: AuthenticationService, private router: Router){}
  user: User = {
    username: '',
    password: '',
    role: ''
  }

  token: any;

  
  UserLogin(){
    this.user.role = "ROLE_USER";
    this.authenticationService.UserLoginComponent(this.user).subscribe((e)=>{
      this.token = e.token;
      // alert(JSON.stringify(e));
      localStorage.setItem('token', this.token);
      localStorage.setItem('role', this.user.role);
      this.router.navigate(["/"])
    },(error) => {
      console.error('Error saving user:', error);
      alert(JSON.stringify(error));
    });
      

  }

  
}
