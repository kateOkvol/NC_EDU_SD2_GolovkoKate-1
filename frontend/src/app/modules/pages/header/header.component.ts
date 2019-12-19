import {Component, OnInit} from '@angular/core';
import {Router} from '@angular/router';
import {UserSubscribeService} from '../../../services/user-subscribe.service';
import {UserModel} from '../../models/users/user.model';
import {StorageService} from '../../../services/storage.service';
import {AppService} from '../../../services/app.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {
  public isLogin: boolean = false;
  public userRole: string = null;

  constructor(private userRoleService: UserSubscribeService,
              private storageService: StorageService,
              private appService: AppService,
              private router: Router) {
  }

  ngOnInit() {
    const user = this.storageService.getCurrentUser();
    if (user != null) {
      this.isLogin = true;
      this.userRole = user.role;
    }

    this.userRoleService.getLoginStatus().subscribe(
      (status) => {
        this.isLogin = status;
      }
    );
    this.userRoleService.getRole().subscribe(
      (role) => {
        this.userRole = role;
      }
    );
  }

  public navigate(): void {
    if (this.userRole === 'ADMIN') {
      this.router.navigate(['/admin-polls']);
    }
    if (this.userRole === 'USER') {
      this.router.navigate(['/user-polls']);
    }
  }

  public logout(): void {
    this.appService.setUserLoggedIn(false);
    this.userRoleService.logout();
    localStorage.clear();
  }
}
