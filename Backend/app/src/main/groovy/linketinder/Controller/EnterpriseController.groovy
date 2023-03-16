package linketinder.Controller

import groovy.json.JsonSlurper
import groovy.sql.GroovyRowResult
import jakarta.servlet.annotation.WebServlet
import jakarta.servlet.http.HttpServlet
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import linketinder.Model.DAO.CandidateDAO
import linketinder.Model.DAO.Interfaces.EnterpriseDAOInterface
import linketinder.Model.DAO.Interfaces.UserDAOInterface
import linketinder.Model.Entity.User

import java.util.stream.Collectors

@WebServlet(name = "enterprise", urlPatterns = "/enterprise")
class EnterpriseController extends HttpServlet {

    EnterpriseDAOInterface enterpriseDAO
    UserDAOInterface userDAO

    EnterpriseController(EnterpriseDAOInterface enterpriseDAO, UserDAOInterface userDAO) {
        this.enterpriseDAO = enterpriseDAO
        this.userDAO = userDAO
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        getAllEnterprises()
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

    User create(User newEnterprise) {
        userDAO.create(newEnterprise)
    }

    void getAllEnterprises() {
        enterpriseDAO.printAll()
    }

    List<User> getOpportunities() {
        CandidateDAO dbCandidate = new CandidateDAO()
        List<User> candidates = dbCandidate.list()
        return candidates
    }

    def match() {
        List<GroovyRowResult> matches = enterpriseDAO.match()
        return matches
    }

    int getId(int userId) {
        enterpriseDAO.getId(userId)
    }
}
