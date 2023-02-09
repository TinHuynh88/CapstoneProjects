import { ProductavailabilityService } from './../productavailability.service';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-availability-of-item-all-locations',
  templateUrl: './availability-of-item-all-locations.component.html'
})
export class AvailabilityOfItemAllLocationsComponent implements OnInit {

  availabilityItems: Object[];
  p: number = 1;
  pageSize: number;
  itemsPPageArray : number[];
  isShowTable = false;

  constructor(private router: Router,
              private service: ProductavailabilityService) { 
      this.findAllAvailableByNameWithAllLocation(); 
      this.pageSize = 5;
      this.itemsPPageArray = [2, 5, 10, 20, 50, 100];
  }

  ngOnInit(): void {
    // this.findAllAvailableByNameWithAllLocation();
  }

  findAvailabilityItemAllLocation(name: string) : Promise<any> {
    return new Promise((resolve, reject) => {
      this.service.findAvailableByNameWithAllLocation(name).subscribe(data =>{
        this.availabilityItems = data;
        this.p = 1;
        this.isShowTable = true;
      });
    });
  }

  findAllAvailableByNameWithAllLocation(): Promise<any> {
    return new Promise((resolve, reject) => {
      this.service.findAllAvailableByNameWithAllLocation().subscribe(data =>{
        this.availabilityItems = data;
        this.p = 1;
        this.isShowTable = true;
      }); 
    });
  }

  itemsPerPageChange() {
    this.p =1;
  }
}
