package linketinder.View

import linketinder.Controller.EnterpriseController
import linketinder.DAO.EnterpriseDAO
import linketinder.DAO.QualificationDAO
import linketinder.DAO.UserDAO
import linketinder.Entity.User

class EnterpriseView {

    static menu(User user) {
        println ""
        println "Ola $user.name"
        println "1 - Ver candidatos"
        println "2 - ver matches"
        println "0 - Sair"
        println ""
        println "Digite o codigo do comando"
        Scanner input = new Scanner(System.in)
        String command = input.nextLine()
        EnterpriseController enterpriseController = new EnterpriseController(new EnterpriseDAO(), new UserDAO())
        QualificationDAO dbQualification = new QualificationDAO()

        switch (command) {
            case "0":
                Menu.start()
                break
            case "1":
                opportunities(enterpriseController, user, dbQualification)
                break
            case "2":
                matches(enterpriseController, user)
                break
            default:
                break
        }
    }

    static opportunities(EnterpriseController enterpriseController, User user, QualificationDAO dbQualification) {
        Scanner input = new Scanner(System.in)

        int id = enterpriseController.enterpriseDAO.getId(user.id)
        List<User> candidates = enterpriseController.getOpportunities()
        candidates.forEach {
            dbQualification.candidateQualifications(it)
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
        int id = enterpriseController.enterpriseDAO.getId(user.id)
        def matches = enterpriseController.match()
        matches.forEach {

            if (id == it.enterprise_id)
                println("Match com o usuario ${it.name} entrar em contato pelo email ${it.email}")
        }
        menu(user)
    }
}
