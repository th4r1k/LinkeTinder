package linketinder.View

import linketinder.Controller.CandidateController
import linketinder.DAO.CandidateDAO
import linketinder.DAO.QualificationDAO
import linketinder.DAO.UserDAO
import linketinder.Entity.CandidateMatch
import linketinder.Entity.Job
import linketinder.Entity.User

class CandidateView {

    static menu(User user) {

        println ""
        println "Ola $user.name"
        println "1 - Ver oportunidades"
        println "2 - Ver matches"
        println "3 - Minhas competencias"
        println "0 - Sair"
        println ""
        println "Digite o codigo do comando"
        Scanner input = new Scanner(System.in);
        String command = input.nextLine();
        CandidateController candidateController = new CandidateController(new CandidateDAO(), new UserDAO())
        QualificationDAO dbQualification = new QualificationDAO()

        switch (command) {
            case "0":
                Menu.start()
                break
            case "1":
                opportunities(candidateController, user, dbQualification)
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

    static void opportunities(CandidateController candidateController, User user, QualificationDAO dbQualification ){
        Scanner input = new Scanner(System.in)

        int UserId = candidateController.candidateDAO.getId(user.id)
        List<Job> jobs = candidateController.getOpportunities()
        jobs.forEach {
            dbQualification.jobQualifications(it)

            println "0 - Like"
            println "1 - Proximo"
            String cmd = input.nextLine()
            if (cmd == "0") {
                candidateController.candidateDAO.like(it.id, UserId)
            }
        }
        println("Nao ha mais vagas cadastradas no momento")
        menu(user)
    }

    static void matches(CandidateController candidateController, User user){
        List<CandidateMatch> matches = candidateController.match()
        int id = candidateController.candidateDAO.getId(user.id)

        matches.forEach {
            if (id == it.candidate_id)
                println("Match com a empresa ${it.name} entrar em contato pelo email ${it.email}")
        }
        menu(user)
    }

}

