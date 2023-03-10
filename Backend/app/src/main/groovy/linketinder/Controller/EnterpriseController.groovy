package linketinder.Controller

import groovy.sql.GroovyRowResult
import linketinder.Model.DAO.CandidateDAO
import linketinder.Model.DAO.Interfaces.EnterpriseDAOInterface
import linketinder.Model.DAO.Interfaces.UserDAOInterface
import linketinder.Model.Entity.User

class EnterpriseController {

    EnterpriseDAOInterface enterpriseDAO
    UserDAOInterface userDAO

    EnterpriseController(EnterpriseDAOInterface enterpriseDAO, UserDAOInterface userDAO) {
        this.enterpriseDAO = enterpriseDAO
        this.userDAO = userDAO
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

    int getId(int userId){
        enterpriseDAO.getId(userId)
    }
}
