import {Component, Input, OnInit, ViewChild} from '@angular/core';
import {PollModel} from '../../models/poll/models/poll.model';
import {SurveyService} from '../../../services/http-requests/survey.service';
import {SurveyModel} from '../../models/survey/models/survey.model';
import {MatPaginator, PageEvent} from '@angular/material';
import {PollType} from '../../models/poll/models/poll-type.enum';
import {StorageService} from '../../../services/storage.service';
import {SnackBarService} from '../../../services/snack-bar.service';

@Component({
  selector: 'app-completed-polls',
  templateUrl: './completed-polls.component.html',
  styleUrls: ['./completed-polls.component.css']
})
export class CompletedPollsComponent implements OnInit {
  public pollType: string = PollType.Completed;
  @Input()
  public pollModel: PollModel = new PollModel([]);
  @Input()
  public currentPoll: PollModel = new PollModel([]);
  @Input()
  public surveyModels: SurveyModel[] = [];

  private loader: boolean = false;

  private userRole: string;

  @ViewChild(MatPaginator, {static: true}) paginator: MatPaginator;
  private pageEvent: PageEvent;

  constructor(private surveyService: SurveyService,
              private snackBar: SnackBarService,
              private storageService: StorageService) {
  }

  ngOnInit() {
    console.log('completed');
    this.userRole = this.storageService.getCurrentUser().role;
    const pollModel = JSON.parse(localStorage.getItem('poll')) as PollModel;
    this.pollModel = pollModel;
    this.surveyService.getByPollId(pollModel.id)
      .subscribe(data => {
        this.surveyModels = data as SurveyModel[];
        if (!!this.surveyModels) {
          this.setCurrentPoll(this.surveyModels[0], pollModel);
          console.log(this.currentPoll);
        }
        this.loader = true;
      }, () => this.snackBar.openSnackBar('Unable to get data'));
  }

  updatePage($event) {
    this.pageEvent = $event;
    this.setCurrentPoll(this.surveyModels[this.pageEvent.pageIndex],
      JSON.parse(localStorage.getItem('poll')) as PollModel);
  }

  setCurrentPoll(survey: SurveyModel, poll: PollModel) {
    console.log(survey);
    this.currentPoll = poll;
    survey.questions.forEach((givenAnswer) => {
      for (let i = 0; i < this.currentPoll.questions.length; i++) {
        if (givenAnswer.questionId === this.currentPoll.questions[i].id) {
          this.currentPoll.questions[i].answers.forEach((answerVariant) => {
            if (this.currentPoll.questions[i].type === 'TEXT') {
              answerVariant.text = givenAnswer.answer;
            } else {
              if (answerVariant.text === givenAnswer.answer) {
                answerVariant.isSelected = true;
              }
            }
          });
          break;
        }
      }
    });
  }

}
