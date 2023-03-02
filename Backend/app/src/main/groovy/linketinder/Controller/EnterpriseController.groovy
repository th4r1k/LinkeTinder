package linketinder.Controller

import groovy.sql.GroovyRowResult
import linketinder.DAO.CandidateDAO
import linketinder.DAO.EnterpriseDAOInterface
import linketinder.DAO.UserDAOInterface
import linketinder.Entity.User

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
}
