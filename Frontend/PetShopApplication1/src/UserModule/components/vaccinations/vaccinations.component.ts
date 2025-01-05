import { Component, OnInit } from '@angular/core';
import { VaccinationsService } from '../../services/vaccinations/vaccinations.service';
import { Vaccination } from '../../../models/Vaccination';
import { CommonModule } from '@angular/common';


@Component({
  selector: 'app-vaccinations',
  imports: [CommonModule],
  templateUrl: './vaccinations.component.html',
  styleUrls: ['./vaccinations.component.css']
})
export class VaccinationsComponent implements OnInit {
  vaccinations: Vaccination[] = [];

  constructor(private vaccinationsService: VaccinationsService) {}

  ngOnInit(): void {
    this.getVaccinations();
  }

  getVaccinations(): void {
    this.vaccinationsService.getAllVaccinations().subscribe({
      next: (data) => {
        this.vaccinations = data;
      },
      error: (err) => {
        console.error('Error fetching vaccinations:', err);
      }
    });
  }
}