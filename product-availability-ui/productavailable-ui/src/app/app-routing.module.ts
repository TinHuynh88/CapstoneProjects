import { AvailabilityRealTimeByLocationComponent } from './availability-real-time-by-location/availability-real-time-by-location.component';
import { NearestProductByNameAndZipComponent } from './nearest-product-by-name-and-zip/nearest-product-by-name-and-zip.component';
import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { Routes, RouterModule } from '@angular/router';
import { AvailabilityOfItemAllLocationsComponent } from './availability-of-item-all-locations/availability-of-item-all-locations.component';

const appRoutes: Routes = [
  { path: '', redirectTo: '/availabilityItems', pathMatch: 'full'},
  { path: 'availabilityItems', component: AvailabilityOfItemAllLocationsComponent},
  { path: 'items', component: NearestProductByNameAndZipComponent},
  { path: 'availabilityItemsByLocation', component: AvailabilityRealTimeByLocationComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(appRoutes)],
    exports: [RouterModule]
})
export class AppRoutingModule { }
