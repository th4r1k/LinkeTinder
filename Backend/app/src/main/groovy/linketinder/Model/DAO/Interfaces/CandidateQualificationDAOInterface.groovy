package linketinder.Model.DAO.Interfaces

import linketinder.Model.Entity.CandidateQualification
import linketinder.Model.Entity.User

interface CandidateQualificationDAOInterface {

    void getQualifications(User user)
    void addQualifications(CandidateQualification candidateQualification)
    void updateQualifications(CandidateQualification candidateQualification)
    void deleteQualification(int candidate_id)

}