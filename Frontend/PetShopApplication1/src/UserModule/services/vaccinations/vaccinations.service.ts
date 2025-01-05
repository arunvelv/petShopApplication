import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Vaccination } from '../../../models/Vaccination';

@Injectable({
  providedIn: 'root'
})
export class VaccinationsService {
  constructor(private httpClient: HttpClient) {}
  baseUrl: string = 'http://localhost:9999/api/v1/vaccinations'; 

  getAllVaccinations(): Observable<Vaccination[]> {
    return this.httpClient.get<Vaccination[]>(this.baseUrl);
  }

}