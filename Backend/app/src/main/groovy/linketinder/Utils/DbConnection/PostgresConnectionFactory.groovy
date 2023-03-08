package linketinder.Utils.DbConnection

import groovy.sql.Sql

import java.sql.SQLException

class PostgresConnectionFactory implements IDbConnectionFactory {

    private static PostgresConnectionFactory instance = null

    private final String url = "jdbc:postgresql://localhost/linketinder"
    private final String user = "postgres"
    private final String password = "179550"
    private final String driver = "org.postgresql.Driver"

    static synchronized PostgresConnectionFactory getInstance(){
        if(instance==null){
            instance = new PostgresConnectionFactory()
        }
        return instance
    }

    Sql start() throws SQLException{
       return Sql.newInstance(url, user, password, driver)
   }

}
