import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Router } from '@angular/router';
import { Observable, throwError, from } from 'rxjs';
import { catchError, map } from 'rxjs/operators';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class ApiService {
  private baseUrl = environment.backendUrl;
//   private apiKey = localStorage.getItem('apiKey');

  constructor(
    private http: HttpClient,
    private router: Router
  ) {}

  callAPI<T>(
    endpoint: string,
    method: 'GET' | 'POST' | 'PUT' | 'DELETE',
    body?: any,
    withCredentials: boolean = false,
    token?: string,
    params?: any
  ): Observable<T> {
    let headers = new HttpHeaders({
      'Content-Type': 'application/json',
    //   ...(this.apiKey ? { 'x-api-key': this.apiKey } : {}),
      ...(token ? { Authorization: `Bearer ${token}` } : {})
    });

    let httpParams = new HttpParams();
    if (params) {
      Object.keys(params).forEach(key => {
        httpParams = httpParams.set(key, params[key]);
      });
    }

    return this.http.request<T>(method, `${this.baseUrl}${endpoint}`, {
      body,
      headers,
      params: httpParams,
      withCredentials
    }).pipe(
      map((response: any) => {
        return response;
      }),
      catchError((error) => {
        console.error('API Error:', error);
        return throwError(() => error);
      })
    );
    // .pipe(
    //   catchError(error => {
    //     if (error.status === 400 && error.error?.error === 'Signature has expired') {
    //       const password = prompt('Phiên đăng nhập đã hết hạn. Vui lòng nhập lại mật khẩu:');
    //       if (password) {
    //         return this.http.post(`${this.baseUrl}/auth/login/validate/password`, { password }, {
    //           withCredentials: true,
    //           headers: new HttpHeaders({
    //             Authorization: `Bearer ${localStorage.getItem('refresh_token') || ''}`
    //           })
    //         }).pipe(
    //           switchMap(() => {
    //             // Gọi lại API gốc sau khi xác thực lại
    //             return this.callAPI<T>(endpoint, method, body, withCredentials, token, params);
    //           }),
    //           catchError(() => {
    //             this.router.navigate(['/']);
    //             return throwError(() => error);
    //           })
    //         );
    //       } else {
    //         this.router.navigate(['/']);
    //         return throwError(() => error);
    //       }
    //     }

    //     return throwError(() => error);
    //   })
    // );
  }
}
