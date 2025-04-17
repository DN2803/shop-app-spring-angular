import { inject, Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { ApiService } from './api.service';

@Injectable({providedIn: 'root'})
export class AuthService {
  private authEndpoint = `/auth`; // URL của API backend

  private apiService = inject(ApiService); // Sử dụng ApiService để gọi API

  constructor(private http: HttpClient) {}

  register(userData: any): Observable<any> {
    return this.apiService.callAPI<any>(
      `${this.authEndpoint}/register`, // Endpoint của API
      'POST', // Phương thức HTTP
      userData,
      true 
    );
  }

  login(userData: any): Observable<any> {
    return this.apiService.callAPI<any>(
      `${this.authEndpoint}/login`, // Endpoint của API
        'POST', // Phương thức HTTP
        userData,
        true 
    )
  }


  logout(): void {
    // Xử lý đăng xuất của người dùng
  }

  isLoggedIn(): boolean {
    // Kiểm tra trạng thái đăng nhập của người dùng
    return false; // Thay đổi thành true nếu người dùng đã đăng nhập
  }
}