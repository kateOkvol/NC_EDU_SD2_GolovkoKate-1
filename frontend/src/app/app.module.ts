import {BrowserModule} from '@angular/platform-browser';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {NgModule} from '@angular/core';
import {NgIdleKeepaliveModule} from '@ng-idle/keepalive';
import {MomentModule} from 'angular2-moment';
import {ModalModule} from 'ngx-bootstrap/modal';
import {AccordionModule} from 'ngx-bootstrap';

import {AppComponent} from './app.component';
import {Routes, RouterModule} from '@angular/router';
import {RegistrationComponent} from './modules/pages/registration/registration.component';
import {HTTP_INTERCEPTORS, HttpClientModule} from '@angular/common/http';
import {AccountComponent} from './modules/pages/account/account.component';
import {LoginComponent} from './modules/pages/login/login.component';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {PollComponent} from './modules/models/poll/poll.component';
import {QuestionComponent} from './modules/models/poll/question/question.component';
import {AnswerComponent} from './modules/models/poll/question/answer/answer.component';
import {
  MatCardModule,
  MatCheckboxModule,
  MatInputModule,
  MatPaginatorModule,
  MatProgressSpinnerModule,
  MatRadioModule,
  MatSelectModule,
  MatTableModule,
  MatTabsModule,
  MatSnackBarModule
} from '@angular/material';
import {HeaderComponent} from './modules/pages/header/header.component';
import {HomeComponent} from './modules/pages/home/home.component';
import {UserPollsComponent} from './modules/pages/user-polls/user-polls.component';
import {SurveyComponent} from './modules/models/survey/survey.component';
import {CompletedPollsComponent} from './modules/pages/completed-polls/completed-polls.component';
import {NewPollComponent} from './modules/pages/newpoll/new-poll.component';
import {AdminPollsComponent} from './modules/pages/admin-polls/admin-polls.component';
import {StatisticComponent} from './modules/models/statistic/statistic.component';
import {PieChartModule} from '@swimlane/ngx-charts';
import {APIInterceptor} from './interceptors/api-interceptor';
import {CanActivateNotLoginPages} from './services/security/can-activate-not-login-pages.service';
import {SubmittedPollComponent} from './modules/models/submitted-poll/submitted-poll.component';
import {NotFoundComponent} from './modules/pages/not-found/not-found.component';
import {CanActivateLoginPagesService} from './services/security/can-activate-login-pages.service';

const appRoutes: Routes = [
  {path: '', component: HomeComponent},
  {path: 'registration', component: RegistrationComponent, canActivate: [CanActivateNotLoginPages]},
  {path: 'login', component: LoginComponent, canActivate: [CanActivateNotLoginPages]},
  {path: 'account', component: AccountComponent, canActivate: [CanActivateLoginPagesService]},
  {path: 'editor', component: NewPollComponent, canActivate: [CanActivateLoginPagesService]},
  {path: 'draft', component: PollComponent, canActivate: [CanActivateLoginPagesService]},
  {path: 'user-polls', component: UserPollsComponent, canActivate: [CanActivateLoginPagesService]},
  {path: 'admin-polls', component: AdminPollsComponent, canActivate: [CanActivateLoginPagesService]},
  {path: 'completed-polls', component: CompletedPollsComponent, canActivate: [CanActivateLoginPagesService]},
  {path: 'survey/:link', component: SurveyComponent},
  {path: '404', component: NotFoundComponent},
  {path: '**', redirectTo: '404'}
];

@NgModule({
  declarations: [
    AppComponent,
    RegistrationComponent,
    AccountComponent,
    LoginComponent,
    PollComponent,
    QuestionComponent,
    AnswerComponent,
    HeaderComponent,
    HomeComponent,
    UserPollsComponent,
    SurveyComponent,
    CompletedPollsComponent,
    NewPollComponent,
    AdminPollsComponent,
    StatisticComponent,
    SubmittedPollComponent,
    NotFoundComponent
  ],
  imports: [
    BrowserModule,
    RouterModule.forRoot(appRoutes),
    NgIdleKeepaliveModule.forRoot(),
    ModalModule.forRoot(),
    MomentModule,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule,
    BrowserAnimationsModule,
    MatInputModule,
    MatSelectModule,
    MatRadioModule,
    MatCheckboxModule,
    MatPaginatorModule,
    MatTableModule,
    MatTabsModule,
    MatCardModule,
    PieChartModule,
    MatProgressSpinnerModule,
    MatSnackBarModule,
    AccordionModule
  ],
  exports: [],
  providers: [APIInterceptor, {
    provide: HTTP_INTERCEPTORS,
    useClass: APIInterceptor,
    multi: true
  }],
  bootstrap: [AppComponent]
})
export class AppModule {
}
