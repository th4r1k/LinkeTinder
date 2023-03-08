package linketinder.Model.DAO

import groovy.sql.Sql
import linketinder.Model.DAO.Interfaces.JobQualificationDAOInterface
import linketinder.Model.Entity.Job
import linketinder.Model.Entity.JobQualification
import linketinder.Utils.DbConnection.DbConnectionFactory
import linketinder.Utils.DbConnection.IDbConnectionFactory

class JobQualificationDAO implements JobQualificationDAOInterface {

    IDbConnectionFactory dbFactory = DbConnectionFactory.createConnectionFactory("Postgres")

    void jobQualifications(Job job) {
        Sql sql = dbFactory.start()
        println(job.description + sql.rows("SELECT DISTINCT skill FROM jobs, job_qualifications, qualifications WHERE jobs.id=job_qualifications.job_id AND  job_qualifications.qualification_id = qualifications.id AND jobs.id=${job.id}"))
        sql.close()
    }

    void addJobQualification(JobQualification jobQualification){
        Sql sql = dbFactory.start()
        sql.executeInsert("INSERT INTO job_qualifications (job_id, qualification_id) VALUES (${jobQualification.job_id}, ${jobQualification.qualification_id})")
    }

}
