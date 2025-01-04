import { Component, OnInit } from '@angular/core';
import { PetFoodsService } from '../../services/pet-foods/pet-foods.service';
import { PetFood } from '../../../models/PetFood';
import { CommonModule } from '@angular/common';
import { Router } from '@angular/router';

declare var Razorpay:any;

@Component({
  selector: 'app-pet-foods',
  imports: [ CommonModule],
  templateUrl: './pet-foods.component.html',
  styleUrl: './pet-foods.component.css'
})
export class PetFoodsComponent implements OnInit {
  petFoods: PetFood[] = [];

  constructor(private petFoodsService: PetFoodsService, private router: Router) {}

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

}