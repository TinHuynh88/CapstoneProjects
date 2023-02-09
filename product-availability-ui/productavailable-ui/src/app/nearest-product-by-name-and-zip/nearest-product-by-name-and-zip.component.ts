import { ProductavailabilityService } from './../productavailability.service';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-nearest-product-by-name-and-zip',
  templateUrl: './nearest-product-by-name-and-zip.component.html'
  // ,  styleUrls: ['./nearest-product-by-name-and-zip.component.css']
})
export class NearestProductByNameAndZipComponent implements OnInit {

  items   : Object[];
  distance: number;
  distances = [5, 10, 15, 20, 25, 30];
  zipCode   : string;
  nameSearch: string;
  submitted = false;
  isShowTable = false;
  p : any;
  pageSize: number;
  itemsPPageArray : number[];

  constructor(private service: ProductavailabilityService) { 
    this.distance   = 10;
    this.zipCode    = "";
    this.nameSearch = "";
    this.pageSize = 5;
    this.itemsPPageArray = [2, 5, 10, 20, 50, 100];
  }  

  ngOnInit(): void {
  }

  findProductsByNameAndZipcodeNearest(){
    if(this.nameSearch == "")
      return;
    if(this.zipCode == ""){
      this.service.findAvailableByNameWithAllLocation(this.nameSearch).subscribe(data =>{
        this.items = data;
        this.p = 1;
        this.isShowTable = true;
      });
    } else{
      this.service.findProductsByNameAndZipcodeNearest(this.nameSearch,this.zipCode,this.distance).subscribe(data =>{
          this.items = data;
          this.p = 1;
          this.isShowTable = true;
      });
    }
  }
 
  itemsPerPageChange() {
    this.p =1;
  }
}
