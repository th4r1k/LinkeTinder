package linketinder.Controller

import groovy.json.JsonSlurper
import jakarta.servlet.annotation.WebServlet
import jakarta.servlet.http.HttpServlet
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import linketinder.Model.DAO.Interfaces.CandidateDAOInterface
import linketinder.Model.DAO.Interfaces.UserDAOInterface
import linketinder.Model.Entity.CandidateMatch
import linketinder.Model.Entity.User

import java.util.stream.Collectors

@WebServlet(name = "candidate", urlPatterns = "/candidate")
class CandidateController extends HttpServlet {

    public CandidateDAOInterface candidateDAO
    public UserDAOInterface userDAO

    CandidateController(CandidateDAOInterface candidateDAO, UserDAOInterface userDAO) {
        this.candidateDAO = candidateDAO
        this.userDAO = userDAO
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        getAllCandidates()
        response.SC_OK
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        String requestData = request.getReader().lines().collect(Collectors.joining());
        def dataJson = new JsonSlurper().parseText(requestData)
        User newUser = new User(name: dataJson.name, email: dataJson.email, country: dataJson.email, password: dataJson.password, category: dataJson.category, doc: new BigInteger(dataJson.doc), zipCode: Integer.parseInt(dataJson.zipCode), state: dataJson.state)
        User userCreated = create(newUser)
        response.SC_CREATED

        if (!userCreated) {
            response.SC_BAD_REQUEST
            response.sendError(400)
        }
    }

    User create(User newCandidate) {
        userDAO.create((newCandidate))
    }

    void getAllCandidates() {
        candidateDAO.printAll()
    }

    List<CandidateMatch> match() {
        List<CandidateMatch> matches = candidateDAO.match()
        return matches
    }

    String checkEducation(User user) {
        return candidateDAO.getEducation(user.id)
    }

    void editEducation(int userId, String newEducation) {
        candidateDAO.editEducation(userId, newEducation)
    }

    int getId(int userId) {
        candidateDAO.getId(userId)
    }

}
