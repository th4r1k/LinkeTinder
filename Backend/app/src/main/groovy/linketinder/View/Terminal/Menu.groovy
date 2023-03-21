package linketinder.View.Terminal

import linketinder.Controller.CandidateController
import linketinder.Controller.EnterpriseController
import linketinder.Controller.UserController
import linketinder.Model.DAO.CandidateDAO
import linketinder.Model.DAO.EnterpriseDAO
import linketinder.Model.DAO.UserDAO
import linketinder.Model.Entity.User

class Menu {

    static start() {
        UserController userController = new UserController(new UserDAO(), new CandidateController(new CandidateDAO(), new UserDAO()))
        EnterpriseController enterpriseController = new EnterpriseController(new EnterpriseDAO(), new UserDAO())
        Scanner input = new Scanner(System.in)
        boolean quit = false

        showMenu()

        while (!quit) {
            String command = input.nextLine()

            switch (command) {
                case "0":
                    quit = true
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
    }

    static showMenu() {
        println ""
        println "Bem vindo ao LinkeTinder"
        println "1 - Criar novo usuario"
        println "2 - Entrar"
        println "3 - Ver todos candidatos"
        println "4 - Ver todas empresas"
        println "0 - Sair"
        println ""
        println "Digite o codigo do comando"
    }

    static login(UserController userController) {
        Scanner input = new Scanner(System.in)

        println "Digite o nome de usuario para fazer login:"
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
        int id = userController.candidateController.getId(user.id)
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
            showMenu()
        }
    }

    static printAllEnterprises(EnterpriseController enterpriseController) {
        Scanner input = new Scanner(System.in)
        println ""
        println "Lista empresas:"
        enterpriseController.getAllEnterprises()
        println("Digite 0 para voltar")
        String cmd = input.nextLine()

        if (cmd) {
            showMenu()
        }
    }

}
