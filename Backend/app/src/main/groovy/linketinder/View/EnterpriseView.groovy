package linketinder.View

import linketinder.Controller.CandidateQualificationController
import linketinder.Controller.EnterpriseController
import linketinder.Model.DAO.CandidateQualificationDAO
import linketinder.Model.DAO.EnterpriseDAO
import linketinder.Model.DAO.UserDAO
import linketinder.Model.Entity.User

class EnterpriseView {

    static menu(User user) {
        EnterpriseController enterpriseController = new EnterpriseController(new EnterpriseDAO(), new UserDAO())
        CandidateQualificationController candidateQualificationController = new CandidateQualificationController(new CandidateQualificationDAO())
        Scanner input = new Scanner(System.in)

        println ""
        println "Ola $user.name"
        println "1 - Ver candidatos"
        println "2 - Ver matches"
        println "3 - Jobs"
        println "0 - Sair"
        println ""
        println "Digite o codigo do comando"
        String command = input.nextLine()
        switch (command) {
            case "0":
                Menu.start()
                break
            case "1":
                opportunities(enterpriseController, user, candidateQualificationController)
                break
            case "2":
                matches(enterpriseController, user)
                break
            case "3":
                JobView.menu(user)
                break
            default:
                break
        }
    }

    static opportunities(EnterpriseController enterpriseController, User user, CandidateQualificationController candidateQualificationController) {
        Scanner input = new Scanner(System.in)

        int id = enterpriseController.getId(user.id)
        List<User> candidates = enterpriseController.getOpportunities()
        candidates.forEach {
            candidateQualificationController.getQualifications(it)
            println "0 - Like"
            println "1 - Proximo"
            String cmd = input.nextLine()
            if (cmd == "0") {
                enterpriseController.enterpriseDAO.like(id, it.id)
                println("curtiu o candidato")
            }
        }
        println("Nao ha mais usuarios")
        menu(user)
    }

    static matches(EnterpriseController enterpriseController, User user) {
        int id = enterpriseController.getId(user.id)
        def matches = enterpriseController.match()
        matches.forEach {

            if (id == it.enterprise_id)
                println("Match com o usuario ${it.name} entrar em contato pelo email ${it.email}")
        }
        menu(user)
    }
}
