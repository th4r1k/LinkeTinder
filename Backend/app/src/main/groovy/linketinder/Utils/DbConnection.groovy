package linketinder.Utils

import groovy.sql.Sql

import java.sql.SQLException

class DbConnection {

    static final String url = "jdbc:postgresql://localhost/linketinder"
    static final String user = "postgres"
    static final String password = "179550"
    static final String driver = "org.postgresql.Driver"

   static start() throws SQLException{

       Sql sql = Sql.newInstance(url, user, password, driver)

   }

}
