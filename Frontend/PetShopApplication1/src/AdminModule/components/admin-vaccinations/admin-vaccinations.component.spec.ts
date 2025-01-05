import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AdminVaccinationsComponent } from './admin-vaccinations.component';

describe('AdminVaccinationsComponent', () => {
  let component: AdminVaccinationsComponent;
  let fixture: ComponentFixture<AdminVaccinationsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AdminVaccinationsComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AdminVaccinationsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
