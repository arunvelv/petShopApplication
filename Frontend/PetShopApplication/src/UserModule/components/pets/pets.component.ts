import { Component, OnInit } from '@angular/core';
import { Pets } from '../../../models/Pets';
import { Router } from '@angular/router';
import { CommonModule } from '@angular/common';
import { PetService } from '../../services/pets/pets.service';

@Component({
  selector: 'app-pets',
  imports: [ CommonModule],
  templateUrl: './pets.component.html',
  styleUrl: './pets.component.css',
})
export class PetsComponent implements OnInit {
  pets: Pets[] = [];
  petId: number = 1;  

  constructor(private petService: PetService, private router: Router) {}
    ngOnInit(): void {
        this.getPets();
    }

  getPets(): void {
    this.petService.getAllPets().subscribe({
      next: (data) => {
        if (data.length > 0) {
          this.pets = data;
        } else {
          console.log('No pets found');
        }
      },
      error: (err) => {
        console.error('Error fetching pets:', err);
      },
    });
  }
  
}
