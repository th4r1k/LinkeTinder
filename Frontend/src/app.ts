import Chart from "chart.js/auto";
import { Candidates } from "./Data/Candidates";
import { Enterprises } from "./Data/Enterprises";
import { Candidate, IUserLike } from "./Entity/Candidate";
import { Enterprise, IOpportunity } from "./Entity/Enterprise";
import { IUser } from "./Entity/User";

const candidateRadio = document.getElementById(
  "candidateRadio"
) as HTMLInputElement;
const enterpriseRadio = document.getElementById(
  "enterpriseRadio"
) as HTMLInputElement;
const userName = document.getElementById("name") as HTMLInputElement;
const email = document.getElementById("email") as HTMLInputElement;
const doc = document.getElementById("doc") as HTMLInputElement;
const country = document.getElementById("country") as HTMLInputElement;
const state = document.getElementById("state") as HTMLInputElement;
const zipCode = document.getElementById("zipCode") as HTMLInputElement;

const userLogin = document.getElementById("userLogin") as HTMLInputElement;
const loginButton: HTMLElement | null = document.getElementById(
  "loginButton"
) as HTMLInputElement;

const createAccModal: HTMLElement | null =
  document.querySelector(".createAccModal");

let candidates: Candidate[] = Candidates.get();
let enterprises: Enterprise[] = Enterprises.get();
let userInfo: IUser;

const switchModal = () => {
  let addStatus: string = createAccModal!.style.display;
  if (addStatus == "block") {
    createAccModal!.style.display = "none";
    clearFields();
  } else {
    createAccModal!.style.display = "block";
    clearFields();
  }
};

const addBtn: HTMLElement | null = document.querySelector(".addBtn");
addBtn?.addEventListener("click", switchModal);

window.onclick = function (event: MouseEvent) {
  if (event.target == createAccModal) {
    switchModal();
  }
};

const addNewUser = function () {
  if (validadeNewUserForm()) {
    if (!verifyCandidateExists(userName.value)) {
      alert("Usuário ja existe!");
    } else if (!verifyEnterpriseExists(userName.value)) {
      alert("Usuário ja existe!");
    } else {
      if (candidateRadio.checked == true) {
        const newCandidate = new Candidate(
          userName!.value.replace(/\s/g, ""),
          email.value,
          parseInt(doc!.value),
          country.value,
          state.value,
          parseInt(zipCode.value),
          [],
          []
        );
        Candidates.create(newCandidate);
        Candidates.save();
        createAccModal!.style.display = "none";
      } else if (enterpriseRadio.checked == true) {
        const newEnterprise = new Enterprise(
          userName!.value.replace(/\s/g, ""),
          email.value,
          parseInt(doc!.value),
          country.value,
          state.value,
          parseInt(zipCode.value),
          [],
          []
        );
        Enterprises.create(newEnterprise);
        Enterprises.save();
        createAccModal!.style.display = "none";
      }
    }
  }
};

const addUser: HTMLElement | null = document.querySelector(".addUser");
addUser?.addEventListener("click", addNewUser);

const verifyCandidateExists = (userLogin: string): boolean => {
  let isOk = true;
  if (candidates) {
    candidates.forEach((candidate) => {
      if (candidate.userName == userLogin) {
        isOk = false;
      }
    });
  }
  return isOk;
};

const verifyEnterpriseExists = (userLogin: string): boolean => {
  let isOk = true;
  if (enterprises) {
    enterprises.forEach((enterprise) => {
      if (enterprise.userName == userLogin) {
        isOk = false;
      }
    });
  }
  return isOk;
};

function getUserInfo(userLogin: string) {
  candidates.forEach((candidate) => {
    if (candidate.userName == userLogin) {
      userInfo = {
        userName: candidate.userName,
        email: candidate.email,
        doc: candidate.doc,
        country: candidate.country,
        state: candidate.state,
        zipCode: candidate.zipCode,
        qualification: candidate.qualification,
        likes: candidate.likes,
      };
    }
  });

  enterprises.forEach((enterprise) => {
    if (enterprise.userName == userLogin) {
      userInfo = {
        userName: enterprise.userName,
        email: enterprise.email,
        doc: enterprise.doc,
        country: enterprise.country,
        state: enterprise.state,
        zipCode: enterprise.zipCode,
        opportunity: enterprise.opportunity,
        likes: enterprise.likes,
      };
    }
  });
}

