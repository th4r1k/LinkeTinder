package linketinder.Model.DAO

import groovy.sql.GroovyRowResult
import groovy.sql.Sql
import linketinder.Model.DAO.Interfaces.EnterpriseDAOInterface
import linketinder.Model.Entity.User
import linketinder.Utils.DbConnection.DbConnectionFactory
import linketinder.Utils.DbConnection.IDbConnectionFactory

class EnterpriseDAO implements EnterpriseDAOInterface{

    IDbConnectionFactory DbFactory = DbConnectionFactory.createConnectionFactory("Postgres")

    void printAll() {
        Sql sql = DbFactory.start()
        sql.eachRow("SELECT * FROM users WHERE category='enterprise'") { user -> println "name: ${user.name} email:${user.email} password:${user.name} doc:${user.doc} country:${user.country} state:${user.state} category:${user.category}"
        }
        sql.close()
    }

    List<User> list() {
        Sql sql = DbFactory.start()
        List<User> list = sql.rows("SELECT * FROM users WHERE category='enterprise'") as List<User>
        sql.close()
        return list
    }

    void like(int enterpriseId, int candidateId) {
        Sql sql = DbFactory.start()
        try {
            sql.executeInsert "INSERT INTO enterprise_likes (enterprise_id, candidate_id) VALUES (${enterpriseId}, ${candidateId})"
        } catch (Exception e) {
            println e.printStackTrace()
        } finally {
            sql.close()
        }
    }

    int getId(int userId){
        Sql sql = DbFactory.start()
        GroovyRowResult enterpriseObject = sql.firstRow("SELECT id FROM enterprises WHERE user_id=${userId}")
        sql.close()
        return enterpriseObject.id as int
    }

    def match(){
        Sql sql = DbFactory.start()
        List<GroovyRowResult> list = sql.rows('''SELECT enterprise_likes.candidate_id, enterprise_likes.enterprise_id, candidate_likes.candidate_id, candidate_likes.job_id, users.name, users.email 
FROM enterprise_likes, candidate_likes, users 
WHERE enterprise_likes.candidate_id = candidate_likes.candidate_id 
AND enterprise_likes.enterprise_id IN (SELECT enterprise_id FROM jobs WHERE id = candidate_likes.job_id ) 
AND users.id IN (SELECT candidates.user_id FROM candidates WHERE candidates.id = enterprise_likes.candidate_id)''')

        sql.close()
        return list
    }

    static class JobQualificationDAO {
    }
}
