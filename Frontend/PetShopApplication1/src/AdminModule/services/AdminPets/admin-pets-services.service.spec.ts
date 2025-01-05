import { TestBed } from '@angular/core/testing';
import { AdminPetsService } from './admin-pets.service';

describe('AdminPetsServicesService', () => {
  let service: AdminPetsService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(AdminPetsService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
