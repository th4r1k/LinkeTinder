package linketinder.Controller

import groovy.sql.GroovyRowResult
import linketinder.DAO.CandidateDAO
import linketinder.DAO.EnterpriseDAO
import linketinder.DAO.QualificationDAO
import linketinder.DAO.UserDAO
import linketinder.Entity.User
import linketinder.Utils.NewUser
import linketinder.View.Menu

class EnterpriseController {

    static create() {
        User newEnterprise = NewUser.enterpriseForm()
        UserDAO db= new UserDAO()
        db.create(newEnterprise)

        println "Empresa cadastrada com sucesso"
        Menu.start()

    }

    static get() {
        EnterpriseDAO dbEnteprise = new EnterpriseDAO()

        println ""
        println "Lista empresas:"
        dbEnteprise.printAll()
        Scanner input = new Scanner(System.in)
        println("Digite 0 para voltar")
        def cmd = input.nextLine()

        if (cmd) {
            Menu.start()
        }
    }

    static getOpportunities(User user) {
        EnterpriseDAO dbEnterprise = new EnterpriseDAO()
        CandidateDAO dbCandidate = new CandidateDAO()
        QualificationDAO dbQualification = new QualificationDAO()


        int id = dbEnterprise.getId(user.id)

        List<GroovyRowResult> candidates = dbCandidate.list()
        candidates.forEach {
            dbQualification.candidateQualifications(it)
            Scanner input = new Scanner(System.in)
            println "0 - Like"
            println "1 - Proximo"
            String cmd = input.nextLine()
            if (cmd == "0") {
                dbEnterprise.like(id, it.id as int)
                println("curtiu o candidato")
            }
        }
        println("Nao ha mais usuarios")
        linketinder.View.Enterprise.menu(user)
    }

    static match(User user) {
        EnterpriseDAO dbEnterprise = new EnterpriseDAO()

        int id = dbEnterprise.getId(user.id)
        List<GroovyRowResult> matches = dbEnterprise.match()
        matches.forEach {

            if (id == it.enterprise_id)
                println("Match com o usuario ${it.name} entrar em contato pelo email ${it.email}")
        }
        linketinder.View.Enterprise.menu(user)
    }
}
