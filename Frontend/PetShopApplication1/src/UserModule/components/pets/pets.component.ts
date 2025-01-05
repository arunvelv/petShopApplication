import { Component, OnInit } from '@angular/core';
import { Pets } from '../../../models/Pets';
import { Router } from '@angular/router';
import { CommonModule } from '@angular/common';
import { PetService } from '../../services/pets/pets.service';

declare var Razorpay:any;

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

    payNow(price: number) {
      const amountInPaise = price;
      const RazorpayOptions = {
        key: 'rzp_test_zWhcqYLonnFntk',
        amount: amountInPaise*100,
        currency: 'INR',
        name: 'Pet Store',
        description: 'Sample Razorpay demo',
        image: 'https://i.imgur.com/FApqk3D.jpeg',
            prefill: {
              name: 'Pet Store',
              email: 'petstore@gmail.com',
              contact: '1234567890'
            },
            theme: {
              color: '#6466e3'
            },
            handler: (response: any) => {
             
              console.log('Payment successful. Payment ID:', response.razorpay_payment_id);
       
              // Navigate to home after successful payment
              // this.processPayment();
              this.router.navigate(['/']);
            },
            modal: {
              ondismiss: () => {
                console.log('Payment modal dismissed');
              }
            }
          };
       
          try {
            const rzp = new Razorpay(RazorpayOptions);
            rzp.open();
       
           
           
          } catch (error) {
            console.error('Error initializing Razorpay:', error);
          }
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
