import {Component, Input, OnInit} from '@angular/core';
import {PollModel} from '../poll/models/poll.model';
import {StatisticModel} from './models/statistic.model';
import {StatisticService} from '../../../services/http-requests/statistic.service';
import {SnackBarService} from '../../../services/snack-bar.service';

@Component({
  selector: 'app-statistic',
  templateUrl: './statistic.component.html',
  styleUrls: ['./statistic.component.css']
})
export class StatisticComponent implements OnInit {
  @Input()
  public pollModel: PollModel;
  public statisticModels: StatisticModel[] = [];

  private loader: boolean = false;

  constructor(private statisticService: StatisticService,
              private snackBar: SnackBarService) {
  }

  ngOnInit() {
    console.log('statistic');
    this.statisticService.getStatistic(this.pollModel.id).subscribe(
      data => {
        console.log(data as StatisticModel[]);
        this.statisticModels = data as StatisticModel[];
        this.pollModel.questions.forEach((question, index) => {
          let count = 0;
          this.statisticModels.forEach((statist) => {
            if (question.id === statist.questionId) {
              count++;
            }
          });
          if (count === 0) {
            this.statisticModels.splice(index, 0, new StatisticModel());
          }
        });
        this.loader = true;
      },
      () => this.snackBar.openSnackBar('Unable to get statistic')
    );
  }

}
