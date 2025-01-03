// import { Component } from '@angular/core';

// @Component({
//   selector: 'app-admin-grooming-services',
//   imports: [],
//   templateUrl: './admin-grooming-services.component.html',
//   styleUrl: './admin-grooming-services.component.css'
// })
// export class AdminGroomingServicesComponent {

// }


import { Component, OnInit } from '@angular/core';
import { AdminGroomingServicesService } from '../../services/AdminGroomingServices/admin-grooming-services.service';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

@Component({
    selector: 'app-admin-grooming-services',
    imports: [CommonModule,FormsModule],
    templateUrl: './admin-grooming-services.component.html',
    styleUrls: ['./admin-grooming-services.component.css']
  })
export class AdminGroomingServicesComponent implements OnInit {
  services: any[] = []; // To display filtered services
  originalServices: any[] = []; // To store the original list of services
  selectedService: any = null; // To hold the service being updated
  newService: any = { name: '', description: '', price: 0, available: true }; // For adding new service
  showAddForm = false;
  showUpdateForm = false;
  showServiceList = true;
  serviceIdToUpdate: number = 0;

  constructor(private admingroomingservicesService: AdminGroomingServicesService) {}

  ngOnInit(): void {
    this.loadServices();
  }

  // Fetch all services from the backend
  loadServices(): void {
    this.admingroomingservicesService.getAllServices().subscribe({
      next: (data) => {
        this.services = data;
        this.originalServices = [...data];
      },
      error: (err) => {
        console.error('Error fetching services:', err);
        alert('An error occurred while fetching grooming services. Please try again later.');
      }
    });
  }

  fetchServiceDetailsById(): void {
    if (!this.selectedService || !this.selectedService.serviceId) {
      alert('Please enter a valid Service ID.');
      return;
    }
  
    console.log('Fetching details for Service ID:', this.selectedService.serviceId);
  
    this.admingroomingservicesService.getServiceById(this.selectedService.serviceId).subscribe({
      next: (data) => {
        console.log('Service fetched successfully:', data); // Debugging log
        if (data) {
          this.selectedService = data; // Populate the form with fetched data
        } else {
          alert('No service found with the entered ID.');
        }
      },
      error: (err) => {
        console.error('Error fetching service by ID:', err); // Debugging log
        alert('Error fetching service details. Please try again.');
      }
    });
  }
  

  // Search services by name or description
  onSearch(event: Event): void {
    const searchTerm = (event.target as HTMLInputElement).value.toLowerCase();
    this.services = this.originalServices.filter((service) =>
      service.name.toLowerCase().includes(searchTerm) ||
      service.description.toLowerCase().includes(searchTerm)
    );
    if (this.services.length === 0) {
      alert('No services found matching your search criteria.');
    }
  }

  // Start adding a new service
  startAddService(): void {
    this.showAddForm = true;
    this.showServiceList = false;
  }

  // Add a new service
  addService(): void {
    if (
      !this.newService.name ||
      !this.newService.description ||
      this.newService.price <= 0
    ) {
      alert('Please provide valid service details(name, description, and price).');
      return;
    }

    this.admingroomingservicesService.addService(this.newService).subscribe({
      next: (addedService) => {
        this.loadServices(); // Refresh the service list
        this.newService = { name: '', description: '', price: 0, available: true }; // Reset the form
        this.showAddForm = false; // Hide the add form
        this.showServiceList = true; // Show the service list again
        alert('Grooming service added successfully!');
        console.log('Service added:', addedService);
      },
      error: (err) => {
        console.error('Error adding service:', err);
        // alert('Error adding grooming service. Please try again.');
      }
    });
  }

  // Cancel adding a new service
  cancelAddService(): void {
    this.showAddForm = false;
    this.showServiceList = true;
    this.newService = { name: '', description: '', price: 0, available: true };
    // alert('Adding service cancelled.');
  }

  // Open the update form for a specific service
  startUpdate(service: any): void {
    this.selectedService = { ...service };
  }

  updateService(): void {
    if (!this.selectedService) return;

    if (!this.selectedService.name || !this.selectedService.description || this.selectedService.price <= 0) {
      alert('Please provide valid service details (name, description, and price).');
      return;
    }
    // Call the update service method in the service
    this.admingroomingservicesService.updateService(this.selectedService).subscribe({
      next: (updatedData) => {
        // Immediately reflect the changes on the local state
        const updatedServices = this.services.map(service =>
          service.serviceId === this.selectedService.serviceId ? updatedData : service
        );
  
        this.services = updatedServices;
        this.originalServices = updatedServices; // Update the original services list
  
        // Reset the selectedService after update
         this.loadServices(); 
        this.selectedService = null;
        alert('Grooming service updated successfully!');
        console.log('Service updated:', updatedData);
      },
      error: (err) => {
        console.error('Error updating service:', err);
        alert('Error updating grooming service. Please try again.');
      }
    });
  }
  
  // Cancel the update operation
  cancelUpdate(): void {
    this.selectedService = null;
  }
  
  // Filter to show only available services
  filterAvailable(): void {
    this.services = this.originalServices.filter((service) => service.available);
  }

  // Filter to show only unavailable services
  filterUnavailable(): void {
    this.services = this.originalServices.filter((service) => !service.available);
  }

  // Show all services
  showAll(): void {
    this.services = [...this.originalServices];
  }
}
