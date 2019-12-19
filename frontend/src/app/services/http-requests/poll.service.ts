import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {PollModel} from '../../modules/models/poll/models/poll.model';

@Injectable({
  providedIn: 'root'
})
export class PollService {

  constructor(private http: HttpClient) {
  }

  public getById(id: number) {
    return this.http.get('/api/polls/' + id);
  }

  public getByLink(link: string) {
    return this.http.get('/api/polls?link=' + link);
  }

  public create(poll: PollModel) {
    return this.http.post('/api/polls', poll);
  }

  public getByUserId(id: number, isDraft: boolean) {
    return this.http.get('/api/polls/user/' + id + '?isDraft=' + isDraft);
  }

  public getAll(page: number, size: number) {
    return this.http.get('/api/polls/admin?page=' + page + '&size=' + size);
  }
}
