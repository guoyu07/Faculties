// "groupId": 0,
//   "id": 0,
//   "lastname": "string",
//   "mark": 0,
//   "name": "string",
//   "patronymic": "string"

import {UserInformation} from './UserInformation';

export class User {
  id: number;
  lastname: string;
  marks: Object;
  name: string;
  patronymic: string;
  info: UserInformation;
  groupId: number;


  constructor(id: number, lastname: string, marks: Object, name: string, patronymic: string, info: UserInformation, groupId: number) {
    this.id = id;
    this.lastname = lastname;
    this.marks = marks;
    this.name = name;
    this.patronymic = patronymic;
    this.info = info;
    this.groupId = groupId;
  }
}
