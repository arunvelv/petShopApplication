import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Employee } from '../../../models/Employee';
import { Address } from '../../../models/Address';

@Injectable({
  providedIn: 'root'
})
export class EmployeesService {
  private baseUrl = 'http://localhost:9999/api/v1/employees'; 

  constructor(private http: HttpClient) { }

  getAllEmployees(): Observable<Employee[]> {
    return this.http.get<Employee[]>(this.baseUrl,{
      responseType: 'json'
    });
  }

  addEmployee(address: Address, employee: Employee): Observable<any> {
    const payload = { address,employee};
    return this.http.post(this.baseUrl + "/add", payload, {
      responseType: 'json'
    });
  }

  




  updateEmployee(employeeId: number, employee: Employee): Observable<Employee> {
    return this.http.put<Employee>(`${this.baseUrl}/update/${employeeId}`, employee, {
      responseType: 'json'
    });
  }

}