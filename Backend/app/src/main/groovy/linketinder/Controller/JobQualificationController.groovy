package linketinder.Controller

import linketinder.Model.DAO.Interfaces.JobQualificationDAOInterface
import linketinder.Model.Entity.Job
import linketinder.Model.Entity.JobQualification

class JobQualificationController {
    JobQualificationDAOInterface jobQualificationDAO

    JobQualificationController(JobQualificationDAOInterface jobQualificationDAO){
        this.jobQualificationDAO = jobQualificationDAO
    }

    void getJobDescription(Job job){
        jobQualificationDAO.jobQualifications(job)
    }

    void addJobQualification(JobQualification jobQualification){
        jobQualificationDAO.addJobQualification(jobQualification)
    }

}
