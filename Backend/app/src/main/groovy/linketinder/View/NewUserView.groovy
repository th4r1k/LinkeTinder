package linketinder.View

import linketinder.Controller.CandidateController
import linketinder.Controller.EnterpriseController
import linketinder.DAO.CandidateDAO
import linketinder.DAO.EnterpriseDAO
import linketinder.DAO.UserDAO
import linketinder.Entity.User

class NewUserView {

    static void menu() {

        println "Criar novo usuario"
        println "1 - Candidato"
        println "2 - Empresa"
        println "0 - Voltar ao menu anterior"
        println ""
        println "Digite o codigo do comando"
        Scanner input = new Scanner(System.in)
        String command = input.nextLine()
        CandidateController candidateController = new CandidateController(new CandidateDAO(), new UserDAO())
        EnterpriseController enterpriseController = new EnterpriseController(new EnterpriseDAO(), new UserDAO())

        switch (command) {
            case "0":
                Menu.start()
                break
            case "1":
                createNewCandidate(candidateController)
                break
            case "2":
                createNewEnterprise(enterpriseController)
                break
            default:
                break
        }
    }

    static void createNewCandidate(CandidateController candidateController) {
        User newCandidate = FormsView.registrationForm("candidate")
        if (candidateController.create(newCandidate)) {
            println "Candidato cadastrado com sucesso"
            Menu.start()
        }
    }

    static void createNewEnterprise(EnterpriseController enterpriseController) {
        User newEnterprise = FormsView.registrationForm("enterprise")
        if (enterpriseController.create(newEnterprise)) {
            println "Empresa cadastrada com sucesso"
            Menu.start()
        }
    }

}
