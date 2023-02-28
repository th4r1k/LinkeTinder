package linketinder.Controller

import groovy.sql.GroovyRowResult
import linketinder.DAO.CandidateDAOInterface
import linketinder.DAO.JobDAO
import linketinder.DAO.QualificationDAO
import linketinder.DAO.UserDAO
import linketinder.Entity.User
import linketinder.Utils.NewUser
import linketinder.View.Menu


class CandidateController {

    public CandidateDAOInterface candidateDAO

    CandidateController (CandidateDAOInterface candidateDAO){
        this.candidateDAO = candidateDAO
    }

    void create() {

        User newCandidate = NewUser.candidateForm()
        UserDAO db = new UserDAO()
        db.create(newCandidate)

        println "Candidato cadastrado com sucesso"
        Menu.start()
    }

    void get() {
        println ""
        println "Lista de candidatos:"
        candidateDAO.printAll()
        println("Digite 0 para voltar")
        Scanner input = new Scanner(System.in)
        String cmd = input.nextLine()

        if (cmd) {
            Menu.start()
        }
    }

    void getOpportunities(User user) {
        JobDAO dbJob = new JobDAO()
        QualificationDAO dbQualification = new QualificationDAO()

        int id = candidateDAO.getId(user.id)
        List<GroovyRowResult> jobs = dbJob.list()
        jobs.forEach {
            dbQualification.jobQualifications(it)
            Scanner input = new Scanner(System.in)

            println "0 - Like"
            println "1 - Proximo"
            String cmd = input.nextLine()
            if (cmd == "0") {
                candidateDAO.like(it.id as int, id)
            }
        }
        println("Nao ha mais vagas cadastradas no momento")
        linketinder.View.Candidate.menu(user)
    }

    void match(User user) {
        List<GroovyRowResult> matches = candidateDAO.match()
        int id = candidateDAO.getId(user.id)

        matches.forEach {
            if (id == it.candidate_id)
                println("Match com a empresa ${it.name} entrar em contato pelo email ${it.email}")
        }
        linketinder.View.Candidate.menu(user)
    }

    void getQualifications(User user) {
        QualificationDAO dbQualiication = new QualificationDAO()
        dbQualiication.candidateQualifications(user)
    }

    void checkData(User user) {
        int id = candidateDAO.getId(user.id)
        String education = candidateDAO.getEducation(user.id)

        if (education) {
            linketinder.View.Candidate.menu(user)
        } else {
            NewUser.newCandidateQualificationForm(user, id)
            linketinder.View.Candidate.menu(user)

        }
    }

}
