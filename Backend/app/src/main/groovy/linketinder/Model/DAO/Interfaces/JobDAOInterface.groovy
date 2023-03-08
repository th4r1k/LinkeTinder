package linketinder.Model.DAO.Interfaces

import linketinder.Model.Entity.Job

interface JobDAOInterface {
    List<Job> list()
    List<Job> enterpriseJobList(int enterprise_id)
    List<List<Job>> create(Job job)



}