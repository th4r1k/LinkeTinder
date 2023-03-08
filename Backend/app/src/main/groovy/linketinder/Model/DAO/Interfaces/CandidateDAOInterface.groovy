package linketinder.Model.DAO.Interfaces

import linketinder.Model.Entity.CandidateMatch
import linketinder.Model.Entity.User

interface CandidateDAOInterface {
    void printAll()
    List<User> list()
    void like(int jobId, int candidateId)
    int getId(int userId)
    List<CandidateMatch> match()
    String getEducation(int userId)
    void editEducation(int userId, String newEducation)
    def editAge(int userId, int newAge)

}
