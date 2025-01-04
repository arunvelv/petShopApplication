import { ComponentFixture, TestBed } from '@angular/core/testing';
import { AdminPetFoodsComponent } from './admin-pet-foods.component';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { RouterTestingModule } from '@angular/router/testing';
import { petfoodService } from '../../services/AdminPetFoods/admin-pet-foods.service';
import { of } from 'rxjs';
import { PetFoodsService } from '../../../UserModule/services/pet-foods/pet-foods.service';
 
 
describe('AdminPetFoodsComponent', () => {
  let component: AdminPetFoodsComponent;
 let fixture: ComponentFixture<AdminPetFoodsComponent>;
  //let petfoodService: petfoodService; // Correct type
 
  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AdminPetFoodsComponent ,HttpClientTestingModule, RouterTestingModule],
      providers: [
        {
          provide: petfoodService, // Correct provide token (service class)
          useValue: {
            getAllPetFoods: () => of([{ foodId: 1, name: 'Test Food', brand: 'Test Brand', type: 'Test Type', quantity: 10, price: 10 }])
          }
        }
      ]
    }).compileComponents();
 
    fixture = TestBed.createComponent(AdminPetFoodsComponent);
    component = fixture.componentInstance;
    //PetFoodsService = TestBed.inject(petfoodService); // Correct injection
    fixture.detectChanges();
  });
 
  it('should create', () => {
    expect(component).toBeTruthy();
  });
 
  it('should get pet foods', () => {
    component.ngOnInit();
    expect(component.petFoods.length).toBe(1);
  });
});
 