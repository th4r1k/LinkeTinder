package linketinder.DAO

import groovy.sql.Sql

class CandidateDAO {

    final String url = "jdbc:postgresql://localhost/linketinder"
    final String user = "postgres"
    final String password = "179550"
    final String driver = "org.postgresql.Driver"

    public printAll() {
        def sql = Sql.newInstance(url, user, password, driver)
        sql.eachRow("SELECT * FROM users WHERE category='candidate' ") { row -> println "name: ${row.name} email:${row.email} password:${row.name} doc:${row.doc} country:${row.country} state:${row.state} category:${row.category}"
        }
        sql.close()
    }

    public list() {
        def sql = Sql.newInstance(url, user, password, driver)
        def list = sql.rows("SELECT * FROM users WHERE category='candidate' ")
        sql.close()
        return list
    }

    public like(int jobId, int candidateId) {
        def sql = Sql.newInstance(url, user, password, driver)

        try {
            sql.executeInsert "INSERT INTO candidate_likes (candidate_id, job_id) VALUES (${candidateId}, ${jobId})"

        } catch (Exception e) {

            println e.printStackTrace()
        } finally {
            sql.close()
        }

    }

    public getId(int userId){
        def sql = Sql.newInstance(url, user, password, driver)
        def id = sql.firstRow("SELECT id FROM candidates WHERE user_id=${userId}")
        sql.close()
        return id
    }

    public match(){
        def sql = Sql.newInstance(url, user, password, driver)
        def list = sql.rows("SELECT enterprise_likes.candidate_id, enterprise_likes.enterprise_id, candidate_likes.candidate_id, candidate_likes.job_id, users.name, users.email from enterprise_likes, candidate_likes, users where enterprise_likes.candidate_id = candidate_likes.candidate_id AND enterprise_likes.enterprise_id in (SELECT enterprise_id from jobs where id = candidate_likes.job_id ) AND users.id in (SELECT enterprises.user_id from enterprises where enterprises.id = enterprise_likes.enterprise_id)")

        sql.close()
        return list
    }

    public getEducation(def id){
        def sql = Sql.newInstance(url, user, password, driver)
        def education = sql.firstRow("SELECT education FROM candidates WHERE user_id=${id}")
        sql.close()
        return education
    }

    public editEducation(def id, newEducation){
        def sql = Sql.newInstance(url, user, password, driver)
        def education = sql.executeInsert("UPDATE candidates SET education=${newEducation} WHERE user_id=${id}")
        sql.close()
        return education
    }

    public editAge(def id, newAge){
        def sql = Sql.newInstance(url, user, password, driver)
        def education = sql.executeInsert("UPDATE candidates SET age=${newAge} WHERE user_id=${id}")
        sql.close()
        return education
    }

}
