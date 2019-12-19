import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {TopicModel} from '../../modules/models/poll/models/topic.model';

@Injectable({
  providedIn: 'root'
})
export class TopicService {

  constructor(private http: HttpClient) {
  }

  public getById(id: number) {
    return this.http.get('/api/topics/' + id);
  }

  public getByUserId(userId: number) {
    return this.http.get('api/topics/user/' + userId);
  }

  public getByAdmin() {
    return this.http.get('api/topics/admin');
  }

  public create(topic: TopicModel) {
    return this.http.post('api/topics', topic);
  }
}