function clearFields() {
  (userName.value = ""),
    (email.value = ""),
    (doc.value = ""),
    (country.value = ""),
    (state.value = ""),
    (zipCode.value = "");
}

function login() {
  if (!verifyCandidateExists(userLogin.value)) {
    document.getElementById("loginBox")!.style.display = "none";
    document.getElementById("candidateMenu")!.style.display = "block";
    getUserInfo(userLogin.value);
  } else if (!verifyEnterpriseExists(userLogin.value)) {
    document.getElementById("loginBox")!.style.display = "none";
    document.getElementById("enterpriseMenu")!.style.display = "block";
    getUserInfo(userLogin.value);
  } else {
    alert("Usuario nao encontrado");
  }
}
loginButton?.addEventListener("click", login);

// MENU DO CANDIDATO  ***************************************************************************************************************************

const qualicationInfo: HTMLElement | null =
  document.getElementById("qualicationInfo");

const switchCandidateMenu = () => {
  let addStatus: string = qualicationInfo!.style.display;
  if (addStatus == "block") {
    qualicationInfo!.style.display = "none";
  } else {
    qualicationInfo!.style.display = "block";
  }
};

const qualificationBtn: HTMLElement | null =
  document.getElementById("qualificationBtn");
qualificationBtn?.addEventListener("click", switchCandidateMenu);

const userJava = document.getElementById("userJava") as HTMLInputElement;
const userGroovy = document.getElementById("userGroovy") as HTMLInputElement;
const userJs = document.getElementById("userJs") as HTMLInputElement;
const userPhyton = document.getElementById("userPhyton") as HTMLInputElement;
const saveQualification: HTMLElement | null =
  document.getElementById("saveQualification");

saveQualification?.addEventListener("click", updateQualification);

function updateQualification() {
  let skills: string[] = [];

  userJava.checked && skills.push(userJava.value);
  userGroovy.checked && skills.push(userGroovy.value);
  userJs.checked && skills.push(userJs.value);
  userPhyton.checked && skills.push(userPhyton.value);

  candidates.forEach((candidate: Candidate) => {
    if (candidate.userName == userInfo.userName) {
      candidate.qualification = skills;
    }
  });
  localStorage.setItem("candidates", JSON.stringify(candidates));
  alert("Suas habilidades foram atualizadas");
}

let renderJobs: HTMLElement | null = document.getElementById("renderJobs");
const renderOpportunitiesForCandidates = () => {
  renderJobs!.innerHTML = "";
  enterprises.forEach((enterprise: Enterprise) => {
    if (enterprise.opportunity) {
      enterprise.opportunity.forEach((opportunity: IOpportunity) => {
        renderJobs!.insertAdjacentHTML(
          "beforeend",

          `<hr><p>Afinidade:${affinityIndex(opportunity.qualification)}/${
            opportunity.qualification.length
          } Descricao:${opportunity.description} Requisitos:${
            opportunity.qualification
          }<button>Like</button></p>`
        );
        let like: IUserLike = {
          id: enterprise.userName,
          jobId: opportunity.id,
        };
        renderJobs
          ?.lastElementChild!.querySelector("button")!
          .addEventListener("click", () => {
            candidateLike(like);
          });
      });
    }
  });
};

function affinityIndex(opportunityQualifications: string[]): number {
  let index: number = 0;
  userInfo.qualification?.forEach((qualification) => {
    if (opportunityQualifications.includes(qualification)) {
      index++;
    }
  });
  return index;
}

function candidateLike(like: IUserLike) {
  candidates.forEach((candidate: Candidate) => {
    if (candidate.userName == userInfo.userName) {
      let isDuplicate = candidate.likes.some(
        (data: IUserLike) => data.id === like.id && data.jobId === like.jobId
      );
      if (!isDuplicate) {
        candidate.likes.push(like);
      } else {
        alert("Você já deu like nessa vaga");
      }
    }
  });

  Candidates.save();
}

const jobsBtn: HTMLElement | null = document.getElementById("jobsBtn");
jobsBtn?.addEventListener("click", renderOpportunitiesForCandidates);

function renderMatchesCandidates() {
  renderJobs!.innerHTML = "";
  userInfo.likes?.forEach((like: IUserLike | string) => {
    if (typeof like != "string") {
      enterprises.forEach((enterprise) => {
        if (enterprise.userName == like.id) {
          if (enterprise.likes.includes(userInfo.userName)) {
            renderJobs!.insertAdjacentHTML(
              "beforeend",
              `<hr><p>Match com a empresa:${enterprise.userName} entre em contato pelo email:${enterprise.email} informando o Id da vaga que se candidatou:${like.jobId}</p>`
            );
          }
        }
      });
    }
  });
}

