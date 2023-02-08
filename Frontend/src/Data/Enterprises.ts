import { Enterprise } from "../Entity/Enterprise";

export class Enterprises {
  static list: Enterprise[];

  static get() {
    if (localStorage.getItem("enterprises")) {
      return (this.list = JSON.parse(
        localStorage.getItem("enterprises") || ""
      ));
    } else {
      return (this.list = []);
    }
  }
  static create(user: Enterprise) {
    this.list.push(user);
  }

  static save() {
    localStorage.setItem("enterprises", JSON.stringify(this.list));
  }
}
