import {AnswerModel} from './answer/answer.model';

export class QuestionModel {
  id?: number;
  type: string;
  name: string;
  answers: AnswerModel[] = [];
  isRequired?: boolean;

  constructor(type: string, name: string, answers: AnswerModel[], isRequired: boolean) {
    this.type = type;
    this.name = name;
    this.answers = answers;
    this.isRequired = isRequired;
  }

}
