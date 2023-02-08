import { Candidate } from "../Entity/Candidate";

export class Candidates {
  static list: Candidate[];

  static get() {
    if (localStorage.getItem("candidates")) {
      return (this.list = JSON.parse(localStorage.getItem("candidates") || ""));
    } else {
      return (this.list = []);
    }
  }

  static create(user: Candidate) {
    this.list.push(user);
  }

  static save() {
    localStorage.setItem("candidates", JSON.stringify(this.list));
  }
}
