package DAO

import groovy.sql.Sql

class QualificationDAO {

    final String url = "jdbc:postgresql://localhost/linketinder"
    final String user = "postgres"
    final String password = "179550"
    final String driver = "org.postgresql.Driver"

    public getQualifications() {
        def sql = Sql.newInstance(url, user, password, driver)
        sql.eachRow("SELECT * FROM qualifications") { row -> println "name: ${row.name}"
        }
        sql.close()
    }

    public createQualifications(String skill) {
        def sql = Sql.newInstance(url, user, password, driver)

        try {
            def id = sql.executeInsert "INSERT INTO qualifications (skill) VALUES (${skill})"
        } catch (Exception e) {
            println "Falha ao criar nova competencia"
        } finally {
            sql.close()
        }
    }

    public deleteQualification(String skill) {
        def sql = Sql.newInstance(url, user, password, driver)
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

    public candidate(def list) {
        def sql = Sql.newInstance(url, user, password, driver)
        print(sql.firstRow("SELECT education FROM candidates WHERE user_id=${list.id}"))
        println( sql.rows("SELECT skill FROM candidates, candidate_qualifications, qualifications where candidates.id=candidate_qualifications.candidate_id  AND candidate_qualifications.qualification_id = qualifications.id AND candidates.id=${list.id}"))
        sql.close()
    }

    public job(def list) {
        def sql = Sql.newInstance(url, user, password, driver)
        println(list.description + sql.rows("SELECT DISTINCT skill FROM jobs, job_qualifications, qualifications WHERE jobs.id=job_qualifications.job_id AND  job_qualifications.qualification_id = qualifications.id AND jobs.id=${list.id}"))
        sql.close()
    }

}