const matchBtn: HTMLElement | null = document.getElementById("matchBtn");
matchBtn?.addEventListener("click", renderMatchesCandidates);

// MENU DA EMPRESA  ***************************************************************************************************************************

const addOpportunityBtn: HTMLElement | null =
  document.getElementById("addOpportunityBtn");
const addOppoInfo: HTMLElement | null = document.getElementById("addOppoInfo");

const switchEnterpiseMenu = () => {
  let addStatus: string = addOppoInfo!.style.display;
  if (addStatus == "block") {
    addOppoInfo!.style.display = "none";
  } else {
    addOppoInfo!.style.display = "block";
  }
};

addOpportunityBtn?.addEventListener("click", switchEnterpiseMenu);

const saveOppotunity: HTMLElement | null =
  document.getElementById("saveOppotunity");
saveOppotunity?.addEventListener("click", updateOpportunity);

const jobId = document.getElementById("jobId") as HTMLInputElement;
const jobsDescription = document.getElementById(
  "jobsDescription"
) as HTMLInputElement;
const jobJava = document.getElementById("jobJava") as HTMLInputElement;
const jobGroovy = document.getElementById("jobGroovy") as HTMLInputElement;
const jobJs = document.getElementById("jobJs") as HTMLInputElement;
const jobPhyton = document.getElementById("jobPhyton") as HTMLInputElement;

function updateOpportunity() {
  let id: number = parseInt(jobId.value);
  let description: string = jobsDescription.value;
  let qualification: string[] = [];

  jobJava.checked && qualification.push(jobJava.value);
  jobGroovy.checked && qualification.push(jobGroovy.value);
  jobJs.checked && qualification.push(jobJs.value);
  jobPhyton.checked && qualification.push(jobPhyton.value);

  enterprises.forEach((enterprise) => {
    if (enterprise.userName == userInfo.userName) {
      enterprise.opportunity.push({
        id: id,
        description: description,
        qualification: qualification,
      });
    }
  });
  localStorage.setItem("enterprises", JSON.stringify(enterprises));
  alert("Vaga criada com sucesso");
}

let renderQualification: HTMLElement | null = document.getElementById(
  "renderQualification"
);

const renderOpportunitiesForEnterprise = () => {
  renderQualification!.innerHTML = "";
  candidates.forEach((candidate: Candidate) => {
    if (candidate.qualification.length > 0) {
      renderQualification?.insertAdjacentHTML(
        "beforeend",
        `<hr><p>Conhecimentos:${candidate.qualification}<button>Like</button></p>`
      );
      renderQualification
        ?.lastElementChild!.querySelector("button")!
        .addEventListener("click", () => {
          enterpriseLike(candidate.userName);
        });
    }
  });
};

function enterpriseLike(userid: string) {
  enterprises.forEach((enterprise) => {
    if (enterprise.userName == userInfo.userName) {
      if (enterprise.likes.includes(userid)) {
        alert("Você já deu like nesse usuário");
      } else {
        enterprise.likes.push(userid);
      }
    }
  });

  Enterprises.save();
}

const usersBtn: HTMLElement | null = document.getElementById("usersBtn");
usersBtn?.addEventListener("click", renderOpportunitiesForEnterprise);

const deleteJob = () => {
  let id = document.getElementById("selectJobId") as HTMLInputElement;

  enterprises.forEach((enterprise: Enterprise) => {
    if (enterprise.userName == userInfo.userName) {
      enterprise.opportunity = enterprise.opportunity.filter(
        (op) => op.id !== parseInt(id.value)
      );
    }
  });
  Enterprises.save();
  renderOwnJob();
};

let deleteJobId: HTMLElement | null;

const renderOwnJob = () => {
  renderQualification!.innerHTML = "";
  enterprises.forEach((enterprise: Enterprise) => {
    if (enterprise.userName == userInfo.userName) {
      enterprise.opportunity.forEach((opportunity) => {
        renderQualification!.innerHTML += `<p>ID:${opportunity.id} Descricao:${opportunity.description} Requisitos:${opportunity.qualification} </p><hr>`;
      });
      renderQualification!.innerHTML += `<input type=text id=selectJobId placeholder="Id da vaga que quer deletar" /> <button id=deleteJobId>Deletar</button>`;
    }
  });
  deleteJobId = document.getElementById("deleteJobId");
  deleteJobId?.addEventListener("click", deleteJob);
};

