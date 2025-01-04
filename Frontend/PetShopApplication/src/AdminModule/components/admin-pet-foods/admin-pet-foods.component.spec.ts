import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { RouterTestingModule } from '@angular/router/testing';
import { of } from 'rxjs';
import { AdminPetFoodsComponent } from './admin-pet-foods.component';
import { AdminPetFoodsService } from '../../services/AdminPetFoods/admin-pet-foods.service';

describe('AdminPetFoodsComponent', () => {
  let component: AdminPetFoodsComponent;
  let fixture: ComponentFixture<AdminPetFoodsComponent>;
  let adminPetFoodsService: AdminPetFoodsService;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [AdminPetFoodsComponent], // Use declarations for component
      imports: [HttpClientTestingModule, RouterTestingModule],
      providers: [
        {
          provide: AdminPetFoodsService,
          useValue: {
            getAllPetFoods: () =>
              of([
                {
                  foodId: 1,
                  name: 'Test Food',
                  brand: 'Test Brand',
                  type: 'Test Type',
                  quantity: 10,
                  price: 10,
                },
              ]),
          },
        },
      ],
    }).compileComponents();

    fixture = TestBed.createComponent(AdminPetFoodsComponent);
    component = fixture.componentInstance;
    adminPetFoodsService = TestBed.inject(AdminPetFoodsService);
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should get pet foods', () => {
    component.ngOnInit(); // Ensure ngOnInit is called
    expect(component.petFoods.length).toBe(1); // Check the petFoods array
  });
});
