import { User } from "./User";

export type IOpportunity = {
  id: number;
  description: string;
  qualification: string[];
};

export class Enterprise extends User {
  constructor(
    userName: string,
    email: string,
    doc: number,
    country: string,
    state: string,
    zipCode: number,
    public opportunity: IOpportunity[],
    public likes: string[]
  ) {
    super(userName, email, doc, country, state, zipCode);
    this.opportunity = opportunity;
    this.likes = likes;
  }
}
