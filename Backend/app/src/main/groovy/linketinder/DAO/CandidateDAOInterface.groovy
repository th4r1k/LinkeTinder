package linketinder.DAO

interface CandidateDAOInterface {
    void printAll()
    def list()
    void like(int jobId, int candidateId)
    int getId(int userId)
    def match()
    String getEducation(int userId)
    def editEducation(int userId, String newEducation)
    def editAge(int userId, int newAge)

}
