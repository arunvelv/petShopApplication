import { TestBed } from '@angular/core/testing';

import { AdminPetsCategoriesService } from './admin-pets-categories.service';

describe('AdminPetsCategoriesService', () => {
  let service: AdminPetsCategoriesService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(AdminPetsCategoriesService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
