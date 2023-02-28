package linketinder.DAO

import groovy.sql.GroovyRowResult
import linketinder.Entity.User
import groovy.sql.Sql
import linketinder.Utils.DbConnection

class UserDAO implements UserDAOInterface{

    void getUsers() {
        Sql sql = DbConnection.start()
        sql.eachRow("SELECT * FROM users") { row -> println "name: ${row.name} email:${row.email} password:${row.name} doc:${row.doc} country:${row.country} state:${row.state} category:${row.category}"
        }
        sql.close()

    }

    void create(User newUser) {
        Sql sql = DbConnection.start()

        try {
            List<List<Object>> id = sql.executeInsert "INSERT INTO users (name, email, password, doc, zipcode, country, state, category) VALUES (${newUser.name}, ${newUser.email}, ${newUser.password}, ${newUser.zipCode}, ${newUser.doc}, ${newUser.country}, ${newUser.state},${newUser.category} )"
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

    void deleteUser(int id, String category) {
        Sql sql = DbConnection.start()

        try {
            if (category == "candidato") {
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

    User getUserInfo(String name) {
        Sql sql = DbConnection.start()
        GroovyRowResult result = sql.firstRow("SELECT * FROM users WHERE name=${name}")
        User user = new User(id: result.id as int, name: result.name, email: result.email, country: result.country, password: result.password, zipCode: result.zipCode as int, doc: result.doc as BigInteger, state: result.state, category: result.category )
        sql.close()
        return user
    }

}
