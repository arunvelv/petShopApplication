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
    editCustomer: Customer | null = null;
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
      // if (!this.newCustomer.name || !this.newCustomer.email || !this.newAddress.street || !this.newAddress.city || !this.newAddress.state || !this.newAddress.zipCode) {
      //   this.errorMessage = 'All fields are required.';
      //   return;
      // }
    
                // console.log(this.newCustomer);

      
      this.customerService.addCustomer(this.newAddress, this.newCustomer).subscribe(
        (data) => {
          // this.customers.push(addedCustomer);
          console.log(this.newAddress);
          console.log(this.newCustomer);
          // this.newCustomer = {} as Customer;
          // this.newAddress = {} as Address;
          // this.errorMessage = null;
          // this.newCustomer = data;
          // this.newAddress = data;

        },
        (error) => {
          console.error('Error adding customer with address:', error);
          this.errorMessage = 'Failed to add customer with address. Please try again.';
        }
      );
    }

    
    
    



    // updateCustomer(): void {
    //   if (this.editCustomer) {
    //     const payload = { customer: this.editCustomer, address: this.newAddress };
    //     this.customerService.updateCustomer(this.editCustomer.customerId).subscribe(
    //       (updatedCustomer: Customer) => {
    //         const index = this.customers.findIndex(c => c.customerId === updatedCustomer.customerId);
    //         if (index !== -1) {
    //           this.customers[index] = updatedCustomer;
    //         }
    //         console.log('Customer updated successfully!');
    //         this.editCustomer = null;
    //       },
    //       (error) => {
    //         console.error('Error updating customer:', error);
    //         this.errorMessage = 'Failed to update customer. Please try again later.';
    //       }
    //     );
    //   }
    // }
    cancelEdit(): void {
      this.editCustomer = null;
  }
}