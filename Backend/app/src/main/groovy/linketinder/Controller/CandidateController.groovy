package linketinder.Controller

import linketinder.Model.DAO.CandidateDAO
import linketinder.Model.DAO.Interfaces.CandidateDAOInterface
import linketinder.Model.DAO.JobDAO

import linketinder.Model.DAO.Interfaces.UserDAOInterface
import linketinder.Model.Entity.CandidateMatch
import linketinder.Model.Entity.User


 class CandidateController {

    public CandidateDAOInterface candidateDAO
    public UserDAOInterface userDAO

    CandidateController(CandidateDAOInterface candidateDAO, UserDAOInterface userDAO) {
        this.candidateDAO = candidateDAO
        this.userDAO = userDAO
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

     void editEducation(int userId, String newEducation){
        candidateDAO.editEducation(userId, newEducation)
     }

     int getId(int userId){
       candidateDAO.getId(userId)
     }

}
