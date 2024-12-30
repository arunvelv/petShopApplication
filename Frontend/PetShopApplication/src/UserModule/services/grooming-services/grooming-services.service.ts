import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { GroomingService } from '../../../models/Grooming-Services';

@Injectable({
  providedIn: 'root'
})
export class GroomingServicesService {
  constructor(private httpClient: HttpClient) {}
  baseUrl: string = 'http://localhost:9999/api/v1/services'; 

  getAllGroomingServices(): Observable<GroomingService[]> {
    return this.httpClient.get<GroomingService[]>(this.baseUrl);
  }

  // Add other methods like getGroomingServiceById, createGroomingService, 
  // updateGroomingService, deleteGroomingService
  // ...
}