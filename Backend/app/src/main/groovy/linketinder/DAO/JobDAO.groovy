package linketinder.DAO

import groovy.sql.GroovyRowResult
import groovy.sql.Sql
import linketinder.Utils.DbConnection

class JobDAO {

    def list() {
        Sql sql = DbConnection.start()
        List<GroovyRowResult> list = sql.rows("SELECT * FROM jobs")

        sql.close()
        return list
    }



}
