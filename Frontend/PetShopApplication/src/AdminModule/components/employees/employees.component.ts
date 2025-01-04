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


  toggleAddEmployeeForm(): void {
    this.showAddForm = !this.showAddForm;
    this.newEmployee = {} as Employee;
  }

  addEmployee(): void {
    this.employeesService.addEmployee(this.newAddress, this.newEmployee).subscribe(
      (data) => {
        console.log('Employee added successfully:', data);
        this.newEmployee = {} as Employee;
        this.newAddress = {} as Address;
        this.showAddForm = false; // Close the form after successful addition
        this.getEmployees(); // Refresh the employee list
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
    if (this.editingEmployee) {
      this.employeesService.updateEmployee(this.editingEmployee.employeeId, this.editingEmployee).subscribe(
        (updatedEmployee: Employee) => {
          const index = this.employees.findIndex(emp => emp.employeeId === updatedEmployee.employeeId);
          if (index !== -1) {
            this.employees[index] = updatedEmployee;
          }
          this.editingEmployee = null; // Exit edit mode
          this.getEmployees(); // Refresh list
        },
        (error) => {
          console.error('Error updating employee:', error);
          this.errorMessage = 'Failed to update employee. Please try again.';
        }
      );
    }
  }
  
  
  cancelEdit(): void {
    this.editingEmployee = null; // Exit edit mode without saving
  }
  


}