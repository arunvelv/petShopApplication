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
  newCustomer: Customer = {} as Customer;
  editCustomer: Customer = {} as Customer;
  showAddCustomerForm: boolean = false;
  showEditCustomerForm: boolean = false;
  
    constructor(private customersService: CustomerService) { }
  
    ngOnInit(): void {
      this.getCustomerList();
    }

  searchType: string = 'name'; // Default search type
searchParams: any = {
  firstName: '',
  lastName: '',
  city: '',
  state: '',
  status: ''
};

searchByName(): void {
  const { firstName, lastName } = this.searchParams;
  this.customersService.getCustomerByName(firstName, lastName).subscribe(
    (data: Customer[]) => {
      this.customers = data;
    },
    (error) => console.error('Error searching by name:', error)
  );
}

searchByCity(): void {
  this.customersService.getCustomersByCity(this.searchParams.city).subscribe(
    (data: Customer[]) => {
      this.customers = data;
    },
    (error) => console.error('Error searching by city:', error)
  );
}

searchByState(): void {
  this.customersService.getCustomersByState(this.searchParams.state).subscribe(
    (data: Customer[]) => {
      this.customers = data;
    },
    (error) => console.error('Error searching by state:', error)
  );
}

searchByTransactionStatus(): void {
  this.customersService.getCustomersByTransactionStatus(this.searchParams.status).subscribe(
    (data: Customer[]) => {
      this.customers = data;
    },
    (error) => console.error('Error searching by transaction status:', error)
  );
}

searchCustomersWithoutTransactions(): void {
  this.customersService.getCustomersWithoutTransactions().subscribe(
    (data: Customer[]) => {
      this.customers = data;
    },
    (error) => console.error('Error searching customers without transactions:', error)
  );
}


  getCustomerList(): void {
    this.customersService.getAllCustomers().subscribe(
      (data: Customer[]) => {
        this.customers = data;
      },
      (error) => {
        console.error('Error fetching customers:', error);
      }
    );
  }

  addCustomer(customer: Customer): void {
    this.customersService.addCustomer(customer).subscribe(
      (addedCustomer: Customer) => {
        this.customers.push(addedCustomer);
        console.log('Customer added successfully!');
        this.newCustomer = {} as Customer; // Reset form
      },
      (error) => {
        console.error('Error adding customer:', error);
      }
    );
  }

  updateCustomer(customerId: number, customerDetails: Customer): void {
    this.customersService.updateCustomer(customerId, customerDetails).subscribe(
      (updatedCustomer: Customer) => {
        const index = this.customers.findIndex(c => c.customerId === customerId);
        if (index !== -1) {
          this.customers[index] = updatedCustomer;
        }
        console.log('Customer updated successfully!');
        this.editCustomer = {} as Customer;
      },
      (error) => {
        console.error('Error updating customer:', error);
      }
    );
  }
}