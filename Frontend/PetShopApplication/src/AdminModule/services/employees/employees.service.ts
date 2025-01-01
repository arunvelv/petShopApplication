import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Employee } from '../../../models/Employee';

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

  addEmployee(employee: Employee): Observable<Employee> {
    return this.http.post<Employee>(this.baseUrl + "/add", employee, {
      responseType: 'json'
    });
  }

  updateEmployee(employeeId: number, employee: Employee): Observable<Employee> {
    return this.http.put<Employee>(this.baseUrl + `/update/${employeeId}`, employee, {
      responseType: 'json'
    });
  }
}