import { TestBed } from '@angular/core/testing';

import { AdminGroomingServicesService } from './admin-grooming-services.service';

describe('AdminGroomingServicesService', () => {
  let service: AdminGroomingServicesService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(AdminGroomingServicesService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
