package linketinder.DAO


import groovy.sql.Sql
import linketinder.Entity.Job
import linketinder.Utils.DbConnection.DbConnectionFactory
import linketinder.Utils.DbConnection.IDbConnectionFactory

class JobDAO {

    IDbConnectionFactory DbFactory = DbConnectionFactory.createConnectionFactory("Postgres")


    List<Job> list() {
        Sql sql = DbFactory.start()
        List<Job> jobs = sql.rows("SELECT * FROM jobs") as List<Job>

        sql.close()
        return jobs
    }



}
