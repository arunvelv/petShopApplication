import { Component, OnInit } from '@angular/core';
import { PetFoodsService } from '../../services/pet-foods/pet-foods.service';
import { PetFood } from '../../../models/PetFood';
import { CommonModule } from '@angular/common';
@Component({
  selector: 'app-pet-foods',
  imports: [CommonModule],
  templateUrl: './pet-foods.component.html',
  styleUrl: './pet-foods.component.css'
})
export class PetFoodsComponent implements OnInit {
  petFoods: PetFood[] = [];

  constructor(private petFoodsService: PetFoodsService) {}

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
}