import { Component } from '@angular/core';
import { AdminVaccinationsService } from '../../services/AdminVaccicnations/admin-vaccinations.service';
import { CommonModule } from '@angular/common';
import{FormsModule} from '@angular/forms';

@Component({
  selector: 'app-admin-vaccinations',
  imports: [CommonModule,FormsModule],
  templateUrl: './admin-vaccinations.component.html',
  styleUrl: './admin-vaccinations.component.css'
})
export class AdminVaccinationsComponent {
  vaccinations: any[] = [];
  router: any;

  constructor(private vaccinationService: AdminVaccinationsService) {}
 originalVaccinations: any[] = [];
 selectedVaccination: any = null;
 newVaccination: any = { name: '', description: '', price: 0, available: true }; // For adding new vaccination
 updatedVaccination: any = { name: '', description: '', price: 0, available: true }; // For updating vaccination
  showAddForm = false;
  showVaccinationList = true;
  ngOnInit(): void {
    this.loadVaccinations();
  }

  loadVaccinations(): void {
    this.vaccinationService.getAllVaccinations().subscribe({
      next: (data) => {
        this.vaccinations = data;
        this.originalVaccinations=[...data];
      },
      error: (err) => {
        console.error('Error fetching vaccinations:', err);
      }
    });
  }
  onSearch(event: Event): void {
    const searchTerm = (event.target as HTMLInputElement).value.toLowerCase();
    this.vaccinations = this.originalVaccinations.filter((v) =>
      v.vaccinationId.toString().toLowerCase().includes(searchTerm)
    );
  }
  startAddVaccination(): void {
    this.showAddForm = true;
  }
  addVaccination(): void {
    if (!this.newVaccination.name || !this.newVaccination.description || this.newVaccination.price <= 0) {
      alert("Please provide valid vaccination details.");
      return;
    }
  
    this.vaccinationService.addVaccination(this.newVaccination).subscribe({
      next: (response: string) => {
        console.log('Response from server:', response); // Log the raw response
  
        // Refresh the list after adding
        this.loadVaccinations();
        this.newVaccination = { name: '', description: '', price: 0, available: true }; // Reset the form
        this.showAddForm = false; // Hide the add form
        this.showVaccinationList = true; // Show the vaccination list again
        alert("Vaccination added successfully!");
      },
      error: (err) => {
        console.error('Error adding vaccination:', err);
        alert(`Error adding vaccination: ${err.message}`);
      },
    });
  }
  
  cancelAddVaccination(): void{
    this.showAddForm = false;
    this.showVaccinationList = true;
    this.newVaccination ={name:'',description:'',price:'',available: true};
  }
  startUpdate(vaccination: any):void {
    this.selectedVaccination = {...vaccination };
  }
  updateVaccination(): void {
    if (!this.selectedVaccination) return; // Exit if no vaccination is selected
  
    this.vaccinationService.updateVaccination(this.selectedVaccination).subscribe({
      next: (updatedData: any) => {
        // Reload vaccinations after update
        this.loadVaccinations();
  
        this.selectedVaccination = null; // Clear the selected vaccination
        alert('Vaccination details updated successfully!');
        console.log('Updated vaccination:', updatedData); // Log success message
      },
      error: (err: any) => {
        console.error('Error updating vaccination:', err); // Handle errors
      },
    });
  }
  
  
  
  
  cancelUpdate(): void {
    this.selectedVaccination = null;
    }
  goToUpdatePage(vaccinationId: number): void {
    this.router.navigate(['/update-vaccination', vaccinationId]);
  }

  filterAvailable():void {
    this.vaccinations = this.originalVaccinations.filter((v)=>v.available);
  }
  filterUnavailable(): void {
    this.vaccinations = this.originalVaccinations.filter((v)=> !v.available);
  }
  showAll(): void {
    this.vaccinations = [...this.originalVaccinations];
  }
  viewDetails(id: string): void {
    console.log('Viewing details for vaccination ID:', id);
  }

  confirmDelete(id: string): void {
    console.log('Deleting vaccination ID:', id);
  }
}
