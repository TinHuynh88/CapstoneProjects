import { TestBed } from '@angular/core/testing';

import { ProductavailabilityService } from './productavailability.service';

describe('ProductavailabilityService', () => {
  let service: ProductavailabilityService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ProductavailabilityService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
