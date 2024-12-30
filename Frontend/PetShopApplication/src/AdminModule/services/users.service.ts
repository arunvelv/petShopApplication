// // users.service.ts
// import { Injectable } from '@angular/core';
// import { HttpClient } from '@angular/common/http';
// import { Observable } from 'rxjs';
// import { User } from '../../models/User';

// @Injectable({
//   providedIn: 'root'
// })
// export class UsersService {
//   private baseUrl = 'http://localhost:9999/api/users'; 

//   constructor(private http: HttpClient) { }

//   getUsers(): Observable<User[]> {
//     return this.http.get<User[]>(this.baseUrl);
//   }
// }