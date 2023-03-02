package linketinder.DAO

import groovy.sql.GroovyRowResult
import groovy.sql.Sql
import linketinder.Entity.CandidateMatch
import linketinder.Entity.User
import linketinder.Utils.DbConnection

class CandidateDAO implements CandidateDAOInterface {

    void printAll() {
        Sql sql = DbConnection.start()
        sql.eachRow("SELECT * FROM users WHERE category='candidate' ") { row -> println "name: ${row.name} email:${row.email} password:${row.name} doc:${row.doc} country:${row.country} state:${row.state} category:${row.category}"
        }
        sql.close()
    }

    List<User> list() {
        Sql sql = DbConnection.start()
        List<User> list = sql.rows("SELECT * FROM users WHERE category='candidate' ") as List<User>
        sql.close()
        return list
    }

    void like(int jobId, int candidateId) {
        Sql sql = DbConnection.start()

        try {
            sql.executeInsert "INSERT INTO candidate_likes (candidate_id, job_id) VALUES (${candidateId}, ${jobId})"

        } catch (Exception e) {

            println e.printStackTrace()
        } finally {
            sql.close()
        }
    }

    int getId(int userId){
        Sql sql = DbConnection.start()
        GroovyRowResult object = sql.firstRow("SELECT id FROM candidates WHERE user_id=${userId}")
        sql.close()
        return object.id as int
    }

    List<CandidateMatch> match(){
        Sql sql = DbConnection.start()
        List<CandidateMatch> list = sql.rows('''SELECT enterprise_likes.candidate_id, enterprise_likes.enterprise_id, candidate_likes.candidate_id, candidate_likes.job_id, users.name, users.email
 FROM enterprise_likes, candidate_likes, users 
 WHERE enterprise_likes.candidate_id = candidate_likes.candidate_id 
 AND enterprise_likes.enterprise_id IN (SELECT enterprise_id FROM jobs WHERE id = candidate_likes.job_id )
 AND users.id IN (SELECT enterprises.user_id FROM enterprises WHERE enterprises.id = enterprise_likes.enterprise_id)''') as List<CandidateMatch>

        sql.close()
        return list
    }

    String getEducation(int userId){
        Sql sql = DbConnection.start()
        GroovyRowResult object = sql.firstRow("SELECT education FROM candidates WHERE user_id=${userId}")
        sql.close()
        return object.education
    }

    def editEducation(int userId, String newEducation){
        Sql sql = DbConnection.start()
        List<List<Object>> education = sql.executeInsert("UPDATE candidates SET education=${newEducation} WHERE user_id=${userId}")
        sql.close()
        return education
    }

    def editAge(int userId, int newAge){
        Sql sql = DbConnection.start()
        List<List<Object>> education = sql.executeInsert("UPDATE candidates SET age=${newAge} WHERE user_id=${userId}")
        sql.close()
        return education
    }

}
