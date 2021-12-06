import { Injectable } from '@angular/core';

import { retry, catchError } from 'rxjs/operators';
import { Observable, throwError } from 'rxjs';
import { HttpClient, HttpContext, HttpHeaders } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class JobService {
postID:any;
httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'text/plain' }),
  
  
};
  constructor(private httpClient: HttpClient) { }

  // endpoint:string;
  // Rest api that creates jobs
  endpoint = "http://localhost:8080/job-management/jobs";
  
  addJob(): Observable<any> {
    return this.httpClient.post<any>(this.endpoint, { title: 'Angular POST Request' }, this.httpOptions)
  }

  getJobByID(jobId:string): Observable<any> {
    return this.httpClient.get<any>(this.endpoint+"/"+jobId, this.httpOptions)
  }

  getAllJobs(): Observable<any> {
    return this.httpClient.get<any>(this.endpoint, this.httpOptions)
  }

}
