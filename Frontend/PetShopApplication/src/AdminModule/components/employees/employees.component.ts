import { Component, OnInit } from '@angular/core';
import { EmployeesService } from '../../services/employees/employees.service';
import { Employee } from '../../../models/Employee';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
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
  editingEmployeeIndex: any | null = null;
  newAddress: Address = {} as Address;
  showAddEmployeeForm: boolean = false;
  errorMessage: string | null = null;
  showEditForm: boolean = false;

  constructor(private employeesService: EmployeesService) {}

  ngOnInit(): void {
    this.getEmployees();
  }

  getEmployees(): void {
    this.employeesService.getAllEmployees().subscribe(
      (data) => {
        this.employees = data;
      },
      (error) => {
        console.error('Error fetching employees:', error);
      }
    );
  }

  toggleAddEmployeeForm(): void {
    this.showAddEmployeeForm = !this.showAddEmployeeForm;
    // this.newEmployee = {} as Employee;
  }

  addEmployee(): void {
    this.employeesService.addEmployee(this.newAddress, this.newEmployee).subscribe(
      () => {
        console.log('Employee added successfully:');
        this.newEmployee = {} as Employee;
        this.newAddress = {} as Address;
        this.showAddEmployeeForm = false;
        this.getEmployees();
      },
      (error) => {
        console.error('Error adding employee:', error);
        this.errorMessage = 'Failed to add employee. Please try again.';
      }
    );
  }

  editEmployee(index: number): void {
    this.editingEmployeeIndex = index;
    // this.newEmployee = { ...this.employees[index] };
  }

  updateEmployee(index: number): void {
    const updatedEmployee = this.employees[index]
      this.employeesService.updateEmployee(updatedEmployee.employeeId, updatedEmployee).subscribe(
        () => {
          
          this.editingEmployeeIndex = null; // Exit edit mode
        },
        (error) => {
          console.error('Error updating employee:', error);
          this.errorMessage = 'Failed to update employee. Please try again.';
        }
      );
    }
  
  cancelEdit(): void {
    this.editingEmployeeIndex = null;
  }
}