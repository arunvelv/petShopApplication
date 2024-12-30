import { TestBed } from '@angular/core/testing';

import { AdminPetFoodsService } from './admin-pet-foods.service';

describe('AdminPetFoodsService', () => {
  let service: AdminPetFoodsService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(AdminPetFoodsService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
