
import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { Pets } from '../../../models/Pets';

@Injectable({
  providedIn: 'root',
})
export class AdminPetsService {
  private  BASE_URL = 'http://localhost:9999/api/v1/pets'; // API Base URL

  constructor(private httpclient: HttpClient) {}

  // Utility function to get authorization headers
  private getHeaders(): HttpHeaders {
    
   
     // Add this line to check the token
    if (!localStorage.getItem('token')) {
      console.error('Token is missing!');
      throw new Error('Unauthorized: Token is missing');
    }
    return new HttpHeaders().set('Authorization', `Bearer ${localStorage.getItem('token')}`);
  }
  

  // Fetch all pets
  getAllPets(): Observable<Pets[]> {
    return this.httpclient
      .get<Pets[]>(this.BASE_URL, { headers: this.getHeaders() })
      .pipe(catchError(this.handleError));
  }

  // Add a new pet
  addPet(pet: Pets): Observable<any> {
    return this.httpclient
      .post(`${this.BASE_URL}/add`, pet, {
        
        responseType: 'text',
      })
      .pipe(catchError(this.handleError));
  }

  // Update an existing pet
  updatePet(pet: Pets): Observable<Pets> {
    return this.httpclient
      .put<Pets>(`${this.BASE_URL}/update/${pet.petId}`, pet, {
        headers: this.getHeaders(),
      })
      .pipe(catchError(this.handleError));
  }

  // Error handling
  private handleError(error: any): Observable<never> {
    console.error('An error occurred:', error);
    return throwError(() => new Error(error.message || 'Server Error'));
  }
}
