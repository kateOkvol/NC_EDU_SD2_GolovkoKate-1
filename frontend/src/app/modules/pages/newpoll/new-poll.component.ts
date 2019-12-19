import {Component, OnInit} from '@angular/core';
import {PollType} from '../../models/poll/models/poll-type.enum';

@Component({
  selector: 'app-newpoll',
  templateUrl: './new-poll.component.html',
  styleUrls: ['./new-poll.component.css']
})
export class NewPollComponent implements OnInit {
  public pollType: string = PollType.New;
  public isTopic: boolean = false;

  constructor() {
  }

  ngOnInit() {
    if (localStorage.getItem('isTopic') !== null) {
      this.isTopic = true;
      localStorage.removeItem('isTopic');
    }
    localStorage.removeItem('poll');
  }
}
