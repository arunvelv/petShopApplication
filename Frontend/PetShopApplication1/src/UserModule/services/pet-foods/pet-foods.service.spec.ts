import { TestBed } from '@angular/core/testing';

import { PetFoodsService } from './pet-foods.service';

describe('PetFoodsService', () => {
  let service: PetFoodsService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(PetFoodsService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
