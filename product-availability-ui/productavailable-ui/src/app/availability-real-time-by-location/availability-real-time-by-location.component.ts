import { getTestBed } from '@angular/core/testing';
import { Location } from './../model/location';
import { ProductavailabilityService } from './../productavailability.service';
import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { isNumber } from 'util';
import Swal from 'sweetalert2'

@Component({
  selector: 'app-availability-real-time-by-location',
  templateUrl: './availability-real-time-by-location.component.html',
  styleUrls: ['./availability-real-time-by-location.component.css']
})
export class AvailabilityRealTimeByLocationComponent {

  availabilityItems: Object[];
  p: number = 1;
  pageSize: number;
  itemsPPageArray : number[];
  isShowTable = false;
  locationName: string ="";
  locationArray: Location[];
  defaulItemsQuanlity: number = 20;
  optionMaps = new Map();

  constructor(private router: Router,
    private service: ProductavailabilityService) { 
      this.getAllLocation();
      this.pageSize = 5;
      this.itemsPPageArray = [2, 5, 10, 20, 50, 100];
    }

  getAllLocation(): Promise<any> {
    return new Promise((resolve, reject) => {
      this.service.getAllLocation().subscribe(data =>{
        this.locationArray = data.reduce((accumalator, current) => {
          if(!accumalator.some(location => location.locationName === current.locationName)) {
            accumalator.push(current);
          }
          return accumalator;
      },[]);
      this.locationArray.sort((a,b) => a.locationName.localeCompare(b.locationName));
      }); 
    });
  }

  findAvailableByLocationName(locationName: string): Promise<any> {
    return new Promise((resolve, reject) => {
      this.service.findProductsByLocationName(locationName).subscribe(data =>{
        this.availabilityItems = data.sort((a,b) => a[7] - b[7]);
        this.p = 1;
        this.isShowTable = true;
        this.availabilityItems.forEach(item => {
          if(item[7] < this.defaulItemsQuanlity) {
            this.optionMaps.set(item[8],item);
            item[9] = this.defaulItemsQuanlity - item[7];
            item[10] = false;
          }
        });
        console.log("OptionMap: ", this.optionMaps);
      }); 
    });
  }

  locationChange() {    
    this.optionMaps.clear();
    this.findAvailableByLocationName(this.locationName);
  }

  itemsPerPageChange() {
    this.p =1;
  }

  isDisabledInputField(quanlity) {
    return (quanlity - this.defaulItemsQuanlity) > 0 ? true: false;
  }

  updateCheckedOption(item, event) {
    if(event.target.checked){
      this.setDisableInputField(item, false);
      this.optionMaps.set(item[8],item);
      console.log("OptionMap check: ", this.optionMaps);
    } else {
      this.setDisableInputField(item, true);
      this.optionMaps.delete(item[8]);
      console.log("OptionMap uncheck: ", this.optionMaps);
    }
  }

  inputFieldChange(item, event) {
    if(event.target.value) {
      this.setAdditionQuanlity(item, parseInt(event.target.value));
    }
  }

  setDisableInputField(item, isDisable) {
    this.availabilityItems.forEach(i => {
      if(i[8] === item[8]) {
        i[10] = isDisable;
      }
    });
  }

  setAdditionQuanlity(item, quanlity) {
    this.availabilityItems.forEach(i => {
      if(i[8] === item[8]) {
        i[9] = quanlity;
      }
    });
    this.optionMaps.set(item[8], item);
    // console.log("Input change: ", this.optionMaps);
  }

  submitAllChecedkBox() {
    Swal.fire({
      title: 'Error!',
      text: 'Do you want to continue',
      icon: 'error',
      confirmButtonText: 'Cool'
    })
    var productList = Array.from(this.optionMaps.values());
    console.log("submit all: ", productList);
    return new Promise((resolve, reject) => {
      this.service.updateAllProduct(productList).subscribe(data =>{
        console.log("Before Clear OptionMap: ", this.optionMaps);
        this.optionMaps.clear();
        this.availabilityItems = data.sort((a,b) => a[7] - b[7]);
        this.p = 1;
        this.isShowTable = true;
        this.availabilityItems.forEach(item => {
          if(item[7] < this.defaulItemsQuanlity) {
            this.optionMaps.set(item[8],item);
            item[9] = this.defaulItemsQuanlity - item[7];
            item[10] = false;
          }
        });
        
        
        console.log("After Clear OptionMap: ", this.optionMaps);
      }); 
    });
  }

  submitOneCheckedkBox(item) {
    console.log("submit One: ", item);
    if(confirm("Are you sure to update")) {
      return new Promise((resolve, reject) => {
        this.service.updateOneProduct(item).subscribe(data =>{
          // console.log("Data: ", data);
          this.availabilityItems.forEach(i => {
            if(i[8] === data[8]) {
              for(let a = 0; a < data.length; a++) {
                i[a] = data[a];
              }          
              if(i[7] < this.defaulItemsQuanlity) {
                i[9] = this.defaulItemsQuanlity - i[7];
                i[10] = false;
                this.optionMaps.set(i[8], i);
              }
              item = i;
            }
          });
          if(item[10])
            this.optionMaps.delete(item[8]);
          // console.log("OptionMap: ", this.optionMaps);
          // console.log("Product: ", this.availabilityItems);
        });
      });
    }
  }

}
