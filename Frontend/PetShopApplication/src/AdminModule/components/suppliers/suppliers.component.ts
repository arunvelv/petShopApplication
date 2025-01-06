import { Component, OnInit } from '@angular/core';
import { Supplier } from '../../../models/Supplier';
import { SuppliersService } from '../../services/suppliers/suppliers.service';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { Address } from '../../../models/Address';

@Component({
  selector: 'app-suppliers',
  imports: [CommonModule, FormsModule],
  templateUrl: './suppliers.component.html',
  styleUrls: ['./suppliers.component.css']
})
export class SuppliersComponent implements OnInit {
  suppliers: Supplier[] = [];
  newSupplier: Supplier = {} as Supplier;
  newAddress: Address = {} as Address;
  showAddSupplierForm: boolean = false;
  editSupplierIndex: number | null = null; 
  showEditForm: boolean = false;
  errorMessage: string | null = null;



  constructor(private suppliersService: SuppliersService) {}

  ngOnInit(): void {
    this.getSuppliers();
  }

  // Fetch all suppliers
  getSuppliers(): void {
    this.suppliersService.getAllSuppliers().subscribe(
      (data) => {
        this.suppliers = data;
      },
      (error) => {console.error('Error fetching suppliers:', error);
      this.errorMessage = 'Failed to load suppliers. Please try again later.';
      }
      );
  }

  // Add a new supplier
  addSupplier(): void {
    this.suppliersService.addSupplier(this.newAddress, this.newSupplier).subscribe(
      ( data) => {
        console.log('Supplier added succesfully', data);
        this.newSupplier = {} as Supplier;
        this.newAddress = {} as Address;
        this.showAddSupplierForm = false;
        this.getSuppliers();
      },
      (error) => console.error('Error adding supplier:', error)
    );
  }

  toggleAddSupplierForm(): void {
    this.showAddSupplierForm = !this.showAddSupplierForm;
  }

  // Enable edit mode for a specific supplier
  editSupplier(index: number): void {
    this.editSupplierIndex = index;
  }

  // Save changes to supplier details (excluding address)
  saveSupplier(index: number): void {
    const updateSupplier = this.suppliers[index];
    this.suppliersService.updateSupplier(updateSupplier.supplierId, updateSupplier).subscribe(
      () => {
        this.editSupplierIndex = null;
      },
      (error) => console.error('Error updating supplier:', error)
    );
  }

  // Cancel edit and revert changes
  cancelEdit(): void {
    this.editSupplierIndex = null;
}

}