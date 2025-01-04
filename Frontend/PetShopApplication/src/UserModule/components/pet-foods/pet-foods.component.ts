import { Component, OnInit } from '@angular/core';
import { PetFoodsService } from '../../services/pet-foods/pet-foods.service';
import { PetFood } from '../../../models/PetFood';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
 
@Component({
  selector: 'app-pet-foods',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './pet-foods.component.html',
  styleUrls: ['./pet-foods.component.css']
})
export class PetFoodsComponent implements OnInit {
  petFoods: PetFood[] = [];
  filteredPetFoods: PetFood[] = [];
  searchTerm: string = '';
  selectedCategory: string = 'name';
 
  constructor(private  petFoodsService: PetFoodsService) {}
 
  ngOnInit(): void {
    this.getPetFoods();
  }
 
  getPetFoods(): void {
    this.petFoodsService.getAllPetFoods().subscribe(
      (data: PetFood[]) => {
        this.petFoods = data;
        this.filteredPetFoods = data;
      },
      (error: any) => {
        console.error('Error fetching pet foods:', error);
      }
    );
  }
 
  onSearch(): void {
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
          this.filteredPetFoods = [...this.petFoods];
      }
    } else {
      this.filteredPetFoods = [...this.petFoods];
    }
  }
 
  searchByName(): void {
    this.petFoodsService.searchByName(this.searchTerm).subscribe(
      (data: PetFood[]) => {
        this.filteredPetFoods = data;
      },
      (error: any) => {
        console.error('Error searching by name:', error);
        this.filteredPetFoods = [];
      }
    );
  }
 
  searchByBrand(): void {
    this.petFoodsService.searchByBrand(this.searchTerm).subscribe(
      (data: PetFood[]) => {
        this.filteredPetFoods = data;
      },
      (error: any) => {
        console.error('Error searching by brand:', error);
        this.filteredPetFoods = [];
      }
    );
  }
 
  searchByType(): void {
    this.petFoodsService.searchByType(this.searchTerm).subscribe(
      (data: PetFood[]) => {
        this.filteredPetFoods = data;
      },
      (error: any) => {
        console.error('Error searching by type:', error);
        this.filteredPetFoods = [];
      }
    );
  }
 
  searchById(): void {
    const id = Number(this.searchTerm.trim());
    if (!isNaN(id) && id > 0) {
      this.petFoodsService.searchById(id).subscribe(
        (data: PetFood) => {
          this.filteredPetFoods = [data];
        },
        (error: any) => {
          console.error('Error searching by ID:', error);
          this.filteredPetFoods = [];
        }
      );
    } else {
      console.warn('Invalid ID entered.');
      this.filteredPetFoods = [];
    }
  }
}
 