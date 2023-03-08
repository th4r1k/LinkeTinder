package linketinder.Model.DAO

import groovy.sql.GroovyRowResult
import groovy.sql.Sql
import linketinder.Model.DAO.Interfaces.CandidateDAOInterface
import linketinder.Model.Entity.CandidateMatch
import linketinder.Model.Entity.User
import linketinder.Utils.DbConnection.DbConnectionFactory
import linketinder.Utils.DbConnection.IDbConnectionFactory

class CandidateDAO implements CandidateDAOInterface {

    IDbConnectionFactory dbFactory = DbConnectionFactory.createConnectionFactory("Postgres")

    void printAll() {
        Sql sql = dbFactory.start()
        sql.eachRow("SELECT * FROM users WHERE category='candidate' ") { row -> println "name: ${row.name} email:${row.email} password:${row.name} doc:${row.doc} country:${row.country} state:${row.state} category:${row.category}"
        }
        sql.close()
    }

    List<User> list() {
        Sql sql = dbFactory.start()
        List<User> list = sql.rows("SELECT * FROM users WHERE category='candidate' ") as List<User>
        sql.close()
        return list
    }

    void like(int jobId, int candidateId) {
        Sql sql = dbFactory.start()
        try {
            sql.executeInsert "INSERT INTO candidate_likes (candidate_id, job_id) VALUES (${candidateId}, ${jobId})"

        } catch (Exception e) {

            println e.printStackTrace()
        } finally {
            sql.close()
        }
    }

    int getId(int userId){
        Sql sql = dbFactory.start()
        GroovyRowResult object = sql.firstRow("SELECT id FROM candidates WHERE user_id=${userId}")
        sql.close()
        return object.id as int
    }

    List<CandidateMatch> match(){
        Sql sql = dbFactory.start()
        List<CandidateMatch> list = sql.rows('''SELECT enterprise_likes.candidate_id, enterprise_likes.enterprise_id, candidate_likes.candidate_id, candidate_likes.job_id, users.name, users.email
 FROM enterprise_likes, candidate_likes, users 
 WHERE enterprise_likes.candidate_id = candidate_likes.candidate_id 
 AND enterprise_likes.enterprise_id IN (SELECT enterprise_id FROM jobs WHERE id = candidate_likes.job_id )
 AND users.id IN (SELECT enterprises.user_id FROM enterprises WHERE enterprises.id = enterprise_likes.enterprise_id)''') as List<CandidateMatch>

        sql.close()
        return list
    }

    String getEducation(int userId){
        Sql sql = dbFactory.start()
        GroovyRowResult object = sql.firstRow("SELECT education FROM candidates WHERE user_id=${userId}")
        sql.close()
        return object.education
    }

    void editEducation(int userId, String newEducation){
        Sql sql = dbFactory.start()
        sql.executeInsert("UPDATE candidates SET education=${newEducation} WHERE user_id=${userId}")
        sql.close()
    }

    def editAge(int userId, int newAge){
        Sql sql = dbFactory.start()
        List<List<Object>> education = sql.executeInsert("UPDATE candidates SET age=${newAge} WHERE user_id=${userId}")
        sql.close()
        return education
    }

}
