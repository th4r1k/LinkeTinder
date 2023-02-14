package linketinder.DAO

import linketinder.Entity.User
import groovy.sql.Sql

class UserDAO {

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

    public create(User newUser) {
        def sql = Sql.newInstance(url, user, password, driver)

        try {
            def id = sql.executeInsert "INSERT INTO users (name, email, password, doc, country, state, category) VALUES (${newUser.name}, ${newUser.email}, ${newUser.password}, ${newUser.doc}, ${newUser.country}, ${newUser.state},${newUser.category} )"
            if (newUser.category == "candidate") {
                sql.execute "INSERT INTO candidates (user_id) VALUES (${id[0][0]})"
            } else {
                sql.execute "INSERT INTO enterprises (user_id) VALUES (${id[0][0]})"
            }
        } catch (Exception e) {
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

    public getUserInfo(String name) {
        def sql = Sql.newInstance(url, user, password, driver)
        def user = sql.firstRow("SELECT * FROM users WHERE name=${name}")
        sql.close()
        return user
    }




}
