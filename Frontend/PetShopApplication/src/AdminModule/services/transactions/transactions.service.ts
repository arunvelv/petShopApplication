import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Transaction } from '../../../models/Transaction';

@Injectable({
  providedIn: 'root'
})
export class TransactionsService {

  private baseUrl = 'http://localhost:9999/api/v1/transaction_history'; 

  constructor(private http: HttpClient) { }

  getAllTransactions(): Observable<Transaction[]> {
    return this.http.get<Transaction[]>(this.baseUrl, {
      responseType: 'json'
    });
  }

  

  addTransaction(transaction: Transaction): Observable<any> {
    return this.http.post(`${this.baseUrl}/add`, transaction, {
      responseType: 'json'
    });
  }

  getSuccessfulTransactions(): Observable<Transaction[]> {
    return this.http.get<Transaction[]>(`${this.baseUrl}/successful`, {
      responseType: 'json'
    });
  }

  getFailedTransactions(): Observable<Transaction[]> {
    return this.http.get<Transaction[]>(`${this.baseUrl}/failed`, {
      responseType: 'json'
    });
  }

  getTransactionsByCustomerId(customerId: number): Observable<Transaction[]> {
    return this.http.get<Transaction[]>(`${this.baseUrl}/by_customer/${customerId}`, {
      responseType: 'json'
    });
  }
}
