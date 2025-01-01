import { Component, OnInit } from '@angular/core';
import { Customer } from '../../../models/Customer';
import { CustomerService } from '../../services/customers/customers.service';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-customers',
  imports: [CommonModule, FormsModule],
  templateUrl: './customers.component.html',
  styleUrls: ['./customers.component.css']
})


export class CustomersComponent implements OnInit {
  customers: Customer[] = [];
  editingCustomer: Customer | null = null; // Tracks the customer being edited
  newCustomer: Customer = {} as Customer; // New customer form data
  errorMessage: string | null = null; // For error handling

  // Search fields
  searchType: string = 'name'; // Default search type
  searchParams: any = {
    firstName: '',
    lastName: '',
    city: '',
    state: '',
    status: ''
  };

  constructor(private customerService: CustomerService) {}

  ngOnInit(): void {
    this.getCustomerList();
  }

  // Fetch all customers
  getCustomerList(): void {
    this.customerService.getAllCustomers().subscribe(
      (data: Customer[]) => {
        this.customers = data;
      },
      (error) => {
        console.error('Error fetching customers:', error);
        this.errorMessage = 'Failed to load customers.';
      }
    );
  }

  // Add a new customer
  addCustomer(): void {
    this.customerService.addCustomer(this.newCustomer).subscribe(
      (addedCustomer: Customer) => {
        this.customers.push(addedCustomer);
        this.newCustomer = {} as Customer; // Reset form
      },
      (error) => {
        console.error('Error adding customer:', error);
        this.errorMessage = 'Failed to add customer.';
      }
    );
  }

  // Enable edit mode for a customer
  editCustomer(customer: Customer): void {
    this.editingCustomer = { ...customer }; // Clone the customer object
  }

  // Save the updated customer
  updateCustomer(): void {
    if (this.editingCustomer) {
      this.customerService.updateCustomer(this.editingCustomer.customerId, this.editingCustomer).subscribe(
        (updatedCustomer: Customer) => {
          const index = this.customers.findIndex(c => c.customerId === updatedCustomer.customerId);
          if (index !== -1) {
            this.customers[index] = updatedCustomer;
          }
          this.editingCustomer = null; // Exit edit mode
        },
        (error) => {
          console.error('Error updating customer:', error);
          this.errorMessage = 'Failed to update customer.';
        }
      );
    }
  }

  // Cancel editing
  cancelEdit(): void {
    this.editingCustomer = null; // Exit edit mode
  }

  // Search customers by name
  searchByName(): void {
    const { firstName, lastName } = this.searchParams;
    this.customerService.getCustomerByName(firstName, lastName).subscribe(
      (data: Customer[]) => {
        this.customers = data;
      },
      (error) => console.error('Error searching by name:', error)
    );
  }

  // Search customers by city
  searchByCity(): void {
    this.customerService.getCustomersByCity(this.searchParams.city).subscribe(
      (data: Customer[]) => {
        this.customers = data;
      },
      (error) => console.error('Error searching by city:', error)
    );
  }

  // Search customers by state
  searchByState(): void {
    this.customerService.getCustomersByState(this.searchParams.state).subscribe(
      (data: Customer[]) => {
        this.customers = data;
      },
      (error) => console.error('Error searching by state:', error)
    );
  }

  // Search customers by transaction status
  searchByTransactionStatus(): void {
    this.customerService.getCustomersByTransactionStatus(this.searchParams.status).subscribe(
      (data: Customer[]) => {
        this.customers = data;
      },
      (error) => console.error('Error searching by transaction status:', error)
    );
  }

  // Search customers without transactions
  searchCustomersWithoutTransactions(): void {
    this.customerService.getCustomersWithoutTransactions().subscribe(
      (data: Customer[]) => {
        this.customers = data;
      },
      (error) => console.error('Error searching customers without transactions:', error)
    );
  }
}