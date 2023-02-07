import { User } from "./User";
export type IUserLike = {
  id: string;
  jobId: number;
};

export class Candidate extends User {
  constructor(
    userName: string,
    email: string,
    doc: number,
    country: string,
    state: string,
    zipCode: number,
    public qualification: string[] = [],
    public likes: IUserLike[]
  ) {
    super(userName, email, doc, country, state, zipCode);
    this.qualification = qualification;
    this.likes = likes;
  }
}
