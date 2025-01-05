import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { GroomingServicesService } from '../../services/grooming-services/grooming-services.service';
import { GroomingService } from '../../../models/Grooming-Services';

@Component({
  selector: 'app-grooming-services',
  imports: [CommonModule],
  templateUrl: './grooming-services.component.html',
  styleUrls: ['./grooming-services.component.css']
})
export class GroomingServicesComponent implements OnInit {
  groomingServices: GroomingService[] = [];

  constructor(private groomingServicesService: GroomingServicesService) {}

  ngOnInit(): void {
    this.getGroomingServices();
  }

  getGroomingServices(): void {
    this.groomingServicesService.getAllGroomingServices().subscribe({
      next: (data) => {
        this.groomingServices = data;
      },
      error: (err) => {
        console.error('Error fetching grooming services:', err);
      }
    });
  }
}