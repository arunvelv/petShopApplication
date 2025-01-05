import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { PetFoodsComponent } from './pet-foods.component';
import { RouterTestingModule } from '@angular/router/testing';
import { PetFoodsService } from '../../services/pet-foods/pet-foods.service';
import { of } from 'rxjs';

describe('PetFoodsComponent', () => {
  let component: PetFoodsComponent;
  let fixture: ComponentFixture<PetFoodsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [HttpClientTestingModule, RouterTestingModule],
      declarations: [PetFoodsComponent],
      providers: [
        {
          provide: PetFoodsService,
          useValue: {
            getAllPetFoods: () => of([
              { foodId: 1, name: 'Test Food', brand: 'Test Brand', type: 'Test Type', quantity: 10, price: 10 }
            ])
          }
        }
      ]
    }).compileComponents();

    fixture = TestBed.createComponent(PetFoodsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create the component', () => {
    expect(component).toBeTruthy();
  });

  it('should get pet foods on initialization', () => {
    component.ngOnInit();
    expect(component.petFoods.length).toBe(1);
    expect(component.petFoods[0].name).toBe('Test Food');
  });
});
