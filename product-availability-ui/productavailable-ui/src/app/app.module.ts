import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

import { AppComponent } from './app.component';
import { AvailabilityOfItemAllLocationsComponent } from './availability-of-item-all-locations/availability-of-item-all-locations.component';
import { AppRoutingModule } from './app-routing.module';
import { RouterModule } from '@angular/router';
import { NearestProductByNameAndZipComponent } from './nearest-product-by-name-and-zip/nearest-product-by-name-and-zip.component';
import { SortDirective } from './sort/sort.directive';
import { MatIconModule } from '@angular/material/icon';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { TooltipModule } from 'ng2-tooltip-directive';
import { NgxPaginationModule } from 'ngx-pagination';
import { AvailabilityRealTimeByLocationComponent } from './availability-real-time-by-location/availability-real-time-by-location.component';

@NgModule({
  declarations: [
    AppComponent,
    AvailabilityOfItemAllLocationsComponent,
    NearestProductByNameAndZipComponent,
    SortDirective,
    AvailabilityRealTimeByLocationComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    FormsModule,
    ReactiveFormsModule,
    AppRoutingModule,
    RouterModule,
    MatIconModule,
    BrowserAnimationsModule,
    TooltipModule,
    NgxPaginationModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
