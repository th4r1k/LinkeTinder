package linketinder.Controller

import linketinder.DAO.UserDAOInterface
import linketinder.Entity.User

class UserController {

    public UserDAOInterface userDAO
    public CandidateController candidateController

    UserController (UserDAOInterface userDAO, CandidateController candidateController){
        this.userDAO = userDAO
        this.candidateController = candidateController
    }

    void login() {

        Scanner input = new Scanner(System.in)
        println "Digite o nome de usuario para fazer login:"
        String name = input.nextLine()

        User user = userDAO.getUserInfo(name)

        if(user){
            if (user.category == "candidate") {
                candidateController.checkData(user)
            } else if (user.category == "enterprise") {
                linketinder.View.Enterprise.menu(user)
            }
        }
        else {
            println "Usuario nao encontrado"
            login()
        }

    }
}
