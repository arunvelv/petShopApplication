import { Component, OnInit } from '@angular/core';
import { AdminPetFoodsService} from '../../services/AdminPetFoods/admin-pet-foods.service';
import { PetFood } from '../../../models/PetFood';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-admin-pet-foods',
  standalone:true,
  imports: [CommonModule,FormsModule],
  templateUrl: './admin-pet-foods.component.html',
  styleUrl: './admin-pet-foods.component.css'
})
export class AdminPetFoodsComponent implements OnInit
{
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
  AdminPetFoodService: any;
  constructor (AdminPetFoodService:AdminPetFoodsService )
  {}

  ngOnInit(): void {
    throw new Error('Method not implemented.');
  }

  toggleAddForm() {
    this.showAddForm = !this.showAddForm;
  }

  addPetFood(): void {
    if (
      this.newPetFood.name &&
      this.newPetFood.brand &&
      this.newPetFood.type &&
      this.newPetFood.quantity > 0 &&
      this.newPetFood.price > 0
    ) {
      this.AdminPetFoodService.addPetFood(this.newPetFood).subscribe({
        next: (response) => {
          this.getPetFoods();
          this.newPetFood = { name: '', brand: '', type: '', quantity: 0, price: 0 };
          this.showAddForm = false;
          alert('Pet food item has been added successfully!');
        },
        error: (error) => {
          console.error('Error adding pet food:', error);
          alert('Failed to add pet food. Please try again.');
        },
      });
    } else {
      alert('Please fill in all fields correctly.');
    }
  }

  getPetFoods() {
    this.AdminPetFoodService.getAllPetFoods().subscribe(
      (data: PetFood[]) => {
        this.petFoods = data;
        this.filteredPetFoods = data;
      },
      (error: any) => {
        console.error('Error fetching pet foods:', error);
      }
    );
  }

  editPetFood(petFood: PetFood) {
    this.editingPetFood = { ...petFood };
  }

  updatePetFood() {
    if (this.editingPetFood) {
      this.AdminPetFoodService.updatePetFood(this.editingPetFood.foodId, this.editingPetFood).subscribe(
        (updatedPetFood: PetFood) => {
          const index = this.petFoods.findIndex(pf => pf.foodId === updatedPetFood.foodId);
          if (index !== -1) {
            this.petFoods[index] = updatedPetFood;
          }
          this.filteredPetFoods = [...this.petFoods];
          this.editingPetFood = null;
        },
        (error: any) => {
          console.error('Error updating pet food:', error);
        }
      );
    }
  }

  cancelEdit() {
    this.editingPetFood = null;
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
        case 'id':
          this.searchById();
          break;
        default:
          break;
      }
    } else {
      this.filteredPetFoods = [...this.petFoods]; // Reset if search term is empty
    }
  }

  searchByName() {
    this.AdminPetFoodService.searchByName(this.searchTerm).subscribe(
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
    this.AdminPetFoodService.searchByBrand(this.searchTerm).subscribe(
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
    this.AdminPetFoodService.searchByType(this.searchTerm).subscribe(
      (data: PetFood[]) => {
        this.filteredPetFoods = Array.isArray(data) ? data : [];
      },
      (error: any) => {
        console.error('Error searching by type:', error);
        this.filteredPetFoods = [];
      }
    );
  }

  searchById() {
    const id = Number(this.searchTerm.trim());
    if (id > 0) {
      this.AdminPetFoodService.getPetFoodById(id).subscribe(
        (data: PetFood) => {
          this.filteredPetFoods = Array.isArray([data]) ? [data] : []; // Wrap single object into an array
        },
        (error: any) => {
          console.error('Error searching by ID:', error);
          this.filteredPetFoods = [];
        }
      );
    } else {
      console.warn('Invalid ID entered. Please enter a positive number.');
      this.filteredPetFoods = [];
    }
  }
}


