import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { PetFood } from '../../../models/PetFood';
 
@Injectable({
  providedIn: 'root'
})
export class PetFoodsService {
  private baseUrl = 'http://localhost:9999/api/v1/pet_foods';
 
  constructor(private http: HttpClient) {}
 
  getAllPetFoods(): Observable<PetFood[]> {
    return this.http.get<PetFood[]>(this.baseUrl);
  }
 
  searchByName(name: string): Observable<PetFood[]> {
    const url = `${this.baseUrl}/search?name=${name}`;
    return this.http.get<PetFood[]>(url);
  }
 
  searchByBrand(brand: string): Observable<PetFood[]> {
    const url = `${this.baseUrl}/brand/${brand}`;
    return this.http.get<PetFood[]>(url);
  }
 
  searchByType(type: string): Observable<PetFood[]> {
    const url = `${this.baseUrl}/food_type/${type}`;
    return this.http.get<PetFood[]>(url);
  }
 
  
}
 