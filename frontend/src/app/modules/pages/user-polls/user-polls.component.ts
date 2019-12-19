import {Component, OnInit, ViewChild} from '@angular/core';
import {PollService} from '../../../services/http-requests/poll.service';
import {UserModel} from '../../models/users/user.model';
import {PollModel} from '../../models/poll/models/poll.model';
import {MatPaginator, MatTableDataSource, PageEvent} from '@angular/material';
import {Router} from '@angular/router';
import {UserSubscribeService} from '../../../services/user-subscribe.service';
import {StorageService} from '../../../services/storage.service';
import {SnackBarService} from '../../../services/snack-bar.service';

@Component({
  selector: 'app-userpoll',
  templateUrl: './user-polls.component.html',
  styleUrls: ['./user-polls.component.css']
})
export class UserPollsComponent implements OnInit {
  public pollModels: PollModel[];
  public dataSource;

  private tableHeader: string[] = ['title', 'isDraft'];
  private isDraft: boolean = true;
  private userId: number;

  @ViewChild(MatPaginator, {static: true}) paginator: MatPaginator;
  private pageEvent: PageEvent;

  constructor(private router: Router,
              private pollService: PollService,
              private snackBar: SnackBarService,
              private storageService: StorageService) {
  }

  ngOnInit() {
    console.log('polls');
    localStorage.removeItem('poll');
    const user = this.storageService.getCurrentUser();
    this.userId = user.id;
    this.updatePollsPage(this.userId, true);
  }

  public setDraft(value: boolean): void {
    this.isDraft = value;
    this.tableHeader = (value) ? ['title', 'isDraft'] : ['title', 'link'];
    this.updatePollsPage(this.userId, value);
  }

  public changePage($event): void {
    this.pageEvent = $event;
  }

  public updatePollsPage(userId: number, value: boolean): void {
    this.pollService.getByUserId(userId, value).subscribe(data => {
        this.pollModels = data as PollModel[];
        this.dataSource = new MatTableDataSource<PollModel>(this.pollModels);
        this.paginator.pageIndex = 0;
        this.dataSource.paginator = this.paginator;
      },
      () => this.snackBar.openSnackBar('Unable to get data')
    );
  }

  public editPoll(poll: PollModel): void {
    localStorage.setItem('poll', JSON.stringify(poll));
    this.router.navigate(['/draft']);
  }

  public showCompletedPolls(poll: PollModel): void {
    localStorage.setItem('poll', JSON.stringify(poll));
    this.router.navigate(['/completed-polls']);
  }

  private newTab(link: string) {
    window.open(link, '_blank');
  }
}
