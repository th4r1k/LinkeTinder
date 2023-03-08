package linketinder.Model.DAO


import groovy.sql.Sql
import linketinder.Model.DAO.Interfaces.JobDAOInterface
import linketinder.Model.Entity.Job
import linketinder.Utils.DbConnection.DbConnectionFactory
import linketinder.Utils.DbConnection.IDbConnectionFactory

class JobDAO implements JobDAOInterface{

    IDbConnectionFactory DbFactory = DbConnectionFactory.createConnectionFactory("Postgres")

    List<Job> list() {
        Sql sql = DbFactory.start()
        List<Job> jobs = sql.rows("SELECT * FROM jobs") as List<Job>

        sql.close()
        return jobs
    }

    List<Job> enterpriseJobList(int enterprise_id) {
        Sql sql = DbFactory.start()
        List<Job> jobs = sql.rows("SELECT * FROM jobs WHERE enterprise_id=${enterprise_id}") as List<Job>

        sql.close()
        return jobs
    }

    List<List<Job>> create(Job job){
        Sql sql = DbFactory.start()
        List<List<Job>> newJob = sql.executeInsert("INSERT INTO jobs (enterprise_id, description) VALUES (${job.enterprise_id}, ${job.description})") as List<List<Job>>
        return newJob
    }

}
