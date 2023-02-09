import { ComponentFixture, TestBed } from '@angular/core/testing';

import { NearestProductByNameAndZipComponent } from './nearest-product-by-name-and-zip.component';

describe('NearestProductByNameAndZipComponent', () => {
  let component: NearestProductByNameAndZipComponent;
  let fixture: ComponentFixture<NearestProductByNameAndZipComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ NearestProductByNameAndZipComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(NearestProductByNameAndZipComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
