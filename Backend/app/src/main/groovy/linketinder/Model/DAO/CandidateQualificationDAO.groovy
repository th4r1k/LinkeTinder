package linketinder.Model.DAO

import groovy.sql.Sql
import linketinder.Model.DAO.Interfaces.CandidateQualificationDAOInterface
import linketinder.Model.Entity.CandidateQualification
import linketinder.Model.Entity.User
import linketinder.Utils.DbConnection.DbConnectionFactory
import linketinder.Utils.DbConnection.IDbConnectionFactory

class CandidateQualificationDAO implements CandidateQualificationDAOInterface {

    IDbConnectionFactory dbFactory = DbConnectionFactory.createConnectionFactory("Postgres")

    void getQualifications(User user) {
        Sql sql = dbFactory.start()
        print(sql.firstRow("SELECT education FROM candidates WHERE user_id=${user.id}"))
        println(sql.rows("SELECT skill FROM candidates, candidate_qualifications, qualifications where candidates.id=candidate_qualifications.candidate_id  AND candidate_qualifications.qualification_id = qualifications.id AND candidates.user_id=${user.id}"))
        sql.close()
    }

    void addQualifications(CandidateQualification candidateQualification) {
        Sql sql = dbFactory.start()

        try {
            sql.executeInsert "INSERT INTO candidate_qualifications (qualification_id, candidate_id) VALUES (${candidateQualification.qualification_id},${candidateQualification.candidate_id})"
        } catch (Exception e) {
            println "Falha ao adicionar nova competencia"
        } finally {
            sql.close()
        }
    }

    void updateQualifications(CandidateQualification candidateQualification) {
        Sql sql = dbFactory.start()

        try {
            sql.executeInsert "UPDATE candidate_qualifications (qualification_id, candidate_id) VALUES (${candidateQualification.qualification_id},${candidateQualification.candidate_id})"
        } catch (Exception e) {
            println "Falha ao adicionar nova competencia"
        } finally {
            sql.close()
        }
    }

    void deleteQualification(int candidate_id) {
        Sql sql = dbFactory.start()
        try {
            sql.execute "DELETE FROM candidate_qualifications WHERE candidate_id= ${candidate_id}"
        } catch (Exception e) {
            println "Falha ao deletar competencia"
        } finally {
            sql.close()
        }
    }


}
