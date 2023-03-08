package linketinder.Controller

import linketinder.Model.DAO.Interfaces.JobDAOInterface
import linketinder.Model.Entity.Job

class JobController {

    JobDAOInterface jobDAO

    JobController(JobDAOInterface jobDAO){
        this.jobDAO = jobDAO
    }

    List<Job> listAllJobs() {
        return jobDAO.list()
    }

    List<Job> listEnterpriseJobs(int enterprise_id) {
        return jobDAO.enterpriseJobList(enterprise_id)
    }

    List<List<Job>> createJob(Job job){
        jobDAO.create(job)
    }

}
