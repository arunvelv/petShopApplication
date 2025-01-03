import { Component, OnInit } from '@angular/core';
import { Pets } from '../../../models/Pets';
import {AdminPetsService } from '../../services/AdminPets/admin-pets.service';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { PetCategory } from '../../../models/PetCategory';
import { AdminPetsCategoriesService } from '../../services/AdminPets/admin-pets-categories.service';

@Component({
  selector: 'app-admin-pets',
  imports: [CommonModule, FormsModule],
  templateUrl: './admin-pets.component.html',
  styleUrl: './admin-pets.component.css'
})
export class AdminPetsComponent implements OnInit  {
  pets: Pets[] = []; // Array to store all pets
  categories: PetCategory[] = [];
  originalPets: Pets[] = []; // A copy of the original list for filtering
  selectedPet: Pets | null = null; // Pet selected for update
  newPet: Pets = {
    petId: 0,
    name: '',
    breed: '',
    age: 0,
    price: 0,
    description: '',
    imageUrl: '',
    category: {
      categoryId: 0,         // Default ID for category
      name: '',              // Default name for category
      petsList: []           // Empty list of pets
    }     
  };
 
  showAddForm = false; // Control visibility of "Add Pet" form
 
  constructor(private petService:  AdminPetsService,
    private petCatService :AdminPetsCategoriesService ) {}
 
  ngOnInit(): void {
    this.loadPets(); // Load pets on component initialization
    this.loadCategories();
  }
 
  // Fetches the list of all pets from the backend
  loadPets(): void {
    this.petService.getAllPets().subscribe({
      next: (data: Pets[]) => {
        this.pets = data;
        this.originalPets = [...data]; // Keep a copy of the original data for filtering
      },
      error: (err: any) => {
        console.error('Error fetching pets:', err);
        alert('Failed to load pets. Please try again later.');
      },
    });
  }
 
  loadCategories(): void {
    this.petCatService.getCategories().subscribe((data: any[]) => (this.categories = data));
  }
 
 
  onSearch(event: Event): void {
    const inputElement = event.target as HTMLInputElement; // Cast to HTMLInputElement
    const searchTerm = inputElement.value.toLowerCase();
 
    // Filter the pets list based on the search term across multiple fields
    this.pets = this.originalPets.filter((pet) =>
      pet.name.toLowerCase().includes(searchTerm) ||
      pet.breed.toLowerCase().includes(searchTerm) ||
      pet.category.name.toLowerCase().includes(searchTerm) ||
      pet.description.toLowerCase().includes(searchTerm)
    );
  }
 
 
 
 
  // Opens the "Add Pet" form
  startAddPet(): void {
    this.showAddForm = true;
  }
 
  addPet(): void {
    this.petService.addPet(this.newPet).subscribe({
      next: () => {
        this.loadPets(); // Refresh the list
        this.resetNewPetForm();
        this.showAddForm = false; // Hide the form
        alert('Pet added successfully');
      },
      error: (err: any) => {
        console.error('Error adding pet:', err);
        if (err.status === 403) {
          alert('You do not have permission to add a pet. Please check your authorization.');
        } else {      
          alert('Failed to add pet. Please check your input.');
        }
      },
    });
  }
 
 
  // Resets the "Add Pet" form
  cancelAddPet(): void {
    this.resetNewPetForm();
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
        this.originalPets = this.originalPets.map((p) =>
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
 
  // Displays all pets
  showAll(): void {
    this.pets = [...this.originalPets];
  }
 
  private resetNewPetForm(): void {
    this.newPet = {
      petId: 0,
      name: '',
      breed: '',
      age: 0,
      price: 0,
      description: '',
      imageUrl: '',
      category: { categoryId: 0, name: '', petsList: [] }
     
    };
  }
 }
