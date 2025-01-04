import { Component, OnInit } from '@angular/core';
import { Pets } from '../../../models/Pets';
import { Router } from '@angular/router';
import { CommonModule } from '@angular/common';
import { PetService } from '../../services/pets/pets.service';
import { FormsModule } from '@angular/forms';

import { PetCategory } from '../../../models/PetCategory';
import { HttpClient } from '@angular/common/http';

declare var Razorpay:any;

@Component({
  selector: 'app-pets',
  imports: [ CommonModule, FormsModule],
  templateUrl: './pets.component.html',
  styleUrl: './pets.component.css',
})
export class PetsComponent implements OnInit {
  pets: Pets[] = [];
  filteredPets: Pets[] = [];
  categories: PetCategory[] = [];
  petId: number = 1;  
  newPet: Pets = this.getDefaultPet();
  selectedPet: Pets | null = null;
  showAddForm = false; // Initialized correctly
  showEditForm = false;
  searchQuery = ''; 

  constructor(private petService: PetService, private router: Router, private http: HttpClient) {}
    ngOnInit(): void {
        this.getPets();
        this.loadCategories();
    }
    private getDefaultPet(): Pets {
      return {
        petId: 0,
        name: '',
        breed: '',
        age: 0,
        price: 0,
        description: '',
        imageUrl: '',
        category: { categoryId: 0, name: 'Uncategorized', petsList: [] },
       
      };
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
 

    

  getPets(): void {
    this.petService.getAllPets().subscribe({
      next: (data) => {
        if (data.length > 0) {
          this.pets = data;
          this.filteredPets = data;
        } else {
          console.log('No pets found');
        }
      },
      error: (err) => {
        console.error('Error fetching pets:', err);
      },
    });
  }
  
  loadCategories(): void {
    // const token = localStorage.getItem('token');
    // const headers = new HttpHeaders().set('Authorization', `Bearer ${token}`);
  
     this.http.get<PetCategory[]>('http://localhost:9999/api/v1/categories', ).subscribe(
       (data: PetCategory[]) => {
         this.categories = data.map(category => ({
           ...category,
           name: category.name || 'Unnamed Category',
         }));
       },
       (error) => {
         console.error('Error fetching categories:', error);
       }
     );
   }
   cancelAction(): void {
    this.showAddForm = false;
    this.selectedPet = null;
    this.newPet = this.getDefaultPet();
  }
  onSearch(event: Event): void {
    const inputElement = event.target as HTMLInputElement; // Cast to HTMLInputElement
    const searchTerm = inputElement.value.toLowerCase();
 
    // Filter the pets list based on the search term across multiple fields
    this.pets = this.filteredPets.filter((pet) =>
      pet.name.toLowerCase().includes(searchTerm) ||
      pet.breed.toLowerCase().includes(searchTerm) ||
      pet.category.name.toLowerCase().includes(searchTerm) ||
      pet.description.toLowerCase().includes(searchTerm) ||
      pet.age.toString().includes(searchTerm) || // Include age
      pet.price.toString().includes(searchTerm)  // Include price
    );
  }
 
}
