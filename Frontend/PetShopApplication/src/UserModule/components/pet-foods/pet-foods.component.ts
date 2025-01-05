import { Component, OnInit } from '@angular/core';
import { PetFoodsService } from '../../services/pet-foods/pet-foods.service';
import { PetFood } from '../../../models/PetFood';
import { CommonModule } from '@angular/common';
import { Router } from '@angular/router';
import { FormsModule } from '@angular/forms';

declare var Razorpay:any;

@Component({
  selector: 'app-pet-foods',
  imports: [ CommonModule,FormsModule],
  standalone:true,
  templateUrl: './pet-foods.component.html',
  styleUrl: './pet-foods.component.css'
})
export class PetFoodsComponent implements OnInit {
  petFoods: PetFood[] = [];
  filteredPetFoods: PetFood[] = [];
  searchTerm: string = '';
  selectedCategory: string = 'name';
  editingPetFood: PetFood | null = null;
  showAddForm: boolean = false;
  newPetFood: any = {
    name: '',
    brand: '',
    type: '',
    quantity: 0,
    price: 0,
    imageURL: ''
  };

  constructor(private petFoodsService: PetFoodsService, private router: Router) {}

  ngOnInit(): void {
    this.getPetFoods();
  }



  
  getPetFoods(): void {
    this.petFoodsService.getAllPetFoods().subscribe({
      next: (data) => {
        this.petFoods = data;
      },
      error: (err) => {
        console.error('Error fetching pet foods:', err);
      }
    });
  }
  onSearch() {
    console.log('Search term:', this.searchTerm);  // Check what the search term is
  console.log('selectedCategory:', this.selectedCategory);  // Check the selected category
  console.log('filteredPetFoods before search:', this.filteredPetFoods); // Debugging output
    console.log('filteredPetFoods:', this.filteredPetFoods); // Debugging output
    if (this.searchTerm.trim()) {
      switch (this.selectedCategory) {
        case 'name':
          this.searchByName();
          break;
        case 'brand':
          this.searchByBrand();
          break;
        case 'type':
          this.searchByType();
          break;
       
        default:
          break;
      }
    } else {
      this.filteredPetFoods = [...this.petFoods]; // Reset if search term is empty
    }
  }
 
  searchByName() {
    this.petFoodsService.searchByName(this.searchTerm).subscribe(
      (data: PetFood[]) => {
        console.log('Data from searchByName:', data);  // Log data received
        this.filteredPetFoods = Array.isArray(data) ? data : [];
      },
      (error: any) => {
        console.error('Error searching by name:', error);
        this.filteredPetFoods = [];
      }
    );
  }
 
 
  searchByBrand() {
    this.petFoodsService.searchByBrand(this.searchTerm).subscribe(
      (data: PetFood[]) => {
        this.filteredPetFoods = Array.isArray(data) ? data : [];
      },
      (error: any) => {
        console.error('Error searching by brand:', error);
        this.filteredPetFoods = [];
      }
    );
  }
 
  searchByType() {
    this.petFoodsService.searchByType(this.searchTerm).subscribe(
      (data: PetFood[]) => {
        this.filteredPetFoods = Array.isArray(data) ? data : [];
      },
      (error: any) => {
        console.error('Error searching by type:', error);
        this.filteredPetFoods = [];
      }
    );
  }
 
  payNow(price: number) {
    const amountInPaise = price;
    const RazorpayOptions = {
      key: 'rzp_test_zWhcqYLonnFntk',
      amount: amountInPaise*100,
      currency: 'INR',
      name: 'Pet Store',
      description: 'Sample Razorpay demo',
      image: 'https://i.imgur.com/FApqk3D.jpeg',
          prefill: {
            name: 'Pet Store',
            email: 'petstore@gmail.com',
            contact: '1234567890'
          },
          theme: {
            color: '#6466e3'
          },
          handler: (response: any) => {
           
            console.log('Payment successful. Payment ID:', response.razorpay_payment_id);
     
            // Navigate to home after successful payment
            // this.processPayment();
            this.router.navigate(['/']);
          },
          modal: {
            ondismiss: () => {
              console.log('Payment modal dismissed');
            }
          }
        };
     
        try {
          const rzp = new Razorpay(RazorpayOptions);
          rzp.open();
     
         
         
        } catch (error) {
          console.error('Error initializing Razorpay:', error);
        }
      }

}