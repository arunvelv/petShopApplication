import { Component, OnInit } from '@angular/core';
import { AdminVaccinationsService } from '../../services/AdminVaccicnations/admin-vaccinations.service';
import { CommonModule } from '@angular/common';
import{FormsModule} from '@angular/forms';

@Component({
  selector: 'app-admin-vaccinations',
  imports: [CommonModule, FormsModule],
  templateUrl: './admin-vaccinations.component.html',
  styleUrl: './admin-vaccinations.component.css'
})
export class AdminVaccinationsComponent implements OnInit {
  vaccinations: any[] = [];
  originalVaccinations: any[] = [];
  editedVaccinationId: number | null = null;
  newVaccination: any = { name: '', description: '', price: 0, available: true };
  searchQuery: string = '';

  constructor(private adminVaccinationService: AdminVaccinationsService) {}

  ngOnInit(): void {
    this.loadVaccinations();
  }

  loadVaccinations(): void {
    this.adminVaccinationService.getAllVaccinations().subscribe({
      next: (data) => {
        this.vaccinations = data;
        this.originalVaccinations = [...data];
      },
      error: (err) => {
        console.error('Error fetching vaccinations:', err);
      },
    });
  }

  onSearch(): void {
    const query = this.searchQuery.toLowerCase();
    this.vaccinations = this.originalVaccinations.filter((v) =>
      v.name.toLowerCase().includes(query) ||
      v.description.toLowerCase().includes(query)
    );
  }

  startEdit(id: number): void {
    this.editedVaccinationId = id;
  }

  saveEdit(vaccination: any): void {
    this.adminVaccinationService.updateVaccination(vaccination).subscribe({
      next: () => {
        this.editedVaccinationId = null;
        this.loadVaccinations();
        alert('Vaccination updated successfully!');
      },
      error: (err) => {
        console.error('Error updating vaccination:', err);
        alert('Failed to update vaccination.');
      },
    });
  }

  cancelEdit(): void {
    this.editedVaccinationId = null;
    this.loadVaccinations();
  }

  addVaccination(): void {
    if (!this.newVaccination.name || !this.newVaccination.description || this.newVaccination.price <= 0) {
      alert('Please fill in all the fields.');
      return;
    }

    this.adminVaccinationService.addVaccination(this.newVaccination).subscribe({
      next: () => {
        this.newVaccination = { name: '', description: '', price: 0, available: true };
        this.loadVaccinations();
        alert('New vaccination added!');
      },
      error: (err) => {
        console.error('Error adding vaccination:', err);
        alert('Failed to add vaccination.');
      },
    });
  }
}