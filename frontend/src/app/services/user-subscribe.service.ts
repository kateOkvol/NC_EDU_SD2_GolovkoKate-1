import {Injectable} from '@angular/core';
import {ReplaySubject, Subject} from 'rxjs';
import {StorageService} from './storage.service';

@Injectable({
  providedIn: 'root'
})
export class UserSubscribeService {
  private isLogin: Subject<boolean> = new ReplaySubject(1);
  private role: Subject<string> = new Subject();

  constructor(private storageService: StorageService) {
  }

  public getLoginStatus() {
    return this.isLogin;
  }

  public login() {
    this.isLogin.next(true);
    this.role.next(this.storageService.getCurrentUser().role);
  }

  public logout() {
    this.isLogin.next(false);
    this.role.next(null);
  }

  public getRole() {
    return this.role;
  }

  public setRoleUser() {
    this.role.next('USER');
  }

  public setRoleAdmin() {
    this.role.next('ADMIN');
  }
}
