package linketinder.Controller

import groovy.json.JsonSlurper
import jakarta.servlet.annotation.WebServlet
import jakarta.servlet.http.HttpServlet
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import linketinder.Model.DAO.Interfaces.JobDAOInterface
import linketinder.Model.Entity.Job

import java.util.stream.Collectors

@WebServlet(name = "job", urlPatterns = "/job")
class JobController extends HttpServlet {

    JobDAOInterface jobDAO

    JobController(JobDAOInterface jobDAO){
        this.jobDAO = jobDAO
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        listAllJobs()
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        String requestData = request.getReader().lines().collect(Collectors.joining());
        def dataJson = new JsonSlurper().parseText(requestData)

        Job newJob = new Job(enterprise_id: dataJson.enterprise_id, description: dataJson.description)
        println(createJob(newJob))
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
