import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {QuestionModel} from './question.model';
import {AnswerModel} from './answer/answer.model';
import {FormControl, Validators} from '@angular/forms';
import {PollEventService} from '../../../../services/poll-event.service';

@Component({
  selector: 'app-question',
  templateUrl: './question.component.html',
  styleUrls: ['./question.component.css']
})
export class QuestionComponent implements OnInit {
  @Input()
  public pollType: string;

  @Input()
  public model: QuestionModel;

  @Output()
  public modelChange: EventEmitter<QuestionModel> = new EventEmitter<QuestionModel>();


  public valid: boolean;
  public type: FormControl;
  public questionText: FormControl;
  public isRequired: FormControl;

  constructor(private pollEvent: PollEventService) {
  }

  ngOnInit() {
    this.formInit();
    console.log('question model');
    // this.type.statusChanges.subscribe((status) => {
    //   if (status === 'INVALID') {
    //     this.pollEvent.setSubmitNotValid();
    //   }
    // });
    // this.questionText.statusChanges.subscribe((status) => {
    //   if (status === 'INVALID') {
    //     this.pollEvent.setSubmitNotValid();
    //   }
    // });
  }

  public addFirstAnswer(): void {
    this.valid = false;
    this.model.answers = [];
    this.addAnswer();
  }

  public addAnswer(): void {
    this.valid = false;
    this.model.answers.push(new AnswerModel(''));
  }

  public deleteAnswer(id: number): void {
    this.model.answers.splice(id, 1);
  }

  private formInit() {
    this.type = new FormControl('', Validators.required);
    this.questionText = new FormControl('', [
      Validators.required,
      Validators.maxLength(250)]
    );
    this.isRequired = new FormControl('', Validators.nullValidator);
    this.type.valueChanges.subscribe(() => {
      this.addFirstAnswer();
    });
  }

}
