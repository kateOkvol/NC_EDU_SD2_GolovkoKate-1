import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {SurveyModel} from '../../modules/models/survey/models/survey.model';

@Injectable({
  providedIn: 'root'
})
export class SurveyService {
  constructor(private http: HttpClient) {
  }

  public create(survey: SurveyModel) {
    return this.http.post('/api/surveys', survey);
  }

  public getByPollId(pollId: number) {
    return this.http.get('api/surveys/poll/' + pollId);
  }
}
