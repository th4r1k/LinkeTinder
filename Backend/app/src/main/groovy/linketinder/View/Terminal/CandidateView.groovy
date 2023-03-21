package linketinder.View.Terminal

import linketinder.Controller.CandidateController
import linketinder.Controller.JobController
import linketinder.Controller.JobQualificationController
import linketinder.Model.DAO.CandidateDAO
import linketinder.Model.DAO.JobDAO
import linketinder.Model.DAO.JobQualificationDAO
import linketinder.Model.DAO.UserDAO
import linketinder.Model.Entity.CandidateMatch
import linketinder.Model.Entity.Job
import linketinder.Model.Entity.User

class CandidateView {

    static menu(User user) {
        CandidateController candidateController = new CandidateController(new CandidateDAO(), new UserDAO())
        JobController jobController = new JobController(new JobDAO())
        JobQualificationController jobQualificationController = new JobQualificationController(new JobQualificationDAO())
        Scanner input = new Scanner(System.in);
        boolean quit = false
        showCandidateMenu(user)

        while (!quit) {
            String command = input.nextLine();

            switch (command) {
                case "0":
                    quit = true
                    Menu.showMenu()
                    break
                case "1":
                    opportunities(candidateController, user, jobController, jobQualificationController)
                    break
                case "2":
                    matches(candidateController, user)
                    break
                case "3":
                    CandidateQualificationsView.menu(user)
                    break
                default:
                    break
            }
        }
    }

    static void showCandidateMenu(User user) {
        println ""
        println "Ola $user.name"
        println "1 - Ver oportunidades"
        println "2 - Ver matches"
        println "3 - Minhas competencias"
        println "0 - Sair"
        println ""
        println "Digite o codigo do comando"
    }

    static void opportunities(CandidateController candidateController, User user, JobController jobController, JobQualificationController jobQualificationController) {
        Scanner input = new Scanner(System.in)

        int UserId = candidateController.getId(user.id)
        List<Job> jobs = jobController.listAllJobs()
        jobs.forEach {
            jobQualificationController.getJobDescription(it as Job)

            println "0 - Like"
            println "1 - Proximo"
            String cmd = input.nextLine()
            if (cmd == "0") {
                candidateController.candidateDAO.like(it.id, UserId)
            }
        }
        println("Nao ha mais vagas cadastradas no momento")
        showCandidateMenu(user)
    }

    static void matches(CandidateController candidateController, User user) {
        List<CandidateMatch> matches = candidateController.match()
        int id = candidateController.getId(user.id)

        matches.forEach {
            if (id == it.candidate_id)
                println("Match com a empresa ${it.name} entrar em contato pelo email ${it.email}")
        }
        showCandidateMenu(user)
    }

}

