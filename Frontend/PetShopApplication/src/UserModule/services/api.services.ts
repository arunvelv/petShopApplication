// import { Injectable } from '@angular/core';
// import { HttpClient } from '@angular/common/http';
// import { Observable } from 'rxjs';

// @Injectable({
//   providedIn: 'root',
// })
// export class ApiService {
//    baseUrl: string = 'http://localhost:9999/api/v1';
//   constructor(private http: HttpClient) {}

//   get(endpoint: string): Observable<any> {
//     return this.http.get(`${this.baseUrl}/${endpoint}`);
//   }

//   post(endpoint: string, data: any): Observable<any> {
//     return this.http.post(`${this.baseUrl}/${endpoint}`, data);
//   }

//   put(endpoint: string, data: any): Observable<any> {
//     return this.http.put(`${this.baseUrl}/${endpoint}`, data);
//   }

//   delete(endpoint: string): Observable<any> {
//     return this.http.delete(`${this.baseUrl}/${endpoint}`);
//   }
// }
