import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {UserModel} from '../../modules/models/users/user.model';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private http: HttpClient) {
  }

  public getById(id: number) {
    return this.http.get('/api/users/' + id);
  }

  public create(user: UserModel) {
    return this.http.post('/api/users', user);
  }

  public update(user: UserModel) {
    return this.http.put('api/users/' + user.id, user);
  }

  public login(user: UserModel) {
    return this.http.post('api/users/login', user);
  }

  public generateToken(user: UserModel) {
    return this.http.post('api/login/generate-token', user);
  }
  public getCurrentUser() {
    return this.http.get('api/users/current');
  }

}
