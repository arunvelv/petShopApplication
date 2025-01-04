import { Component, OnInit } from '@angular/core';
import { EmployeesService } from '../../services/employees/employees.service';
import { Employee } from '../../../models/Employee';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { HttpClient } from '@angular/common/http';
import { Address } from '../../../models/Address';

@Component({
  selector: 'app-employees',
  imports: [CommonModule, FormsModule],
  templateUrl: './employees.component.html',
  styleUrl: './employees.component.css'
})
export class EmployeesComponent implements OnInit {
  employees: Employee[] = [];
  newEmployee: Employee = {} as Employee;
  editingEmployee: Employee  | null = null;
  newAddress: Address = {} as Address;
  // editingEmployee: Employee = {} as Employee;
  addresses: any[] = []; 
  addressesForEmployees: any[] = [];
  showAddForm: boolean = false;
  showEditForm: boolean = false;
  errorMessage: string | null = null;

  constructor(private employeesService: EmployeesService, private http: HttpClient) {}

  ngOnInit(): void {
    this.getEmployees();
    // this.getAddresses();
  }

  getEmployees(): void {
    this.employeesService.getAllEmployees().subscribe(
      (data: Employee[]) => {
        this.employees = data;
      },
      (error) => {
        console.error('Error fetching employees:', error);
      }
    );
  }

  // getAddresses(): void {
  //   this.http.get<any[]>('http://localhost:9999/api/v1/address').subscribe(
  //     (data: any[]) => {
  //       this.addresses = data;
  //       this.addressesForEmployees = data; // Sync with dropdown
  //     },
  //     (error) => {
  //       console.error('Error fetching addresses:', error);
  //     }
  //   );
  // }

  toggleAddEmployeeForm(): void {
    this.showAddForm = !this.showAddForm;
    this.newEmployee = {} as Employee;
  }

  addEmployee(): void {
    this.employeesService.addEmployee(this.newAddress, this.newEmployee).subscribe(
      (data) => {
          console.log(this.newAddress);
          console.log(this.newEmployee);
          this.newEmployee = {} as Employee;
        this.newAddress = {} as Address;
      this.getEmployees();
        },
        (error) => {
          console.error('Error adding employee with address:', error);
          this.errorMessage = 'Failed to add employee with address. Please try again.';
        }
      );
    }

  editEmployee(employee: Employee): void {
    this.editingEmployee = { ...employee };
    this.showEditForm = true;
  }

  updateEmployee(employee: Employee): void {
    this.employeesService.updateEmployee(employee.employeeId, employee).subscribe(
      (updatedEmployee: Employee) => {
        const index = this.employees.findIndex(emp => emp.employeeId === updatedEmployee.employeeId);
        if (index !== -1) {
          this.employees[index] = updatedEmployee;
        }
        this.editingEmployee = null; // Exit edit mode
      },
      (error) => {
        console.error('Error updating employee:', error);
      }
    );
  }
  
  cancelEdit(): void {
    this.editingEmployee = null; // Exit edit mode without saving
  }
  


}