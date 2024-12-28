import { Component } from '@angular/core';
import { User } from '../../models/User';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { AuthenticationService } from '../services/auth-services.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-user-register',
  imports: [CommonModule, FormsModule],
  templateUrl: './user-register.component.html',
  styleUrls: ['./user-register.component.css']
})
export class UserRegisterComponent {
  constructor(
    private authenticationService: AuthenticationService,
    private router: Router
  ) {}

  user: User = {
    username: '',
    password: '',
    role: ''
  };

  message: string = '';
  showModal: boolean = false;

  UserRegister() {
    this.user.role = 'ROLE_USER';

    if (!this.user.username || !this.user.password) {
      this.message = 'Please fill in all the required fields.';
      return;
    }

    this.authenticationService.UserRegister(this.user).subscribe(
      (response) => {
        console.log('User registered successfully:', response);
        this.message = 'User account created successfully!';
        this.showModal = true;
        setTimeout(() => {
          this.showModal = false;
          this.router.navigate(['/user-login']);
        }, 1000);
      },
      (error) => {
        console.error('Error during registration:', error);
        this.message = 'Registration failed. Please try again.';
        this.showModal = true;
        setTimeout(() => {
          this.showModal = false;
        }, 3000);
      }
    );
  }
}
