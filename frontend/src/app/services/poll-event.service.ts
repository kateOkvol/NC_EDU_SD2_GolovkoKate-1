import {Injectable} from '@angular/core';
import {Observable, ReplaySubject, Subject} from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class PollEventService {
  private submit$: Subject<boolean> = new ReplaySubject(1);

  constructor() {
  }

  public getSubmitSubscription(): Observable<boolean> {
    return this.submit$;
  }

  public setSubmitNotValid(): void {
    this.submit$.next(false);
  }

  public setSubmitValid(): void {
    this.submit$.next(true);
  }
}
