package linketinder.DAO

import groovy.sql.GroovyRowResult
import groovy.sql.Sql
import linketinder.Entity.Job
import linketinder.Utils.DbConnection

class JobDAO {

    List<Job> list() {
        Sql sql = DbConnection.start()
        List<Job> jobs = sql.rows("SELECT * FROM jobs") as List<Job>

        sql.close()
        return jobs
    }



}
