import { IUserLike } from "./Candidate";
import { IOpportunity } from "./Enterprise";

export type IUser = {
  userName: string;
  email: string;
  doc: number;
  country: string;
  state: string;
  zipCode: number;
  opportunity?: IOpportunity[];
  qualification?: string[];
  likes?: string[] | IUserLike[];
};

export class User {
  constructor(
    public userName: string,
    public email: string,
    public doc: number,
    public country: string,
    public state: string,
    public zipCode: number
  ) {
    this.userName = userName;
    this.email = email;
    this.doc = doc;
    this.country = country;
    this.state = state;
    this.zipCode = zipCode;
  }
}
