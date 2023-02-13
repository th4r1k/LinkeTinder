package Controller

import DAO.CandidateDAO
import DAO.EnterpriseDAO
import DAO.UserDAO

class User {

    static login() {
        CandidateDAO dbCandidate = new CandidateDAO()
        EnterpriseDAO dbEnteprise = new EnterpriseDAO()

        def candidatesList = dbCandidate.list()
        def enterpriseList = dbEnteprise.list()
        println ""
        println "Lista de empresas:"


        enterpriseList.forEach { println it.name }

        println "Lista de candidatos:"
        candidatesList.forEach { println it.name }


        Scanner input = new Scanner(System.in);
        println "Digite algum dos nomes acima para fazer login:"
        String name = input.nextLine();

        UserDAO dbUser = new UserDAO()
        def queryResult = dbUser.getUserInfo(name)

        if(queryResult){
            if (queryResult.category == "candidate") {
                Candidate.checkData(queryResult)
            } else if (queryResult.category == "enterprise") {
                View.Enterprise.menu(queryResult)
            }
        }
        else {
            println "Usuario nao encontrado"
            login()
        }

    }
}
