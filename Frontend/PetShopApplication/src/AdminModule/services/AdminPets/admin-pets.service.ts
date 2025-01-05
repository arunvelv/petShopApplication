import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Pets } from '../../../models/Pets';

@Injectable({
  providedIn: 'root'
})
export class AdminPetsService {

 
  BASE_URL = 'http://localhost:9999/api/v1/pets'; // API Base URL
 
  constructor(private httpclient: HttpClient) {}
 
  // Fetch all pets
  getAllPets(): Observable<Pets[]> {
    return this.httpclient.get<Pets[]>(this.BASE_URL);
  }
 
  // Add a new pet
  addPet(pet: Pets): Observable<any> {
    return this.httpclient.post(`${this.BASE_URL}/add`, pet, { responseType: 'text' });
  }
 
  // Update an existing pet
  updatePet(pet: Pets): Observable<Pets> {
    return this.httpclient.put<Pets>(`${this.BASE_URL}/update/${pet.petId}`, pet);
  }
}

  
 
 