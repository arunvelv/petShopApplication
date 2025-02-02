import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Supplier } from '../../../models/Supplier';
import { Address } from '../../../models/Address';

@Injectable({
  providedIn: 'root'
})
export class SuppliersService {

 
  private baseUrl = 'http://localhost:9999/api/v1/suppliers';

  constructor(private http: HttpClient) {}

  getAllSuppliers(): Observable<any[]> {
    return this.http.get<any[]>(this.baseUrl);
  }

  getSupplierById(id: number): Observable<Supplier> {
    return this.http.get<Supplier>(`${this.baseUrl}/${id}`, { responseType: 'json' });
  }

  getSuppliersByName(name: string): Observable<Supplier[]> {
    return this.http.get<Supplier[]>(`${this.baseUrl}/name/${name}`, { responseType: 'json' });
  }

  getSuppliersByCity(city: string): Observable<Supplier[]> {
    return this.http.get<Supplier[]>(`${this.baseUrl}/city/${city}`, { responseType: 'json' });
  }

  getSuppliersByState(state: string): Observable<Supplier[]> {
    return this.http.get<Supplier[]>(`${this.baseUrl}/state/${state}`, { responseType: 'json' });
  }

  addSupplier(address: Address, supplier: Supplier): Observable<any> {
    const payload = { address, supplier };
    return this.http.post(this.baseUrl + "/add", payload, { 
      responseType: 'json'
     });
  }

  


  updateSupplier(suppliersId: number, suppliersDetails: Supplier): Observable<Supplier> {
    return this.http.put<Supplier>(`${this.baseUrl}/update/${suppliersId}`, suppliersDetails, { responseType: 'json' });
  }

}
