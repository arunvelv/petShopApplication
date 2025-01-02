import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { AdminGroomingServicesService } from '../../services/AdminGroomingServices/admin-grooming-services.service';
 
@Component({
  selector: 'app-groomingservices',
  imports: [CommonModule, FormsModule],
  templateUrl: './admin-grooming-services.component.html',
  styleUrls: ['./admin-grooming-services.component.css']
})
export class GroomingServicesComponent implements OnInit {
  services: any[] = []; // All services
  newService: any = null; // New service being added
  editingIndex: number | null = null; // Index of the row being edited

  constructor(private adminGroomingService: AdminGroomingServicesService) {}

  ngOnInit(): void {
    this.loadServices();
  }

  // Fetch services from backend
  loadServices(): void {
    this.adminGroomingService.getAllServices().subscribe({
      next: (data) => {
        this.services = data;
      },
      error: (err) => {
        console.error('Error fetching services:', err);
        alert('Failed to load services.');
      }
    });
  }

  // Start adding a new service
  addServiceRow(): void {
    this.newService = { name: '', description: '', price: 0, available: true };
  }

  // Save new service to backend
  saveNewService(): void {
    if (!this.newService.name || !this.newService.description || this.newService.price <= 0) {
      alert('Please provide valid details for the service.');
      return;
    }

    this.adminGroomingService.addService(this.newService).subscribe({
      next: () => {
        this.loadServices();
        this.newService = null;
        alert('Service added successfully!');
      },
      error: (err) => {
        console.error('Error adding service:', err);
        alert('Failed to add service.');
      }
    });
  }

  // Cancel adding a new service
  cancelAddService(): void {
    this.newService = null;
  }

  // Start editing a service
  startEditing(index: number): void {
    this.editingIndex = index;
  }

  // Save the edited service
  saveEdit(index: number): void {
    const service = this.services[index];
    this.adminGroomingService.updateService(service).subscribe({
      next: () => {
        this.loadServices();
        this.editingIndex = null;
        alert('Service updated successfully!');
      },
      error: (err) => {
        console.error('Error updating service:', err);
        alert('Failed to update service.');
      }
    });
  }

  // Cancel editing
  cancelEdit(): void {
    this.editingIndex = null;
    this.loadServices(); // Reload services to discard unsaved changes
  }
}