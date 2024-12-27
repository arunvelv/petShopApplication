import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { UserLoginComponent } from '../user-login/user-login.component';
import { UserRegisterComponent } from '../user-register/user-register.component';
import { AdminLoginComponent } from '../admin-login/admin-login.component';

@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {
  constructor(private httpClient: HttpClient) { }
  RESTURL: string = "http://localhost:9999/api/";
 
 
  UserLoginComponent(user:any):Observable<any>{
    return this.httpClient.post(this.RESTURL+"login",user,{
      responseType:'json'
    })
  }

  UserRegisterComponent(user:any):Observable<any>{
    return this.httpClient.post(this.RESTURL+"user/register",user,{
      responseType:'json'
    })
  }

  AdminLogin(user:any):Observable<any>{
    return this.httpClient.post(this.RESTURL+"login",user,{
      responseType:'json'
    })
  }


  
}
 
 