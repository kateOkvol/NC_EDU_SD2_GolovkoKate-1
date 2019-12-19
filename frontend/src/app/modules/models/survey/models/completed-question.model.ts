export class CompletedQuestionModel {
  id?: number;
  answer: string;
  questionId: number;


  constructor(answer: string, id: number) {
    this.answer = answer;
    this.questionId = id;
  }
}
