import { TestBed } from '@angular/core/testing';

import { AdminVaccinationsService } from './admin-vaccinations.service';

describe('AdminVaccinationsService', () => {
  let service: AdminVaccinationsService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(AdminVaccinationsService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
