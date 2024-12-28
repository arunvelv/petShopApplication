import { Component } from '@angular/core';
import { FormsModule } from '@angular/forms';
import {CommonModule} from "@angular/common";
import { AuthenticationService } from '../services/auth-services.service';
import { User } from '../../models/User';
import { Router } from '@angular/router';

@Component({
  selector: 'app-admin-login',
  imports: [CommonModule, FormsModule],
  templateUrl: './admin-login.component.html',
  styleUrl: './admin-login.component.css'
})
export class AdminLoginComponent {
  constructor(private authenticationService: AuthenticationService,  private router: Router){}
  user: User = {
    username: '',
    password: '',
    role: ''
  }

  token: any;
  AdminLogin(){
    this.user.role = "ROLE_ADMIN";
    this.authenticationService.UserLogin(this.user).subscribe((e)=>{
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
