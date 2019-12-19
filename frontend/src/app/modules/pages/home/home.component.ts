import {Component, OnInit} from '@angular/core';
import {UserSubscribeService} from '../../../services/user-subscribe.service';
import {Router} from '@angular/router';
import {UserModel} from '../../models/users/user.model';
import {TopicModel} from '../../models/poll/models/topic.model';
import {TopicService} from '../../../services/http-requests/topic.service';
import {StorageService} from '../../../services/storage.service';
import {SnackBarService} from '../../../services/snack-bar.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {
  public isLogin: boolean;
  private role: string;
  private userId: number;
  public adminTopics: TopicModel[] = [];
  public userTopics: TopicModel[] = [];

  constructor(private route: Router,
              private userRoleService: UserSubscribeService,
              private topicService: TopicService,
              private snackBar: SnackBarService,
              private storageService: StorageService) {
  }

  ngOnInit() {
    localStorage.removeItem('topic');
    if (this.storageService.getCurrentUser()) {
      const user = this.storageService.getCurrentUser();
      this.isLogin = true;
      this.role = user.role;
      this.userId = user.id;

      this.userRoleService.getLoginStatus().subscribe(
        (status) => {
          this.isLogin = status;
        }
      );
      this.topicService.getByAdmin().subscribe(
        data => {
          this.adminTopics = data as TopicModel[];
        },
        () => this.snackBar.openSnackBar('Unable to get topics')
      );
      this.topicService.getByUserId(this.userId).subscribe(
        data => {
          this.userTopics = data as TopicModel[];
        },
        () => this.snackBar.openSnackBar('Unable to get data')
      );
    }
  }

  public redirect(topic: TopicModel): void {
    localStorage.setItem('topic', JSON.stringify(topic));
    this.route.navigate(['/draft']);
  }

  public createTopic(): void {
    localStorage.setItem('isTopic', 'true');
    this.route.navigate(['/editor']);
  }

}
