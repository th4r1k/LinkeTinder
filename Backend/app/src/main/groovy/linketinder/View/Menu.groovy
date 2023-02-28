package linketinder.View

import linketinder.Controller.CandidateController
import linketinder.Controller.EnterpriseController
import linketinder.Controller.UserController
import linketinder.DAO.CandidateDAO
import linketinder.DAO.UserDAO

class Menu {

    static start() {

        println ""
        println "Bem vindo ao LinkeTinder"
        println "1 - Criar novo usuario"
        println "2 - Entrar"
        println "3 - Ver todos candidatos"
        println "4 - Ver todas empresas"
        println "0 - Sair"
        println ""
        println "Digite o codigo do comando"
        Scanner input = new Scanner(System.in)
        String command = input.nextLine()
        UserController userController = new UserController(new UserDAO(), new CandidateController(new CandidateDAO()))

        switch (command) {
            case "0":
                break
            case "1":
                NewUser.menu()
                break
            case "2":
                userController.login()
                break
            case "3":
                userController.candidateController.get()
                break
            case "4":
                EnterpriseController.get()
                break
            default:
                break

        }
    }
}
