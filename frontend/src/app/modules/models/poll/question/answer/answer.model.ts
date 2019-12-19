export class AnswerModel {
  id?: number;
  questionId?: number;
  completedPollId?: number;
  text: string;
  isSelected: boolean;

  constructor(text: string) {
    this.text = text;
  }
}
