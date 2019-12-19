import {Component, OnInit} from '@angular/core';
import {Router} from '@angular/router';
import {UserService} from '../../../services/http-requests/user.service';
import {FormControl, FormGroup, Validators} from '@angular/forms';
import {UserModel} from '../../models/users/user.model';
import {UserSubscribeService} from '../../../services/user-subscribe.service';
import {StorageService} from '../../../services/storage.service';
import {SnackBarService} from '../../../services/snack-bar.service';
import {AppService} from '../../../services/app.service';

@Component({
  selector: 'app-registration',
  templateUrl: './login.component.html'
})
export class LoginComponent implements OnInit {
  model: any = {};
  public loginFormGroup: FormGroup;

  ngOnInit(): void {
    this.loginFormGroup = new FormGroup({
      username: new FormControl('', Validators.required),
      password: new FormControl('', Validators.required)
    });
  }

  constructor(
    private router: Router,
    private userService: UserService,
    private storageService: StorageService,
    private headerService: UserSubscribeService,
    private snackBar: SnackBarService,
    private appService: AppService) {
  }

  login() {
    console.log(this.loginFormGroup);
    this.userService.generateToken({
      password: this.loginFormGroup.controls.password.value,
      userName: this.loginFormGroup.controls.username.value,
    } as UserModel).subscribe(
      tokenObj => {
        this.storageService.setToken((tokenObj as any).token);
        this.userService.getCurrentUser().subscribe(currentUser => {
          this.storageService.setCurrentUser(currentUser as UserModel);
          this.headerService.login();
          this.appService.setUserLoggedIn(true);
          this.router.navigate(['/']);
        }, () => this.router.navigate(['/non-found'])
        );
      },
      () => this.snackBar.openSnackBar('Wrong username or password!')
    );
  }
}
