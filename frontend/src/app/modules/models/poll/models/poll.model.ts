import {QuestionModel} from '../question/question.model';

export class PollModel {
  id?: number;
  title?: string;
  userId?: number;
  isDraft?: boolean = false;
  link?: string;
  questions: QuestionModel[];
  isDeleted?: boolean;


  constructor(questions: QuestionModel[]) {
    this.questions = questions;
  }
}
