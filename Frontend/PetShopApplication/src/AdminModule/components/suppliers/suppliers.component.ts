import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { Supplier } from '../../../models/Supplier';
import { SuppliersService } from '../../services/suppliers/suppliers.service';

@Component({
  selector: 'app-suppliers',
  imports: [CommonModule],
  templateUrl: './suppliers.component.html',
  styleUrl: './suppliers.component.css'
})
export class SuppliersComponent implements OnInit {
  suppliers: Supplier[] = [];

  constructor(private suppliersService: SuppliersService) { }

  ngOnInit(): void {
    this.getSuppliers();
  }

  getSuppliers() {
    this.suppliersService.getAllSuppliers().subscribe(
      (data: Supplier[]) => {
        this.suppliers = data;
      },
      (error) => {
        console.error('Error fetching suppliers:', error);
      }
    );
  }
}