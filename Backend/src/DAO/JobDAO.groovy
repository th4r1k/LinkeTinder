package DAO

import groovy.sql.Sql

class JobDAO {

    final String url = "jdbc:postgresql://localhost/linketinder"
    final String user = "postgres"
    final String password = "179550"
    final String driver = "org.postgresql.Driver"

    public list() {
        def sql = Sql.newInstance(url, user, password, driver)
        def list = sql.rows("SELECT * FROM jobs")

        sql.close()
        return list
    }



}
