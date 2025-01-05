import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Pets } from '../../../models/Pets';

@Injectable({
  providedIn: 'root',
})
export class PetService {
  constructor(private httpClient: HttpClient) {}
   baseUrl:string = 'http://localhost:9999/api/v1/pets';


  getAllPets(): Observable<Pets[]> {
    return this.httpClient.get<Pets[]>(this.baseUrl+"",{
      responseType: 'json'
    });
  }

  // getPetById(petId: number): Observable<Pets> {
  //   return this.httpClient.get<Pets>(`${this.baseUrl}/${petId}`);
  // }

  // addPet(pet: Pets): Observable<Pets> {
  //   return this.http.post<Pets>(`${this.baseUrl}/add`, pet);
  // }

  // updatePet(pet: Pets): Observable<Pets> {
  //   return this.http.put<Pets>(`${this.baseUrl}/${pet.petId}`, pet);
  // }

  // deletePet(petId: number): Observable<void> {
  //   return this.http.delete<void>(`${this.baseUrl}/${petId}`);
  // }
}
