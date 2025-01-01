import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { PetCategory } from '../../../models/PetCategory';

@Injectable({
  providedIn: 'root'
})
export class AdminPetsCategoriesService {

  BASE_URL = 'http://localhost:9999/api/v1/categories'; // API Base URL
 
  constructor(private httpclient: HttpClient) {}
 
  // Fetch all categories
  getCategories(): Observable<PetCategory[]> {
    return this.httpclient.get<PetCategory[]>(this.BASE_URL);
  }
 
  // Fetch a category by its ID
  getCategoryById(categoryId: number): Observable<PetCategory> {
    return this.httpclient.get<PetCategory>(`${this.BASE_URL}/${categoryId}`);
  }
 
  // Fetch a category by its name
  getCategoryByName(categoryName: string): Observable<PetCategory> {
    return this.httpclient.get<PetCategory>(
      `${this.BASE_URL}/name/${categoryName}`
    );
  }
 
  // Add a new category
  addCategory(category: PetCategory): Observable<any> {
    return this.httpclient.post(`${this.BASE_URL}/add`, category, {
      responseType: 'text',
    });
  }
 
  // Update an existing category
  updateCategory(
    categoryId: number,
    updatedCategory: PetCategory
  ): Observable<PetCategory> {
    return this.httpclient.put<PetCategory>(
      `${this.BASE_URL}/update/${categoryId}`,
      updatedCategory
    );
  }
}