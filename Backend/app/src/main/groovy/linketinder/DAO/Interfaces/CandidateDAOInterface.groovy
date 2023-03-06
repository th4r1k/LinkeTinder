package linketinder.DAO.Interfaces

import linketinder.Entity.CandidateMatch

interface CandidateDAOInterface {
    void printAll()
    def list()
    void like(int jobId, int candidateId)
    int getId(int userId)
    List<CandidateMatch> match()
    String getEducation(int userId)
    def editEducation(int userId, String newEducation)
    def editAge(int userId, int newAge)

}
