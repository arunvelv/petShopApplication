import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
@Injectable({
  providedIn: 'root'
})
export class AdminGroomingServicesService {
  URL:string ="http://localhost:9999/"
 
  constructor(private httpclient : HttpClient) { }
 
  getAllServices():Observable<any>{
 
    const data =this.httpclient.get(this.URL+"api/v1/services");
 
    return data;
 
  }
 
  getServiceById(serviceId: number): Observable<any> {
    return this.httpclient.get(`${this.URL}api/v1/services/${serviceId}`);
  }
 
  updateService(service: any): Observable<any>
{
  const data = this.httpclient.put(`${this.URL}api/v1/services/update/${service.serviceId}`,service,{
    responseType:'text'
    });
 
  return data;
}
 
addService(service:any): Observable<any> {
  const data = this.httpclient.post(this.URL + "api/v1/services/add",service,{
    responseType:'text'
  });
  return data;
}
}
 
 
 
 
 
