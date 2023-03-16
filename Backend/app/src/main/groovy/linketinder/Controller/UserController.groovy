package linketinder.Controller

import groovy.json.JsonSlurper
import jakarta.servlet.annotation.WebServlet
import jakarta.servlet.http.HttpServlet
import jakarta.servlet.http.HttpServletResponse
import linketinder.Model.DAO.Interfaces.UserDAOInterface
import linketinder.Model.Entity.User

import jakarta.servlet.http.HttpServletRequest

import java.util.stream.Collectors


@WebServlet(name = "user", urlPatterns = "/user")
class UserController extends HttpServlet {

    public UserDAOInterface userDAO
    public CandidateController candidateController

    UserController(UserDAOInterface userDAO, CandidateController candidateController) {
        this.userDAO = userDAO
        this.candidateController = candidateController
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        String requestData = request.getReader().lines().collect(Collectors.joining());
        def dataJson = new JsonSlurper().parseText(requestData)

        login(dataJson.user)
        println(userDAO.getUserInfo(dataJson.user))
    }


    User login(String name) {
        userDAO.getUserInfo(name)
    }

}