const delOpportunityBtn: HTMLElement | null =
  document.getElementById("delOpportunityBtn");
delOpportunityBtn?.addEventListener("click", renderOwnJob);

function renderMatchesEnterprise() {
  renderQualification!.innerHTML = "";
  userInfo.likes?.forEach((like: IUserLike | string) => {
    candidates.forEach((candidate) => {
      if (candidate.userName == like) {
        let isReciprocal = candidate.likes.some(
          (data: IUserLike) => data.id == userInfo.userName
        );

        if (isReciprocal) {
          renderQualification!.insertAdjacentHTML(
            "beforeend",
            `<hr><p>Match com candidato:${candidate.userName} entre em contato pelo email:${candidate.email}</p>`
          );
        }
      }
    });
  });
}

const matchsBtn: HTMLElement | null = document.getElementById("matchsBtn");
matchsBtn?.addEventListener("click", renderMatchesEnterprise);

// CHART ***************************************************************************************************************************

let userQuantity: number = candidates.length;

function usersKnowJava(): number {
  let users: number = 0;
  candidates.forEach((candidate) => {
    if (candidate.qualification.includes("java")) {
      users++;
    }
  });
  return users;
}

function usersKnowJs(): number {
  let users: number = 0;
  candidates.forEach((candidate) => {
    if (candidate.qualification.includes("javascript")) {
      users++;
    }
  });
  return users;
}

function usersKnowGroovy(): number {
  let users: number = 0;
  candidates.forEach((candidate) => {
    if (candidate.qualification.includes("groovy")) {
      users++;
    }
  });
  return users;
}

function usersKnowPhyton(): number {
  let users: number = 0;
  candidates.forEach((candidate) => {
    if (candidate.qualification.includes("phyton")) {
      users++;
    }
  });
  return users;
}

const ctx = document.getElementById("myChart") as HTMLCanvasElement;
new Chart(ctx, {
  type: "bar",
  data: {
    labels: ["Total", "Java", "Javascript", "Groovy", "Phyton"],
    datasets: [
      {
        label: "Quantidade de usuarios",
        data: [
          userQuantity,
          usersKnowJava(),
          usersKnowJs(),
          usersKnowGroovy(),
          usersKnowPhyton(),
        ],
        borderWidth: 1,
      },
    ],
  },
  options: {
    scales: {
      y: {
        beginAtZero: true,
      },
    },
  },
});

// Input Sanitization **************************************************************

const nameRegex: RegExp = /[A-z]{4,15}/;
const emailRegex: RegExp = /^[\w-\.]+@([\w-]+\.)+[\w-]{2,4}$/;
const DocRegex: RegExp =
  /([0-9]{2}[\.]?[0-9]{3}[\.]?[0-9]{3}[\/]?[0-9]{4}[-]?[0-9]{2})|([0-9]{3}[\.]?[0-9]{3}[\.]?[0-9]{3}[-]?[0-9]{2})/;
const countryRegex: RegExp = /[A-z]{4,15}/;
const stateRegex: RegExp = /[A-z]{2}/;
const zipCodeRegex: RegExp = /^[0-9]{5}-?[0-9]{3}$/;
const descriptionRegex: RegExp = /[A-z]{4,50}/;

function validadeNewUserForm(): boolean {
  let isOk = false;
  if (candidateRadio.checked == false && enterpriseRadio.checked == false) {
    alert("Selecionar candidato ou empresa");
  } else if (!nameRegex.test(userName.value)) {
    alert(
      "No campo nome sao aceito apenas caracteres alfanumerios de 4 a 15 digitos, nao sendo aceito espaco"
    );
  } else if (!emailRegex.test(email.value)) {
    alert("Favor verifique se o email esta correto");
  } else if (!DocRegex.test(doc.value)) {
    alert("CPF ou CNPJ invalido");
  } else if (!countryRegex.test(country.value)) {
    alert(
      "No campo pais sao aceito apenas caracteres alfanumerios de 4 a 15 digitos"
    );
  } else if (!stateRegex.test(state.value)) {
    alert("Digite apena a sigla do estado");
  } else if (!zipCodeRegex.test(zipCode.value)) {
    alert("Favor digitar um CEP valido");
  } else {
    isOk = true;
  }
  return isOk;
}
