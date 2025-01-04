  import { Component, OnInit } from '@angular/core';
  import { Customer } from '../../../models/Customer';
  import { CustomerService } from '../../services/customers/customers.service';
  import { FormsModule } from '@angular/forms';
  import { CommonModule } from '@angular/common';
  import { Address } from '../../../models/Address';

  @Component({
    selector: 'app-customers',
    imports: [CommonModule, FormsModule],
    templateUrl: './customers.component.html',
    styleUrls: ['./customers.component.css']
  })
  export class CustomersComponent implements OnInit {
    customers: Customer[] = [];
  newCustomer: Customer = {} as Customer;
  newAddress: Address = {} as Address;
  editCustomerIndex: number | null = null;  // Track the index of the customer being edited
  showAddCustomerForm: boolean = false;
  errorMessage: string | null = null;

  constructor(private customerService: CustomerService) {}

  ngOnInit(): void {
    this.getCustomers();
  }

  getCustomers(): void {
    this.customerService.getAllCustomers().subscribe(
      (data) => {
        this.customers = data;
      },
      (error) => {
        console.error('Error fetching customers:', error);
        this.errorMessage = 'Failed to load customers. Please try again later.';
      }
    );
  }

  addCustomer(): void {
    this.customerService.addCustomer(this.newAddress, this.newCustomer).subscribe(
      (data) => {
        this.newCustomer = {} as Customer;
        this.newAddress = {} as Address;
        this.showAddCustomerForm = false;
        this.getCustomers();
      },
      (error) => {
        console.error('Error adding customer with address:', error);
        this.errorMessage = 'Failed to add customer with address. Please try again.';
      }
    );
  }

  // Toggle the visibility of the Add Customer form
  toggleAddCustomerForm(): void {
    this.showAddCustomerForm = !this.showAddCustomerForm;
  }

  // Start editing a customer
  editCustomer(index: number): void {
    this.editCustomerIndex = index;
  }

  // Save edited customer details
  saveCustomer(index: number): void {
    const updatedCustomer = this.customers[index];
    this.customerService.updateCustomer(updatedCustomer.customerId, updatedCustomer).subscribe(
      () => {
        this.editCustomerIndex = null;  // Close the edit mode
      },
      (error) => {
        console.error('Error updating customer:', error);
        this.errorMessage = 'Failed to update customer. Please try again.';
      }
    );
  }

  // Cancel editing
  cancelEdit(): void {
    this.editCustomerIndex = null;
  }
}