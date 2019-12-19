import {QuestionModel} from '../question/question.model';

export class TopicModel {
  id?: number;
  name?: string;
  userId?: number;
  isShared?: boolean;
  questions?: QuestionModel[];
}
