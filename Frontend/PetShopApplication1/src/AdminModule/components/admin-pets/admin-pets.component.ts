
import { Component, OnInit } from '@angular/core';
import { Pets } from '../../../models/Pets';
import { AdminPetsService } from '../../services/AdminPets/admin-pets.service';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { PetCategory } from '../../../models/PetCategory';
import { HttpClient, HttpHeaders } from '@angular/common/http';

@Component({
  selector: 'app-admin-pets',
  standalone: true,
  imports: [CommonModule, FormsModule ],
  templateUrl: './admin-pets.component.html',
  styleUrls: ['./admin-pets.component.css']
})
export class AdminPetsComponent implements OnInit {
  pets: Pets[] = [];
  filteredPets: Pets[] = []; // For search results
  categories: PetCategory[] = [];
  newPet: Pets = this.getDefaultPet();
  selectedPet: Pets | null = null;
  showAddForm = false; // Initialized correctly
  showEditForm = false;
  searchQuery = ''; // Bind this to the search input

  constructor(
    private petService: AdminPetsService,
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
  

  startAddPet(): void {
    this.showAddForm = !this.showAddForm;
    this.newPet = this.getDefaultPet();
    console.log('Add Pet toggled:', this.showAddForm); // Debug log
  }

  cancelAction(): void {
    this.showAddForm = false;
    this.selectedPet = null;
    this.newPet = this.getDefaultPet();
  }

  addPet(): void {
    this.petService.addPet(this.newPet).subscribe(
      (addedPet: Pets) => {
        this.pets.push(addedPet);
        this.filteredPets = [...this.pets]; // Update filtered list
        this.cancelAction();
        this.loadPets();
        alert(" pet  added successfully");
      },
      (error) => {
        console.error('Error adding pet:', error);
      }
    );
  }

  cancelAddPet(): void {
    this.getDefaultPet();
    this.showAddForm = false; // Hide the form
  }
 
  // Prepares the selected pet for updating
  startUpdate(pet: Pets): void {
    this.selectedPet = { ...pet }; // Clone the pet to avoid modifying the original
  }
 
  // Updates the selected pet
  updatePet(): void {
    if (!this.selectedPet) return;
 
    this.petService.updatePet(this.selectedPet).subscribe({
      next: (updatedPet: Pets) => {
        // Update both lists (original and filtered)
        this.pets = this.pets.map((p) =>
          p.petId === updatedPet.petId ? updatedPet : p
        );
        this.filteredPets = this.filteredPets.map((p) =>
          p.petId === updatedPet.petId ? updatedPet : p
        );
        this.selectedPet = null; // Clear the selected pet
        alert("Updated pet successfully");
      },
      error: (err: any) => {
        console.error('Error updating pet:', err);
        alert('Failed to update pet.');
      },
    });
  }
 
  // Cancels the update process
  cancelUpdate(): void {
    this.selectedPet = null; // Clear the selected pet
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
