package linketinder.DAO

import groovy.sql.GroovyRowResult
import groovy.sql.Sql
import linketinder.Utils.DbConnection

class EnterpriseDAO {


    void printAll() {
        Sql sql = DbConnection.start()
        sql.eachRow("SELECT * FROM users WHERE category='enterprise'") { user -> println "name: ${user.name} email:${user.email} password:${user.name} doc:${user.doc} country:${user.country} state:${user.state} category:${user.category}"
        }
        sql.close()
    }

    def list() {
        Sql sql = DbConnection.start()
        List<GroovyRowResult> list = sql.rows("SELECT * FROM users WHERE category='enterprise'")
        sql.close()
        return list
    }

    void like(int enterpriseId, int candidateId) {
        Sql sql = DbConnection.start()

        try {
            sql.executeInsert "INSERT INTO enterprise_likes (enterprise_id, candidate_id) VALUES (${enterpriseId}, ${candidateId})"

        } catch (Exception e) {

            println e.printStackTrace()
        } finally {
            sql.close()
        }
    }

    int getId(int userId){
        Sql sql = DbConnection.start()
        GroovyRowResult object = sql.firstRow("SELECT id FROM enterprises WHERE user_id=${userId}")
        sql.close()
        return object.id as int
    }

    def match(){
        Sql sql = DbConnection.start()
        List<GroovyRowResult> list = sql.rows("SELECT enterprise_likes.candidate_id, enterprise_likes.enterprise_id, candidate_likes.candidate_id, candidate_likes.job_id, users.name, users.email from enterprise_likes, candidate_likes, users where enterprise_likes.candidate_id = candidate_likes.candidate_id AND enterprise_likes.enterprise_id in (SELECT enterprise_id from jobs where id = candidate_likes.job_id ) AND users.id in (SELECT candidates.user_id from candidates where candidates.id = enterprise_likes.candidate_id)")

        sql.close()
        return list
    }

}
