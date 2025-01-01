  import { CommonModule } from '@angular/common';
  import { Component, OnInit } from '@angular/core';
  import { Address } from '../../../models/Address';
  import { AddressService } from '../../services/address/address.service';
  import { FormsModule } from '@angular/forms';

  @Component({
    selector: 'app-address',
    imports: [CommonModule, FormsModule],
    templateUrl: './address.component.html',
    styleUrl: './address.component.css'
  })
  
  export class AddressComponent implements OnInit {
    addresses: Address[] = [];
    newAddress: Address = {} as Address; // For adding new addresses
    editingAddress: Address | null = null; // For editing an address
    errorMessage: string | null = null;
  
    constructor(private addressService: AddressService) {}
  
    ngOnInit(): void {
      this.getAddress();
    }
  
    getAddress(): void {
      this.addressService.getAllAddress().subscribe(
        (data) => {
          console.log('Received data:', data);
          this.addresses = data;
        },
        (error) => {
          console.error('Error fetching addresses:', error);
          this.errorMessage = 'Failed to load addresses. Please try again later.';
        }
      );
    }
  
    addAddress(): void {
      this.addressService.addAddress(this.newAddress).subscribe(
        (addedAddress) => {
          this.addresses.push(addedAddress);
          this.newAddress = {} as Address;
          this.getAddress();
        },
        (error) => {
          console.error('Error adding address:', error);
          this.errorMessage = 'Failed to add address. Please try again later.';
        }
      );
    }
  
    editAddress(address: Address): void {
      this.editingAddress = { ...address };
    }
  
    updateAddress(): void {
      if (this.editingAddress) {
        this.addressService.updateAddress(this.editingAddress.addressId, this.editingAddress).subscribe(
          (updatedAddress) => {
            const index = this.addresses.findIndex(addr => addr.addressId === updatedAddress.addressId);
            if (index !== -1) {
              this.addresses[index] = updatedAddress; // Update the list
              this.getAddress();
            }
            this.editingAddress = null; // Exit edit mode
          },
          (error) => {
            console.error('Error updating address:', error);
            this.errorMessage = 'Failed to update address. Please try again later.';
          }
        );
      }
    }
  
    cancelEdit(): void {
      this.editingAddress = null; // Exit edit mode without saving
    }
  }
  