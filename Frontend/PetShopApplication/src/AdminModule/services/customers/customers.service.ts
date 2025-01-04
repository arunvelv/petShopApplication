import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Customer } from '../../../models/Customer';
import { Address } from '../../../models/Address';

@Injectable({
  providedIn: 'root'
})
export class CustomerService {
  private baseUrl = 'http://localhost:9999/api/v1/customers';

  constructor(private http: HttpClient) { }



  addCustomer(address: Address, customer: Customer): Observable<any> {
      const payload = { address,customer};
    return this.http.post(this.baseUrl + "/add", payload, {
      responseType: 'json'
    });
  }

  getAllCustomers(): Observable<any[]> {
    return this.http.get<any[]>(this.baseUrl);
  }

  getCustomerById(customerId: number): Observable<any> {
    return this.http.get(`${this.baseUrl}/${customerId}`);
  }

  getCustomerByName(firstName: string, lastName: string): Observable<any> {
    return this.http.get(`${this.baseUrl}/name/${firstName}/${lastName}`);
  }

  getCustomersByCity(city: string): Observable<any[]> {
    return this.http.get<any[]>(`${this.baseUrl}/city/${city}`);
  }

  getCustomersByState(state: string): Observable<any[]> {
    return this.http.get<any[]>(`${this.baseUrl}/state/${state}`);
  }

  getTransactionsByCustomerId(id: number): Observable<any[]> {
    return this.http.get<any[]>(`${this.baseUrl}/transactions/${id}`);
  }

  getCustomersByTransactionStatus(status: string): Observable<any[]> {
    return this.http.get<any[]>(`${this.baseUrl}/transactions-status/${status}`);
  }

  getCustomersWithoutTransactions(): Observable<any[]> {
    return this.http.get<any[]>(`${this.baseUrl}/no-transactions`);
  }

  getPetsByCustomerId(customerId: number): Observable<any[]> {
    return this.http.get<any[]>(`${this.baseUrl}/pets/${customerId}`);
  }

  updateCustomer(customerId: number, customerDetails: Customer): Observable<Customer> {
    return this.http.put<Customer>(`${this.baseUrl}/update/${customerId}`, customerDetails, {
      responseType: 'json'
    });
  }
}
