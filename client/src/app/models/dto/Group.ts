import {UserScore} from './UserScore';

export class Group {
  id: number;
  information: string;
  limit: number;
  users: UserScore[];
  enrollMark: number;
  facultyId: number;
  issueDate: Date;
  qualify: string;
  subjectNames: String[];
}
