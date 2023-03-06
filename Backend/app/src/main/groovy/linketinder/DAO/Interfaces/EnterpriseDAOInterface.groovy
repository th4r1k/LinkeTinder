package linketinder.DAO.Interfaces

interface EnterpriseDAOInterface {
    void printAll()
    def list()
    void like(int enterpriseId, int candidateId)
    int getId(int userId)
    def match()

}