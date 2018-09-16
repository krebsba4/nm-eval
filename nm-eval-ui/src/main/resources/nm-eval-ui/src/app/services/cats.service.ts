import { Injectable } from '@angular/core';
import { HttpClient, HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';

@Injectable({
  providedIn: 'root'
})
export class CatsService {

  constructor(private http: HttpClient) { }

  getCats(): Observable<any> {
    let searchParams = new HttpParams();
    searchParams = searchParams.append('numberOfPictures', '25');
    return this.http.get('//localhost:8080/getCats', {params: searchParams});
  }
}
