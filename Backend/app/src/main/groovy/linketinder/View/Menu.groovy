package linketinder.View

import linketinder.Controller.CandidateController
import linketinder.Controller.EnterpriseController
import linketinder.Controller.UserController
import linketinder.DAO.CandidateDAO
import linketinder.DAO.EnterpriseDAO
import linketinder.DAO.UserDAO
import linketinder.Entity.User

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
        UserController userController = new UserController(new UserDAO(), new CandidateController(new CandidateDAO(), new UserDAO()))
        EnterpriseController enterpriseController = new EnterpriseController(new EnterpriseDAO(), new UserDAO())

        switch (command) {
            case "0":
                break
            case "1":
                NewUserView.menu()
                break
            case "2":
                login(userController)
                break
            case "3":
                printAllCandidates(userController)
                break
            case "4":
                printAllEnterprises(enterpriseController)
                break
            default:
                break
        }
    }

    static login(UserController userController) {
        println "Digite o nome de usuario para fazer login:"
        Scanner input = new Scanner(System.in)
        String name = input.nextLine()
        User user = userController.login(name)
        if (user) {
            if (user.category == "candidate") {
                String education = userController.candidateController.checkEducation(user)
                checkCandidateEducation(userController, user, education)

            } else if (user.category == "enterprise") {
                EnterpriseView.menu(user)
            }
        } else {
            println "Usuario nao encontrado"
            login(userController)
        }
    }

    static checkCandidateEducation(UserController userController, User user, String education) {
        int id = userController.candidateController.candidateDAO.getId(user.id)
        if (education) {
            CandidateView.menu(user)
        } else {
            FormsView.newCandidateQualificationForm(user, id)
            CandidateView.menu(user)
        }
    }

    static printAllCandidates(UserController userController) {
        Scanner input = new Scanner(System.in)

        println ""
        println "Lista de candidatos:"
        userController.candidateController.getAllCandidates()
        println("Digite 0 para voltar")
        String cmd = input.nextLine()

        if (cmd) {
            start()
        }
    }

    static printAllEnterprises(EnterpriseController enterpriseController){
        Scanner input = new Scanner(System.in)
        println ""
        println "Lista empresas:"
        enterpriseController.getAllEnterprises()
        println("Digite 0 para voltar")
        String cmd = input.nextLine()

        if (cmd) {
            start()
        }
    }

}
