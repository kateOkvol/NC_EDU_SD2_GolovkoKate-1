import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {SurveyModel} from './models/survey.model';
import {SurveyService} from '../../../services/http-requests/survey.service';
import {PollService} from '../../../services/http-requests/poll.service';
import {PollModel} from '../poll/models/poll.model';
import {CompletedQuestionModel} from './models/completed-question.model';
import {PollType} from '../poll/models/poll-type.enum';
import {Router} from '@angular/router';
import {AnswerModel} from '../poll/question/answer/answer.model';
import {SnackBarService} from '../../../services/snack-bar.service';

@Component({
  selector: 'app-survey',
  templateUrl: './survey.component.html',
  styleUrls: ['./survey.component.css']
})
export class SurveyComponent implements OnInit {
  public surveyModel: SurveyModel = new SurveyModel();
  @Input()
  public poll: PollModel = new PollModel([]);
  @Input()
  public pollType: string = PollType.Processed;

  constructor(private surveyService: SurveyService,
              private pollService: PollService,
              private snackBar: SnackBarService,
              private  router: Router) {
  }

  ngOnInit() {
    console.log('survey');
    if (this.poll.questions.length === 0) {
      let link: string;
      if (localStorage.getItem('poll') !== null) {
        link = JSON.parse(localStorage.getItem('poll')).link;
      } else {
        link = location.host + location.pathname;
      }
      this.pollService.getByLink(link).subscribe(
        data => {
          this.poll = data as PollModel;
        },
        () => this.router.navigate(['404'])
      );
    }
  }

  public saveSurvey(): void {
    let valid = true;
    this.surveyModel.questions = [];
    this.surveyModel.pollId = this.poll.id;

    this.poll.questions.forEach((question) => {
      question.answers.forEach((answer) => {
        if (question.type === 'TEXT' || answer.isSelected) {
          this.surveyModel.questions.push(new CompletedQuestionModel(answer.text, question.id));
        }
      });
      const last = this.surveyModel.questions.length - 1;
      if (question.isRequired) {
        if (this.surveyModel.questions[last].questionId === question.id) {
          if (this.surveyModel.questions[last].answer === '') {
            valid = false;
          }
        } else {
          valid = false;
        }
      }
    });


    if (valid) {
      this.surveyService.create(this.surveyModel).subscribe(() => {
          this.snackBar.openSnackBar('Successfully saved!');
          this.router.navigate(['/']);
        },
        () => this.snackBar.openSnackBar('Some error occurred. Please, try again.'));
    } else {
      this.snackBar.openSnackBar('Please, fill in all required fields');
    }
  }

}
