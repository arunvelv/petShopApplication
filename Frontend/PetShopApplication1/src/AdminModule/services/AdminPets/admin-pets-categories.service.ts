
import { HttpClient, HttpHeaders } from '@angular/common/http';
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
    const token = localStorage.getItem('token'); // Retrieve token from local storage or another source
    const headers = new HttpHeaders().set('Authorization', `Bearer ${token}`);
    return this.httpclient.get<PetCategory[]>(this.BASE_URL);
  }
 
  // Fetch a category by its ID
  getCategoryById(categoryId: number): Observable<PetCategory> {
    const token = localStorage.getItem('token'); // Retrieve token from local storage or another source
    const headers = new HttpHeaders().set('Authorization', `Bearer ${token}`);
    return this.httpclient.get<PetCategory>(`${this.BASE_URL}/${categoryId}`);
  }
 
  // Fetch a category by its name
  getCategoryByName(categoryName: string): Observable<PetCategory> {
    const token = localStorage.getItem('token'); // Retrieve token from local storage or another source
    const headers = new HttpHeaders().set('Authorization', `Bearer ${token}`);
    return this.httpclient.get<PetCategory>(
      `${this.BASE_URL}/name/${categoryName}`
    );
  }
 
  // Add a new category
  addCategory(category: PetCategory): Observable<any> {
    const token = localStorage.getItem('token'); // Retrieve token from local storage or another source
    const headers = new HttpHeaders().set('Authorization', `Bearer ${token}`);
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
