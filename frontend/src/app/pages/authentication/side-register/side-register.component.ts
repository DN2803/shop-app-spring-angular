import { Component } from '@angular/core';
import { CoreService } from 'src/app/core/services/core.service';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { RouterModule } from '@angular/router';
import { MaterialModule } from 'src/app/material.module';
import { CommonModule } from '@angular/common';

import { AuthService } from 'src/app/core/services/auth.service';
@Component({
  selector: 'app-side-register',
  imports: [CommonModule, RouterModule, MaterialModule, FormsModule, ReactiveFormsModule],
  templateUrl: './side-register.component.html',
})
export class AppSideRegisterComponent {
  options = this.settings.getOptions();

  constructor(
    private settings: CoreService, 
    private router: Router,
    private authService: AuthService
) {}

  form = new FormGroup({
    fullname: new FormControl('', [Validators.required, Validators.minLength(6), Validators.maxLength(40)]),
    username: new FormControl('', [Validators.required, Validators.minLength(6), Validators.maxLength(40)]),
    email: new FormControl('', [Validators.required, Validators.email]),
    password: new FormControl('', [Validators.required]),
  });

  get f() {
    return this.form.controls;
  }

  submit() {
    // 
    console.log("hihi")
    console.log(this.form.valid);
    console.log(this.form.value);
    if (this.form.invalid) {
      this.form.markAllAsTouched(); // để hiển thị lỗi tất cả input
      return;
    }
    const payload = this.form.value; // lấy dữ liệu từ form
    console.log(payload); // in ra dữ liệu
    this.authService.register(payload).subscribe({
      next: (res) => {
        console.log('Register successfully:', res);
        this.router.navigate(['/login']); // điều hướng sang login
      },
      error: (err) => {
        console.error('Register failed:', err);
      }
    });
  }
}
