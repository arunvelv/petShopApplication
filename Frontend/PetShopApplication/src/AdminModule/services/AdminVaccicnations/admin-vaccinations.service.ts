import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
@Injectable({
  providedIn: 'root'
})
export class AdminVaccinationsService {

  URL:string ="http://localhost:9999/"
 
  constructor(private httpclient : HttpClient) { }
 
  getAllVaccinations():Observable<any>{
 
    const data =this.httpclient.get(this.URL+"api/v1/vaccinations");
 
    return data;
 
  }
  updateVaccination(vaccination: any): Observable<any>
{
  const data = this.httpclient.put(`${this.URL}api/v1/vaccinations/update/${vaccination.vaccinationId}`,vaccination,{
    responseType: 'text'
  });
  return data;
}
addVaccination(vaccination:any): Observable<any>
 {
  const data = this.httpclient.post(`${this.URL}api/v1/vaccinations/add`,vaccination,{
    responseType:'text'
  });
  return data;
}
}
