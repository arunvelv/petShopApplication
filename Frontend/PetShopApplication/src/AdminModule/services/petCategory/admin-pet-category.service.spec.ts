import { TestBed } from '@angular/core/testing';

import { AdminPetCategoryService } from './admin-pet-category.service';

describe('AdminPetCategoryService', () => {
  let service: AdminPetCategoryService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(AdminPetCategoryService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
