package linketinder.Controller

import linketinder.DAO.Interfaces.CandidateDAOInterface
import linketinder.DAO.JobDAO
import linketinder.DAO.QualificationDAO
import linketinder.DAO.Interfaces.UserDAOInterface
import linketinder.Entity.CandidateMatch
import linketinder.Entity.Job
import linketinder.Entity.User


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

    List<Job> getOpportunities() {
        JobDAO dbJob = new JobDAO()
        List<Job> jobs = dbJob.list()
        return jobs
    }

     List<CandidateMatch> match() {
        List<CandidateMatch> matches = candidateDAO.match()
        return matches
    }

    void getQualifications(User user) {
        QualificationDAO dbQualiication = new QualificationDAO()
        dbQualiication.candidateQualifications(user)
    }

    String checkEducation(User user) {
        return candidateDAO.getEducation(user.id)
    }

}
