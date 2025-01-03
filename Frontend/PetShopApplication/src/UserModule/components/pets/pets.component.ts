// import { Component, OnInit } from '@angular/core';
// import { Pets } from '../../../models/Pets';
// import { Router } from '@angular/router';
// import { CommonModule } from '@angular/common';
// import { PetService } from '../../services/pets/pets.service';

// @Component({
//   selector: 'app-pets',
//   imports: [ CommonModule],
//   templateUrl: './pets.component.html',
//   styleUrl: './pets.component.css',
// })
// export class PetsComponent implements OnInit {
//   pets: Pets[] = [];
//   petId: number = 1;  

//   constructor(private petService: PetService, private router: Router) {}
//     ngOnInit(): void {
//         this.getPets();
//     }

//   getPets(): void {
//     this.petService.getAllPets().subscribe({
//       next: (data) => {
//         if (data.length > 0) {
//           this.pets = data;
//         } else {
//           console.log('No pets found');
//         }
//       },
//       error: (err) => {
//         console.error('Error fetching pets:', err);
//       },
//     });
//   }
  
// }






import { Component, OnInit } from '@angular/core';
import { Pets } from '../../../models/Pets';
import { Router } from '@angular/router';
import { CommonModule } from '@angular/common';
import { PetService } from '../../services/pets/pets.service';
import { FormsModule } from '@angular/forms';
import { PetCategory } from '../../../models/PetCategory';
import { HttpClient, HttpHeaders } from '@angular/common/http';
 
@Component({
  selector: 'app-pets',
  imports: [ CommonModule,FormsModule],
  templateUrl: './pets.component.html',
  styleUrl: './pets.component.css',
})
export class PetsComponent implements OnInit {
  pets: Pets[] = [];
  filteredPets: Pets[] = []; // For search results
  categories: PetCategory[] = [];
  newPet: Pets = this.getDefaultPet();
  selectedPet: Pets | null = null;
  showAddForm = false; // Initialized correctly
  showEditForm = false;
  searchQuery = ''; // Bind this to the search input
 
  constructor(
    private petService: PetService,
    private http: HttpClient
  ) {}
 
  ngOnInit(): void {
    this.loadPets();
    this.loadCategories();
  }
 
  // Default pet object to avoid undefined errors
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
 
  loadPets(): void {
    this.petService.getAllPets().subscribe(
      (data: Pets[]) => {
        this.pets = data;
        this.filteredPets = data; // Initialize filteredPets
      },
      (error) => {
        console.error('Error fetching pets:', error);
      }
    );
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