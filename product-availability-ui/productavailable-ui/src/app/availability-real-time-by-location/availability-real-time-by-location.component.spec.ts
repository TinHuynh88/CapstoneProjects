import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AvailabilityRealTimeByLocationComponent } from './availability-real-time-by-location.component';

describe('AvailabilityRealTimeByLocationComponent', () => {
  let component: AvailabilityRealTimeByLocationComponent;
  let fixture: ComponentFixture<AvailabilityRealTimeByLocationComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AvailabilityRealTimeByLocationComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AvailabilityRealTimeByLocationComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
