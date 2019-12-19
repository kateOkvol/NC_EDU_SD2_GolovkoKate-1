import {Component, EventEmitter, Input, OnDestroy, OnInit, Output} from '@angular/core';
import {PollModel} from './models/poll.model';
import {QuestionModel} from './question/question.model';
import {PollService} from '../../../services/http-requests/poll.service';
import {PollType} from './models/poll-type.enum';
import {TopicModel} from './models/topic.model';
import {TopicService} from '../../../services/http-requests/topic.service';
import {Router} from '@angular/router';
import {StorageService} from '../../../services/storage.service';
import {SnackBarService} from '../../../services/snack-bar.service';
import {HttpResponse} from '@angular/common/http';
import {PollEventService} from '../../../services/poll-event.service';
import {FormControl, FormGroup, Validators} from '@angular/forms';

@Component({
  selector: 'app-poll',
  templateUrl: './poll.component.html',
  styleUrls: ['./poll.component.css']
})
export class PollComponent implements OnInit, OnDestroy {
  @Input()
  public model: PollModel = {title: '', questions: []};

  @Output()
  public modelChange: EventEmitter<PollModel> = new EventEmitter<PollModel>();

  @Input()
  public pollType: string = PollType.New;

  @Input()
  public isTopic: boolean = false;

  @Input()
  public pollForm: FormGroup;

  public pollValid: boolean = true;

  constructor(private pollService: PollService,
              private topicService: TopicService,
              private pollEvent: PollEventService,
              private storageService: StorageService,
              private snackBar: SnackBarService,
              private router: Router) {
  }

  ngOnInit() {
    console.log('poll model');
    this.formInit();
    if (this.model.questions.length === 0) {
      if (localStorage.getItem('poll') !== null) {
        this.model = JSON.parse(localStorage.getItem('poll')) as PollModel;
      }
      if (this.storageService.getCurrentUser() !== null) {
        const user = this.storageService.getCurrentUser();
        this.model.userId = user.id;
      }
    }
    if (this.pollType === PollType.New) {
      this.addQuestion();
    }
    if (localStorage.getItem('topic') !== null) {
      this.model.questions = JSON.parse(localStorage.getItem('topic')).questions;
    }
    // this.pollForm.statusChanges.subscribe((status) => {
    //   console.log('status: ' + status);
    //   if (status === 'VALID') {
    //     this.pollEvent.setSubmitValid();
    //   }
    //   if (status === 'INVALID') {
    //     this.pollEvent.setSubmitNotValid();
    //   }
    //
    // });
    // this.pollEvent.getSubmitSubscription().subscribe((valid) => {
    //   this.pollValid = valid.valueOf();
    // });
  }

  public addQuestion(): void {
    this.model.questions.push(new QuestionModel('', '', [], false));
  }

  public deleteQuestion(id: number): void {
    this.model.questions.splice(id, 1);
  }

  public saveDraft(): void {
    this.model.isDraft = true;
    this.cleanId();
    this.pollService.create(this.model).subscribe(data => {
      const poll = data as PollModel;
      this.snackBar.openSnackBar('Draft "' + poll.title + '" created!');
      this.router.navigate(['user-polls']);
    }, () => {
      this.snackBar.openSnackBar('Not saved. Please, check your data and try again');
    });
  }

  public savePoll(): void {
    this.model.isDraft = false;
    this.cleanId();
    this.pollService.create(this.model).subscribe(() => {
      this.snackBar.openSnackBar('Successfully.');
      this.router.navigate(['user-polls']);
    }, () => {
      this.snackBar.openSnackBar('Not saved. Please, check your data and try again');
    });
  }

  public deletePoll(): void {
    this.model.isDeleted = true;
    this.savePoll();
  }

  public saveTopic(): void {
    const topic: TopicModel = new TopicModel();
    topic.questions = this.model.questions;
    topic.name = this.model.title;
    const user = this.storageService.getCurrentUser();
    topic.userId = user.id;
    topic.isShared = (user.role === 'ADMIN');
    this.cleanId();
    this.topicService.create(topic).subscribe(
      data => {
        const createdTopic = (data as HttpResponse<TopicModel>).body as TopicModel;
        this.snackBar.openSnackBar('Topic "' + createdTopic.name + '" created!');
        this.router.navigate(['/']);
      }, () => {
        this.snackBar.openSnackBar('Not saved. Please, check your data and try again');
      });
  }

  private cleanId(): void {
    this.model.questions.forEach((question) => {
      question.id = null;
      question.answers.forEach((answer) => {
        answer.id = null;
      });
    });
  }

  private formInit(): void {
    this.pollForm = new FormGroup({
      pollTitle: new FormControl('', Validators.compose([
        Validators.required, Validators.maxLength(100)
      ]))
    });
  }

  ngOnDestroy(): void {
    localStorage.removeItem('topic');
  }

}
