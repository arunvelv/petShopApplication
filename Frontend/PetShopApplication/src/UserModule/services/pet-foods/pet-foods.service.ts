import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { PetFood } from '../../../models/PetFood';

@Injectable({
  providedIn: 'root'
})

export class PetFoodsService {
  constructor(private httpClient: HttpClient) {}
  baseUrl: string = 'http://localhost:9999/api/v1/pet_foods';

  getAllPetFoods(): Observable<PetFood[]> {
    return this.httpClient.get<PetFood[]>(this.baseUrl);
  }

}