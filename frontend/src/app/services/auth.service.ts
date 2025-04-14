import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private loggedIn = new BehaviorSubject<boolean>(true);
  private userRole = new BehaviorSubject<string | null>("user");

  isLoggedIn$ = this.loggedIn.asObservable();
  userRole$ = this.userRole.asObservable();

  constructor() {}

  login(role: 'user' | 'expert') {
    this.loggedIn.next(true);
    this.userRole.next(role);
  }

  logout() {
    this.loggedIn.next(false);
    this.userRole.next(null);
  }

  isLoggedIn(): boolean {
    return this.loggedIn.value;
  }

  isExpert(): boolean {
    return this.userRole.value === 'expert';
  }
}