import { Injectable } from '@angular/core';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Observable , catchError, throwError} from 'rxjs';
// import { UserLoginComponent } from '../user-login/user-login.component';
// import { UserRegisterComponent } from '../user-register/user-register.component';
// import { AdminLoginComponent } from '../admin-login/admin-login.component';

@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {
  constructor(private httpClient: HttpClient) { }
  RESTURL: string = "http://localhost:9999/api/";

  
 
 
  UserLogin(user:any):Observable<any>{
    return this.httpClient.post(this.RESTURL+"login",user,{
      responseType:'json'
    }).pipe( catchError(this.handleError));
  }

  UserRegister(user:any):Observable<any>{
    return this.httpClient.post(this.RESTURL+"user/register",user,{
      responseType:'json'
    }).pipe( catchError(this.handleError));
  }

  AdminLogin(user:any):Observable<any>{
    return this.httpClient.post(this.RESTURL+"login",user,{
      responseType:'json'
    }).pipe( catchError(this.handleError));
  }

  private handleError(error: HttpErrorResponse): Observable<never> {
    console.error('Error occurred:', error);
    return throwError(error);
  }
  
}
 
 