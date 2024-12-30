import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Customer } from '../../../models/Customer';

@Injectable({
  providedIn: 'root'
})
export class CustomerService {

  private baseUrl = 'http://localhost:9999/api/v1/customers'; 

  constructor(private http: HttpClient) { }

  getAllCustomers(): Observable<Customer[]> {
    return this.http.get<Customer[]>(this.baseUrl,
      {
        responseType: 'json'
      });
  }

  addCustomer(customer: Customer): Observable<Customer> {
    return this.http.post<Customer>(this.baseUrl + "/add", customer, {
      responseType: 'json'
    });
  }

  updateCustomer(customerId: number, customerDetails: Customer): Observable<Customer> {
    return this.http.put<Customer>(this.baseUrl + `/update/${customerId}`, customerDetails, {
      responseType: 'json'
    });
  }

}