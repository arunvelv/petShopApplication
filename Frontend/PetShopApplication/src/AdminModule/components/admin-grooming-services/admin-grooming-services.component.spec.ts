import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AdminGroomingServicesComponent } from './admin-grooming-services.component';

describe('AdminGroomingServicesComponent', () => {
  let component: AdminGroomingServicesComponent;
  let fixture: ComponentFixture<AdminGroomingServicesComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AdminGroomingServicesComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AdminGroomingServicesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
