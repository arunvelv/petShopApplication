import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { PetFood } from '../../../models/PetFood';

@Injectable({
  providedIn: 'root'
})
export class AdminPetFoodsService {
  private baseUrl = 'http://localhost:9999/api/v1/pet_foods';

  constructor(private http: HttpClient) {}
  getAllPetFoods(): Observable<PetFood[]> {
    return this.http.get<PetFood[]>(this.baseUrl);
  }
  updatePetFood(foodId: number, updatedPetFood: PetFood): Observable<PetFood>
  {
    const url = `${this.baseUrl}/update/${foodId}`; // Corrected template string
    return this.http.put<PetFood>(url, updatedPetFood);
  }
 
  searchByName(name: string): Observable<PetFood[]> {
    const url = `${this.baseUrl}/search?name=${name}`;
    return this.http.get<PetFood[]>(url);
  }
 
  // Fetch pet food by ID
  getPetFoodById(foodId: number): Observable<PetFood> {
    const url = `${this.baseUrl}/${foodId}`;
    return this.http.get<PetFood>(url);
  }
 
  // Search pet food by type
  searchByType(type: string): Observable<PetFood[]> {
    const url = `${this.baseUrl}/food_type/${type}`;
    return this.http.get<PetFood[]>(url);
  }
 
  searchById(foodId: number): Observable<PetFood>
  {
    const url = `${this.baseUrl}/${foodId}`;
    return this.http.get<PetFood>(url);
  }
 
 
  // Search pet food by brand
  searchByBrand(brand_name: string): Observable<PetFood[]> {
    const url = `${this.baseUrl}/brand/${brand_name}`;
    return this.http.get<PetFood[]>(url);
  }
 
  addPetFood(petFood: any): Observable<any> {
    return this.http.post(`${this.baseUrl}/add`, petFood);
  }
 

}
