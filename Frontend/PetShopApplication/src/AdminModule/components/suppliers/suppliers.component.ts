import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { Supplier } from '../../../models/Supplier';
import { SuppliersService } from '../../services/suppliers/suppliers.service';
import { FormsModule } from '@angular/forms';
import { Address } from '../../../models/Address';

@Component({
  selector: 'app-suppliers',
  imports: [CommonModule, FormsModule],
  templateUrl: './suppliers.component.html',
  styleUrl: './suppliers.component.css'
})
export class SuppliersComponent implements OnInit {
  suppliers: Supplier[] = [];
  newSupplier: Supplier = {} as Supplier;
  editSupplier: Supplier = {} as Supplier;
  newAddress: Address = {} as Address;
  showAddSupplierForm: boolean = false;
  showEditSupplierForm: boolean = false;
  errorMessage: string | null = null;

  searchParams: any = {
    name: '',
    city: '',
    state: ''
  };


  constructor(private suppliersService: SuppliersService) {}

  ngOnInit(): void {
    this.getSuppliers();
  }

  getSuppliers(): void {
    this.suppliersService.getAllSuppliers().subscribe(
      (data: Supplier[]) => {
        this.suppliers = data;
      },
      (error) => {
        console.error('Error fetching suppliers:', error);
      }
    );
  }

  searchByName(): void {
    if (this.searchParams.name) {
      this.suppliersService.getSuppliersByName(this.searchParams.name).subscribe(
        (data: Supplier[]) => {
          this.suppliers = data;
        },
        (error) => console.error('Error searching by name:', error)
      );
    }
  }

  searchByCity(): void {
    if (this.searchParams.city) {
      this.suppliersService.getSuppliersByCity(this.searchParams.city).subscribe(
        (data: Supplier[]) => {
          this.suppliers = data;
        },
        (error) => console.error('Error searching by city:', error)
      );
    }
  }

  searchByState(): void {
    if (this.searchParams.state) {
      this.suppliersService.getSuppliersByState(this.searchParams.state).subscribe(
        (data: Supplier[]) => {
          this.suppliers = data;
        },
        (error) => console.error('Error searching by state:', error)
      );
    }
  }

  addSupplier(): void {
    console.log('Address:', this.newAddress);
        console.log('Supplier:', this.newSupplier);
    this.suppliersService.addSupplier(this.newAddress, this.newSupplier).subscribe(

      (data) => {
        console.log('Address:', this.newAddress);
        console.log('Supplier:', this.newSupplier);
        // this.newSupplier = {} as Supplier;
        // this.newAddress = {} as Address;
        // this.getSuppliers();
      },
      (error) => {
        console.error('Error adding suppliers with address:', error);
          this.errorMessage = 'Failed to add supliers with address. Please try again.';
      }
    );
  }
  

  // addEmployee(): void {
  //   this.employeesService.addEmployee(this.newAddress, this.newEmployee).subscribe(
  //     (data) => {
  //         console.log(this.newAddress);
  //         console.log(this.newEmployee);
  //         this.newEmployee = {} as Employee;
  //       this.newAddress = {} as Address;
  //     this.getEmployees();
  //       },
  //       (error) => {
  //         console.error('Error adding customer with address:', error);
  //         this.errorMessage = 'Failed to add customer with address. Please try again.';
  //       }
  //     );
  //   }

  updateSupplier(): void {
    if (this.editSupplier && this.editSupplier.supplierId) {
      this.suppliersService.updateSupplier(this.editSupplier.supplierId, this.editSupplier).subscribe(
        (updatedSupplier: Supplier) => {
          const index = this.suppliers.findIndex(s => s.supplierId === updatedSupplier.supplierId);
          if (index !== -1) {
            this.suppliers[index] = updatedSupplier; // Update the supplier in the list
          }
          console.log('Supplier updated successfully!');
          this.editSupplier = {} as Supplier; // Exit edit mode
        },
        (error) => {
          console.error('Error updating supplier:', error);
        }
      );
    } else {
      console.error('Supplier ID is undefined or invalid.');
    }
  }
  editSelectedSupplier(supplier: Supplier): void {
    this.editSupplier = { ...supplier }; // Copy supplier data to edit form
  } 
}