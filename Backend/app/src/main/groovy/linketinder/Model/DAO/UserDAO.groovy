package linketinder.Model.DAO

import groovy.sql.GroovyRowResult
import linketinder.Model.DAO.Interfaces.UserDAOInterface
import linketinder.Model.Entity.User
import groovy.sql.Sql
import linketinder.Utils.DbConnection.DbConnectionFactory
import linketinder.Utils.DbConnection.IDbConnectionFactory

class UserDAO implements UserDAOInterface {

    IDbConnectionFactory DbFactory = DbConnectionFactory.createConnectionFactory("Postgres")

    void getUsers() {
        Sql sql = DbFactory.start()
        sql.eachRow("SELECT * FROM users") { row -> println "name: ${row.name} email:${row.email} password:${row.name} doc:${row.doc} country:${row.country} state:${row.state} category:${row.category}"
        }
        sql.close()
    }

    User create(User newUser) {
        Sql sql = DbFactory.start()
        try {
            List<List<User>> user = sql.executeInsert("INSERT INTO users (name, email, country, state, password, category, doc, zipcode) VALUES (${newUser.name}, ${newUser.email}, ${newUser.country}, ${newUser.state}, ${newUser.password}, ${newUser.category}, ${newUser.doc}, ${newUser.zipCode})") as List<List<User>>
            int userId = user[0][0] as int
            if (newUser.category == "candidate") {
                sql.executeInsert "INSERT INTO candidates (user_id) VALUES (${userId})"
            } else {
                sql.executeInsert "INSERT INTO enterprises (user_id) VALUES (${userId})"
            }
            User userObject = new User(id:userId, name:user[0][1], email: user[0][2], password: user[0][3], doc:user[0][4] as BigInteger, zipCode: user[0][5] as int, country: user[0][6], state: user[0][7], category: user[0][8]   )

            return userObject
        } catch (Exception e) {
            println e.printStackTrace()
        } finally {
            sql.close()
        }
    }

    void deleteUser(int id, String category) {
        Sql sql = DbFactory.start()
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
        Sql sql = DbFactory.start()
        GroovyRowResult result = sql.firstRow("SELECT * FROM users WHERE name=${name}")
        User user = new User(id: result.id as int, name: result.name, email: result.email, country: result.country, password: result.password, zipCode: result.zipCode as int, doc: result.doc as BigInteger, state: result.state, category: result.category)
        sql.close()
        return user
    }

}
