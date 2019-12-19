import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class StatisticService {

  constructor(private http: HttpClient) {
  }

  public getStatistic(pollId: number) {
    return this.http.get('api/statistic/polls/' + pollId);
  }
}
