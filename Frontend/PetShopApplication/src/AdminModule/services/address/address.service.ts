import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Address } from '../../../models/Address';

@Injectable({
  providedIn: 'root'
})
export class AddressService {
  private baseUrl = 'http://localhost:9999/api/v1/address'; 

  constructor(private http: HttpClient) { }

  getAllAddress(): Observable<Address[]> {
    return this.http.get<Address[]>(this.baseUrl,{
      responseType:'json'});
  }
}