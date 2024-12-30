import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { Address } from '../../../models/Address';
import { AddressService } from '../../services/address/address.service';

@Component({
  selector: 'app-address',
  imports: [CommonModule],
  templateUrl: './address.component.html',
  styleUrl: './address.component.css'
})
export class AddressComponent implements OnInit {
  addresses: Address[] = [];

  constructor(private addressService: AddressService) { }

  ngOnInit(): void {
    this.getAddress();
  }

  getAddress() {
    this.addressService.getAllAddress().subscribe(
      (data) => {
        console.log('Received data:', data); // Inspect the response here
        this.addresses = data;
      },
      (error) => {
        console.error('Error fetching addresses:', error);
      }
    );
  }
}
