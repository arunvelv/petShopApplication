import { TestBed } from '@angular/core/testing';

import { GroomingServicesService } from './grooming-services.service';

describe('GroomingServicesService', () => {
  let service: GroomingServicesService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(GroomingServicesService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
