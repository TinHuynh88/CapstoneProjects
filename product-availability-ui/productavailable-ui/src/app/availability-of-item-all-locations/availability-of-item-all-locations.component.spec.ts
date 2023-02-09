import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AvailabilityOfItemAllLocationsComponent } from './availability-of-item-all-locations.component';

describe('AvailabilityOfItemAllLocationsComponent', () => {
  let component: AvailabilityOfItemAllLocationsComponent;
  let fixture: ComponentFixture<AvailabilityOfItemAllLocationsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AvailabilityOfItemAllLocationsComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AvailabilityOfItemAllLocationsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
