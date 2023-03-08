package linketinder.Model.DAO.Interfaces

import linketinder.Model.Entity.Job
import linketinder.Model.Entity.JobQualification

interface JobQualificationDAOInterface {

    void jobQualifications(Job job)
    void addJobQualification(JobQualification jobQualification)
}