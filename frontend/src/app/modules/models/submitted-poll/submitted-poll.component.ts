import {Component, Input, OnInit} from '@angular/core';
import {PollModel} from '../poll/models/poll.model';
import {PollType} from '../poll/models/poll-type.enum';

@Component({
  selector: 'app-submitted-poll',
  templateUrl: './submitted-poll.component.html',
  styleUrls: ['./submitted-poll.component.css']
})
export class SubmittedPollComponent implements OnInit {
  public pollModel: PollModel = new PollModel([]);
  public pollType: string = PollType.Completed;

  constructor() {
  }

  ngOnInit() {
    this.pollModel = JSON.parse(localStorage.getItem('poll')) as PollModel;
  }

}
