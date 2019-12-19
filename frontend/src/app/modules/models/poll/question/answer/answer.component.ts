import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {AnswerModel} from './answer.model';
import {FormControl, Validators} from '@angular/forms';
import {PollEventService} from '../../../../../services/poll-event.service';

@Component({
  selector: 'app-answer',
  templateUrl: './answer.component.html',
  styleUrls: ['./answer.component.css']
})

export class AnswerComponent implements OnInit {
  @Input()
  public pollType: string;

  @Input()
  public questType: string;

  @Input()
  public model: AnswerModel;

  @Output()
  public modelChange: EventEmitter<AnswerModel> = new EventEmitter<AnswerModel>();

  public answerText: FormControl;

  constructor(private pollEvent: PollEventService) {
  }

  ngOnInit() {
    console.log('answer model');
    this.answerText = new FormControl('', [Validators.required, Validators.maxLength(500)]);
    // this.answerText.statusChanges.subscribe((status) => {
    //   if (status === 'INVALID') {
    //     this.pollEvent.setSubmitNotValid();
    //   }
    // });
  }

}
