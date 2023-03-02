package linketinder.DAO

import groovy.sql.GroovyRowResult
import groovy.sql.Sql
import linketinder.Entity.User
import linketinder.Utils.DbConnection

class EnterpriseDAO implements EnterpriseDAOInterface{


    void printAll() {
        Sql sql = DbConnection.start()
        sql.eachRow("SELECT * FROM users WHERE category='enterprise'") { user -> println "name: ${user.name} email:${user.email} password:${user.name} doc:${user.doc} country:${user.country} state:${user.state} category:${user.category}"
        }
        sql.close()
    }

    List<User> list() {
        Sql sql = DbConnection.start()
        List<User> list = sql.rows("SELECT * FROM users WHERE category='enterprise'") as List<User>
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
        GroovyRowResult enterpriseObject = sql.firstRow("SELECT id FROM enterprises WHERE user_id=${userId}")
        sql.close()
        return enterpriseObject.id as int
    }

    def match(){
        Sql sql = DbConnection.start()
        List<GroovyRowResult> list = sql.rows('''SELECT enterprise_likes.candidate_id, enterprise_likes.enterprise_id, candidate_likes.candidate_id, candidate_likes.job_id, users.name, users.email 
FROM enterprise_likes, candidate_likes, users 
WHERE enterprise_likes.candidate_id = candidate_likes.candidate_id 
AND enterprise_likes.enterprise_id IN (SELECT enterprise_id FROM jobs WHERE id = candidate_likes.job_id ) 
AND users.id IN (SELECT candidates.user_id FROM candidates WHERE candidates.id = enterprise_likes.candidate_id)''')

        sql.close()
        return list
    }

}
