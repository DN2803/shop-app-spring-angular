import { AbstractControl, AsyncValidatorFn, ValidationErrors } from '@angular/forms';
import { map, catchError } from 'rxjs/operators';
import { Observable, of } from 'rxjs';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({ providedIn: 'root' })
export class UniqueValidatorService {
  constructor(private http: HttpClient) {}

  checkUsername(): AsyncValidatorFn {
    return (control: AbstractControl): Observable<ValidationErrors | null> => {
      return this.http.get<any>(`/api/users/check-username?username=${control.value}`).pipe(
        map(response => (response.exists ? { usernameTaken: true } : null)),
        catchError(() => of(null))
      );
    };
  }

  checkEmail(): AsyncValidatorFn {
    return (control: AbstractControl): Observable<ValidationErrors | null> => {
      return this.http.get<any>(`/api/users/check-email?email=${control.value}`).pipe(
        map(response => (response.exists ? { emailTaken: true } : null)),
        catchError(() => of(null))
      );
    };
  }
}
