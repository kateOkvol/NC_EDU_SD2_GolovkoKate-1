import {Component, OnInit} from '@angular/core';
import {Router} from '@angular/router';
import {FormControl, FormGroup, Validators} from '@angular/forms';
import {UserModel} from '../../models/users/user.model';
import {UserService} from '../../../services/http-requests/user.service';
import {HttpResponse} from '@angular/common/http';
import {UserSubscribeService} from '../../../services/user-subscribe.service';
import {StorageService} from '../../../services/storage.service';
import {SnackBarService} from '../../../services/snack-bar.service';

@Component({
  selector: 'app-account',
  templateUrl: './account.component.html'
})
export class AccountComponent implements OnInit {
  public model: any = {};
  public formGroup: FormGroup;

  ngOnInit() {
    this.model = this.storageService.getCurrentUser();
    this.formGroup = new FormGroup({
      firstName: new FormControl(this.model.firstName, Validators.maxLength(100)),
      middleName: new FormControl(this.model.middleName, Validators.maxLength(100)),
      lastName: new FormControl(this.model.lastName, Validators.maxLength(100)),
      gender: new FormControl(this.model.gender),
      birthDate: new FormControl(this.model.birthDate)
    });
  }

  constructor(
    private router: Router,
    private userService: UserService,
    private snackBar: SnackBarService,
    private storageService: StorageService) {
  }


  updateUser() {
    this.model.firstName = this.formGroup.controls.firstName.value;
    this.model.middleName = this.formGroup.controls.middleName.value;
    this.model.lastName = this.formGroup.controls.lastName.value;
    this.model.gender = this.formGroup.controls.gender.value;
    this.model.birthDate = this.formGroup.controls.birthDate.value;
    this.userService.update(this.model).subscribe(
      data => {
        const user = data as HttpResponse<any>;
        const userModel: UserModel = user.body;
        this.model = userModel;
        this.storageService.setCurrentUser(userModel);
        this.snackBar.openSnackBar('Successfully updated');
      },
      () => this.snackBar.openSnackBar('Not updated. Please, check your data and try again.')
    );
  }

}
