export class UserSubject{
  id: number;
  subject: string;
  score: number


  constructor(id: number, subject: string, score: number) {
    this.id = id;
    this.subject = subject;
    this.score = score;
  }
}
