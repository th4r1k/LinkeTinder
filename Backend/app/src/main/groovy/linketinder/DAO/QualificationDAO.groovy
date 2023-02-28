package linketinder.DAO

import groovy.sql.Sql
import linketinder.Entity.User
import linketinder.Utils.DbConnection

class QualificationDAO {

    void getQualifications() {
        Sql sql = DbConnection.start()
        sql.eachRow("SELECT * FROM qualifications") { row -> println "name: ${row.name}"
        }
        sql.close()
    }

    void createQualifications(String skill) {
        Sql sql = DbConnection.start()

        try {
            List<List<Object>> id = sql.executeInsert "INSERT INTO qualifications (skill) VALUES (${skill})"
        } catch (Exception e) {
            println "Falha ao criar nova competencia"
        } finally {
            sql.close()
        }
    }

    void deleteQualification(String skill) {
        Sql sql = DbConnection.start()
        try {
            if (type == "candidato") {
                sql.execute "DELETE FROM qualifications where skill=${skill}"
            } else {
                sql.execute "DELETE FROM qualifications where skill=${skill}"
            }
            sql.execute "DELETE FROM users WHERE id= ${id}"
        } catch (Exception e) {
            println "Falha ao deletar competencia"
        } finally {
            sql.close()
        }
    }

    void candidateQualifications(def user) {
        Sql sql = DbConnection.start()
        print(sql.firstRow("SELECT education FROM candidates WHERE user_id=${user.id}"))
        println( sql.rows("SELECT skill FROM candidates, candidate_qualifications, qualifications where candidates.id=candidate_qualifications.candidate_id  AND candidate_qualifications.qualification_id = qualifications.id AND candidates.user_id=${user.id}"))
        sql.close()
    }

    void jobQualifications(def job) {
        Sql sql = DbConnection.start()
        println(job.description + sql.rows("SELECT DISTINCT skill FROM jobs, job_qualifications, qualifications WHERE jobs.id=job_qualifications.job_id AND  job_qualifications.qualification_id = qualifications.id AND jobs.id=${job.id}"))
        sql.close()
    }

    void addCandidateQualifications(int candidate_id, int skill_id) {
        Sql sql = DbConnection.start()

        try {
            List<List<Object>> id = sql.executeInsert "INSERT INTO candidate_qualifications (qualification_id, candidate_id) VALUES (${skill_id},${candidate_id})"
        } catch (Exception e) {
            println "Falha ao adicionar nova competencia"
        } finally {
            sql.close()
        }
    }

}
