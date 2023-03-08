package linketinder.Controller


import linketinder.Model.DAO.Interfaces.CandidateQualificationDAOInterface
import linketinder.Model.Entity.CandidateQualification
import linketinder.Model.Entity.User

class CandidateQualificationController {

    CandidateQualificationDAOInterface candidateQualificationDAO

    CandidateQualificationController(CandidateQualificationDAOInterface candidateQualificationDAO){
        this.candidateQualificationDAO = candidateQualificationDAO
    }

    void getQualifications(User user) {
        candidateQualificationDAO.getQualifications(user)
    }

    void addQualifications(CandidateQualification candidateQualification){
        candidateQualificationDAO.addQualifications(candidateQualification)
    }

    void deleteQualifications(int candidate_id){
        candidateQualificationDAO.deleteQualification(candidate_id)
    }

}
