package linketinder.DAO

import groovy.sql.Sql
import linketinder.Entity.Job
import linketinder.Entity.User
import linketinder.Utils.DbConnection.PostgresConnectionFactory

class QualificationDAO {

    void getQualifications() {
        Sql sql = PostgresConnectionFactory.start()
        sql.eachRow("SELECT * FROM qualifications") { qualification -> println "name: ${qualification.name}"
        }
        sql.close()
    }

    void createQualifications(String skill) {
        Sql sql = PostgresConnectionFactory.start()

        try {
            List<List<Object>> id = sql.executeInsert "INSERT INTO qualifications (skill) VALUES (${skill})"
        } catch (Exception e) {
            println "Falha ao criar nova competencia"
        } finally {
            sql.close()
        }
    }

    void deleteQualification(String skill) {
        Sql sql = PostgresConnectionFactory.start()
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

    void candidateQualifications(User user) {
        Sql sql = PostgresConnectionFactory.start()
        print(sql.firstRow("SELECT education FROM candidates WHERE user_id=${user.id}"))
        println( sql.rows("SELECT skill FROM candidates, candidate_qualifications, qualifications where candidates.id=candidate_qualifications.candidate_id  AND candidate_qualifications.qualification_id = qualifications.id AND candidates.user_id=${user.id}"))
        sql.close()
    }

    void jobQualifications(Job job) {
        Sql sql = PostgresConnectionFactory.start()
        println(job.description + sql.rows("SELECT DISTINCT skill FROM jobs, job_qualifications, qualifications WHERE jobs.id=job_qualifications.job_id AND  job_qualifications.qualification_id = qualifications.id AND jobs.id=${job.id}"))
        sql.close()
    }

    void addCandidateQualifications(int candidate_id, int skill_id) {
        Sql sql = PostgresConnectionFactory.start()

        try {
            List<List<Object>> id = sql.executeInsert "INSERT INTO candidate_qualifications (qualification_id, candidate_id) VALUES (${skill_id},${candidate_id})"
        } catch (Exception e) {
            println "Falha ao adicionar nova competencia"
        } finally {
            sql.close()
        }
    }

}
