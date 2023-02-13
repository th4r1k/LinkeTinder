package Utils

import Entity.User
import groovy.sql.Sql

class Db {

    final String url = "jdbc:postgresql://localhost/linketinder"
    final String user = "postgres"
    final String password = "179550"
    final String driver = "org.postgresql.Driver"


    public getUsers() {
        def sql = Sql.newInstance(url, user, password, driver)
        sql.eachRow("SELECT * FROM users") { row -> println "name: ${row.name} email:${row.email} password:${row.name} doc:${row.doc} country:${row.country} state:${row.state} category:${row.category}"
        }
        sql.close()

    }

    public createUser(User newUser) {
        def sql = Sql.newInstance(url, user, password, driver)

        try {
            def id = sql.executeInsert "INSERT INTO users (name, email, password, doc, country, state, category) VALUES (${newUser.name}, ${newUser.email}, ${newUser.password}, ${newUser.doc}, ${newUser.country}, ${newUser.state},${newUser.category} )"
            if (newUser.category == "candidate") {
                sql.execute "INSERT INTO candidates (user_id) VALUES (${id[0][0]})"
            } else {
                sql.execute "INSERT INTO enterprises (user_id) VALUES (${id[0][0]})"
            }
        } catch (Exception e) {
//            println "Email ja cadastrado"
            println e.printStackTrace()
        } finally {
            sql.close()
        }
    }

    public deleteUser(int id, String type) {
        def sql = Sql.newInstance(url, user, password, driver)

        try {
            if (type == "candidato") {
                sql.execute "DELETE FROM candidates WHERE user_id=${id}"
            } else {
                sql.execute "DELETE FROM enterprises WHERE user_id=${id}"
            }
            sql.execute "DELETE FROM users WHERE id= ${id}"
        } catch (Exception e) {
            println "Falha ao deletar usuario"
        } finally {
            sql.close()
        }
    }

    public getCandidates() {
        def sql = Sql.newInstance(url, user, password, driver)
        sql.eachRow("SELECT * FROM users WHERE category='candidate' ") { row -> println "name: ${row.name} email:${row.email} password:${row.name} doc:${row.doc} country:${row.country} state:${row.state} category:${row.category}"
        }
        sql.close()
    }

    public getEnterprises() {
        def sql = Sql.newInstance(url, user, password, driver)
        sql.eachRow("SELECT * FROM users WHERE category='enterprise'") { row -> println "name: ${row.name} email:${row.email} password:${row.name} doc:${row.doc} country:${row.country} state:${row.state} category:${row.category}"
        }
        sql.close()
    }

    public getUserInfo(String name) {
        def sql = Sql.newInstance(url, user, password, driver)
        def user = sql.firstRow("SELECT * FROM users WHERE name=${name}")
        sql.close()
        return user
    }

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

    public candidateList() {
        def sql = Sql.newInstance(url, user, password, driver)
        def list = sql.rows("SELECT * FROM users WHERE category='candidate' ")
        sql.close()
        return list
    }

    public enterpriseList() {
        def sql = Sql.newInstance(url, user, password, driver)
        def list = sql.rows("SELECT * FROM users WHERE category='enterprise'")
        sql.close()
        return list
    }

    public jobsDescriptions() {
        def sql = Sql.newInstance(url, user, password, driver)
        def list = sql.rows("SELECT * FROM jobs")

        sql.close()
        return list
    }

    public candidatesQualifications(def list) {
        def sql = Sql.newInstance(url, user, password, driver)
        print(sql.firstRow("SELECT education FROM candidates WHERE user_id=${list.id}"))
        println( sql.rows("SELECT skill FROM candidates, candidate_qualifications, qualifications where candidates.id=candidate_qualifications.candidate_id  AND candidate_qualifications.qualification_id = qualifications.id AND candidates.id=${list.id}"))
        sql.close()
    }


    public jobsQualifications(def list) {
        def sql = Sql.newInstance(url, user, password, driver)
        println(list.description + sql.rows("SELECT DISTINCT skill FROM jobs, job_qualifications, qualifications WHERE jobs.id=job_qualifications.job_id AND  job_qualifications.qualification_id = qualifications.id AND jobs.id=${list.id}"))
        sql.close()
    }

    public candidateLikeJob(int jobId, int candidateId) {
        def sql = Sql.newInstance(url, user, password, driver)

        try {
            sql.executeInsert "INSERT INTO candidate_likes (candidate_id, job_id) VALUES (${candidateId}, ${jobId})"

        } catch (Exception e) {

            println e.printStackTrace()
        } finally {
            sql.close()
        }

    }

    public enterpriseLikeJob(int enterpriseId, int candidateId) {
        def sql = Sql.newInstance(url, user, password, driver)

        try {
            sql.executeInsert "INSERT INTO enterprise_likes (enterprise_id, candidate_id) VALUES (${enterpriseId}, ${candidateId})"

        } catch (Exception e) {

            println e.printStackTrace()
        } finally {
            sql.close()
        }
    }

    public getCandidateId(int userId){
        def sql = Sql.newInstance(url, user, password, driver)
        def id = sql.firstRow("SELECT id FROM candidates WHERE user_id=${userId}")
        sql.close()
        return id
    }

    public getEnterpriseId(int userId){
        def sql = Sql.newInstance(url, user, password, driver)
        def id = sql.firstRow("SELECT id FROM enterprises WHERE user_id=${userId}")
        sql.close()
        return id
    }

    public candidateMatches(){
        def sql = Sql.newInstance(url, user, password, driver)
        def list = sql.rows("SELECT enterprise_likes.candidate_id, enterprise_likes.enterprise_id, candidate_likes.candidate_id, candidate_likes.job_id, users.name, users.email from enterprise_likes, candidate_likes, users where enterprise_likes.candidate_id = candidate_likes.candidate_id AND enterprise_likes.enterprise_id in (SELECT enterprise_id from jobs where id = candidate_likes.job_id ) AND users.id in (SELECT enterprises.user_id from enterprises where enterprises.id = enterprise_likes.enterprise_id)")

        sql.close()
        return list
    }

    public enterpriseMatches(){
        def sql = Sql.newInstance(url, user, password, driver)
        def list = sql.rows("SELECT enterprise_likes.candidate_id, enterprise_likes.enterprise_id, candidate_likes.candidate_id, candidate_likes.job_id, users.name, users.email from enterprise_likes, candidate_likes, users where enterprise_likes.candidate_id = candidate_likes.candidate_id AND enterprise_likes.enterprise_id in (SELECT enterprise_id from jobs where id = candidate_likes.job_id ) AND users.id in (SELECT candidates.user_id from candidates where candidates.id = enterprise_likes.candidate_id)")

        sql.close()
        return list
    }



}