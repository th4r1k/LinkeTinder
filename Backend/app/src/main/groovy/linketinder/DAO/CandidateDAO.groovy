package linketinder.DAO

import groovy.sql.GroovyRowResult
import groovy.sql.Sql
import linketinder.Utils.DbConnection

class CandidateDAO implements CandidateDAOInterface {

    void printAll() {
        Sql sql = DbConnection.start()
        sql.eachRow("SELECT * FROM users WHERE category='candidate' ") { row -> println "name: ${row.name} email:${row.email} password:${row.name} doc:${row.doc} country:${row.country} state:${row.state} category:${row.category}"
        }
        sql.close()
    }

    def list() {
        Sql sql = DbConnection.start()
        List<GroovyRowResult> list = sql.rows("SELECT * FROM users WHERE category='candidate' ")
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

    def match(){
        Sql sql = DbConnection.start()
        List<GroovyRowResult> list = sql.rows("SELECT enterprise_likes.candidate_id, enterprise_likes.enterprise_id, candidate_likes.candidate_id, candidate_likes.job_id, users.name, users.email from enterprise_likes, candidate_likes, users where enterprise_likes.candidate_id = candidate_likes.candidate_id AND enterprise_likes.enterprise_id in (SELECT enterprise_id from jobs where id = candidate_likes.job_id ) AND users.id in (SELECT enterprises.user_id from enterprises where enterprises.id = enterprise_likes.enterprise_id)")

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
        List<List<Object>>  education = sql.executeInsert("UPDATE candidates SET age=${newAge} WHERE user_id=${userId}")
        sql.close()
        return education
    }

}
