import { TestBed } from '@angular/core/testing';

import { AdminPetsServicesService } from './admin-pets.service';

describe('AdminPetsServicesService', () => {
  let service: AdminPetsServicesService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(AdminPetsServicesService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
