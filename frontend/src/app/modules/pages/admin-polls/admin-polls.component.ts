import {Component, OnInit, ViewChild} from '@angular/core';
import {PollModel} from '../../models/poll/models/poll.model';
import {MatPaginator, MatTableDataSource} from '@angular/material';
import {PollService} from '../../../services/http-requests/poll.service';
import {Router} from '@angular/router';
import {SnackBarService} from '../../../services/snack-bar.service';

@Component({
  selector: 'app-admin-polls',
  templateUrl: './admin-polls.component.html',
  styleUrls: ['./admin-polls.component.css']
})
export class AdminPollsComponent implements OnInit {
  public pollModels: PollModel[];
  public dataSource;
  private tableHeader: string[] = ['title', 'info'];

  @ViewChild(MatPaginator, {static: true}) paginator: MatPaginator;

  constructor(private pollService: PollService,
              private snackBar: SnackBarService,
              private router: Router) {
  }

  ngOnInit() {
    localStorage.removeItem('poll');
    console.log('polls for admin');
    this.updatePollsPage(0, 5);
  }

  public updatePollsPage(page: number, size: number): void {
    this.pollService.getAll(page, size).subscribe(data => {
        this.pollModels = data as PollModel[];
        this.dataSource = new MatTableDataSource<PollModel>(this.pollModels);
        this.dataSource.paginator = this.paginator;
      },
      () => this.snackBar.openSnackBar('Unable to get data'));
  }

  public changePage(): void {
    this.updatePollsPage(this.paginator.pageIndex, this.paginator.pageSize);
  }

  public showPoll(poll: PollModel): void {
    localStorage.setItem('poll', JSON.stringify(poll));
    this.router.navigate(['/completed-polls']);
  }
}
