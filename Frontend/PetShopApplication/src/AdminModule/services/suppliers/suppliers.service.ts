// suppliers.service.ts
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Supplier } from '../../../models/Supplier';

@Injectable({
  providedIn: 'root'
})
export class SuppliersService {
  private baseUrl = 'http://localhost:9999/api/v1/suppliers';

  constructor(private http: HttpClient) { }

  getAllSuppliers(): Observable<Supplier[]> {
    return this.http.get<Supplier[]>(this.baseUrl,{
      responseType: 'json'
    });
  }
}