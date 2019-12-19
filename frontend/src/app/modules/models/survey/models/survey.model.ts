import {CompletedQuestionModel} from './completed-question.model';

export class SurveyModel {
  id?: number;
  pollId: number;
  questions: CompletedQuestionModel[];
}
