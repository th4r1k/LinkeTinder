package linketinder.Controller

import groovy.json.JsonSlurper
import jakarta.servlet.annotation.WebServlet
import jakarta.servlet.http.HttpServlet
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import linketinder.Model.DAO.Interfaces.JobQualificationDAOInterface
import linketinder.Model.Entity.Job
import linketinder.Model.Entity.JobQualification

import java.util.stream.Collectors

@WebServlet(name = "jqualification", urlPatterns = "/jqualification")
class JobQualificationController extends HttpServlet {
    JobQualificationDAOInterface jobQualificationDAO

    JobQualificationController(JobQualificationDAOInterface jobQualificationDAO){
        this.jobQualificationDAO = jobQualificationDAO
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        String mensagem = "Olá, mundo!";
        request.setAttribute("mensagem", mensagem);
        response.setContentType("text/html")

        PrintWriter out = response.getWriter()
        out.println(mensagem)
        out.close()
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        String requestData = request.getReader().lines().collect(Collectors.joining());
        def dataJson = new JsonSlurper().parseText(requestData)

        JobQualification newJobQualification = new JobQualification(job_id: dataJson.job_id, qualification_id: dataJson.qualification_id)
        addJobQualification(newJobQualification)
    }


    void getJobDescription(Job job){
        jobQualificationDAO.jobQualifications(job)
    }

    void addJobQualification(JobQualification jobQualification){
        jobQualificationDAO.addJobQualification(jobQualification)
    }

}
