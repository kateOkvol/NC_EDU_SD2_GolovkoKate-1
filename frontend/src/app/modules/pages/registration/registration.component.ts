import {Component, OnInit} from '@angular/core';
import {Router} from '@angular/router';
import {UserService} from '../../../services/http-requests/user.service';
import {FormControl, FormGroup, Validators} from '@angular/forms';
import {UserModel} from '../../models/users/user.model';
import {AccountComponent} from '../account/account.component';
import {HttpResponse} from '@angular/common/http';
import {UserSubscribeService} from '../../../services/user-subscribe.service';
import {StorageService} from '../../../services/storage.service';
import {SnackBarService} from '../../../services/snack-bar.service';
import {AppService} from '../../../services/app.service';

@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html'
})
export class RegistrationComponent implements OnInit {
  model: any = {};
  public formGroup: FormGroup;

  ngOnInit(): void {
    this.formGroup = new FormGroup({
      email: new FormControl('', Validators.compose([Validators.email, Validators.required])),
      username: new FormControl('', Validators.required),
      password: new FormControl('', Validators.required)
    });
  }

  constructor(
    private router: Router,
    private userService: UserService,
    private headerService: UserSubscribeService,
    private snackBar: SnackBarService,
    private storageService: StorageService,
    private appService: AppService) {
  }

  register() {
    console.log(this.formGroup);
    this.userService.create({
      email: this.formGroup.controls.email.value,
      password: this.formGroup.controls.password.value,
      userName: this.formGroup.controls.username.value,
    } as UserModel).subscribe(
      userData => {
        this.appService.setUserLoggedIn(true);
        this.headerService.login();
        this.storageService.setCurrentUser(userData as UserModel);
        this.router.navigate(['/']);
      },
      error => {
        if (error.status === 400) {
          this.snackBar.openSnackBar('The username is already in use.');
        } else {
          this.snackBar.openSnackBar('Some error occurred.');
        }
      });
  }
}
