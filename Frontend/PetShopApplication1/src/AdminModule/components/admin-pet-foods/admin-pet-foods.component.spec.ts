import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AdminPetFoodsComponent } from './admin-pet-foods.component';

describe('AdminPetFoodsComponent', () => {
  let component: AdminPetFoodsComponent;
  let fixture: ComponentFixture<AdminPetFoodsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AdminPetFoodsComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AdminPetFoodsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
