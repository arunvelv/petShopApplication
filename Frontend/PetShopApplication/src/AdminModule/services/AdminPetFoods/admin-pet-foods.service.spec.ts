import { TestBed } from '@angular/core/testing';

import { petfoodService } from './admin-pet-foods.service';

describe('AdminPetFoodsService', () => {
  let service: petfoodService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(petfoodService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
