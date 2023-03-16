package linketinder.Controller

import groovy.json.JsonSlurper
import jakarta.servlet.annotation.WebServlet
import jakarta.servlet.http.HttpServlet
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import linketinder.Model.DAO.Interfaces.CandidateQualificationDAOInterface
import linketinder.Model.Entity.CandidateQualification
import linketinder.Model.Entity.User

import java.util.stream.Collectors

@WebServlet(name = "cqualification", urlPatterns = "/cqualification")
class CandidateQualificationController extends HttpServlet {

    CandidateQualificationDAOInterface candidateQualificationDAO

    CandidateQualificationController(CandidateQualificationDAOInterface candidateQualificationDAO){
        this.candidateQualificationDAO = candidateQualificationDAO
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

        CandidateQualification newCandidateQualification = new CandidateQualification(candidate_id: dataJson.candidate_id, qualification_id: dataJson.qualification_id)
        println(addQualifications(newCandidateQualification))
    }

    protected void doDelete(HttpServletRequest request, HttpServletResponse response) {
        String requestData = request.getReader().lines().collect(Collectors.joining());
        def dataJson = new JsonSlurper().parseText(requestData)

        int candidate_id = dataJson.candidate_id
        deleteQualifications(candidate_id)
    }


    void getQualifications(User user) {
        candidateQualificationDAO.getQualifications(user)
    }

    void addQualifications(CandidateQualification candidateQualification){
        candidateQualificationDAO.addQualifications(candidateQualification)
    }

    void deleteQualifications(int candidate_id){
        candidateQualificationDAO.deleteQualification(candidate_id)
    }

}
