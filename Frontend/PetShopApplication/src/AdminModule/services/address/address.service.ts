import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Address } from '../../../models/Address';

@Injectable({
  providedIn: 'root'
})
export class AddressService {
  private baseUrl = 'http://localhost:9999/api/v1/address';

  constructor(private http: HttpClient) {}

  // Get all addresses
  getAllAddress(): Observable<Address[]> {
    return this.http.get<Address[]>(this.baseUrl);
  }

  // Add a new address
  addAddress(address: Address): Observable<Address> {
    return this.http.post<Address>(`${this.baseUrl}/add`, address, {
      responseType: 'json'
    });
  }

  // Update an address by ID
  updateAddress(addressId: number, address: Address): Observable<Address> {
    return this.http.put<Address>(`${this.baseUrl}/update/${addressId}`, address,{
      responseType: 'json'
    });
  }
}
