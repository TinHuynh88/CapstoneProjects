import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Location } from './model/location';
@Injectable({
  providedIn: 'root'
})
export class ProductavailabilityService {

  constructor(private http: HttpClient) { }

  findAvailableByNameWithAllLocation(name: string): Observable<Object[]> {
    return this.http.get<Object[]>('/balance-service/availabilityItem/'+name);
  }

  findAllAvailableByNameWithAllLocation(): Observable<Object[]> {
    return this.http.get<Object[]>('/balance-service/availabilityItem');
  }

  findProductsByNameAndZipcodeNearest(name:string, zipcode:string, radius: number): Observable<Object[]> {
   return this.http.get<Object[]>('/balance-service/items/item='+name+'&zip='+zipcode+'&radius='+radius);
 }

  findProductsByLocationName(locationName:string): Observable<Object[]> {
    return this.http.get<Object[]>('/balance-service/items/locationName='+locationName);
  }

  getAllLocation(): Observable<Location[]>{
    return this.http.get<Location[]>('/balance-service/getAllLocation');
  }

  updateOneProduct(product: Object): Observable<Object[]>{
    return this.http.put<Object[]>('/balance-service/updateOneProduct', product);
  }

  updateAllProduct(productList): Observable<Object[]>{
    return this.http.put<Object[]>('/balance-service/updateAllProduct', productList);
  }
}
