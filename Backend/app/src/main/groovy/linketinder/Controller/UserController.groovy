package linketinder.Controller

import linketinder.DAO.Interfaces.UserDAOInterface
import linketinder.Entity.User

class UserController {

    public UserDAOInterface userDAO
    public CandidateController candidateController

    UserController(UserDAOInterface userDAO, CandidateController candidateController) {
        this.userDAO = userDAO
        this.candidateController = candidateController
    }
    User login(String name) {
        userDAO.getUserInfo(name)
    }
}
