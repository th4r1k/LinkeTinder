package linketinder.View

import linketinder.Controller.CandidateController
import linketinder.DAO.CandidateDAO

class Candidate {

    static menu(def user) {

        println ""
        println "Ola $user.name"
        println "1 - Ver oportunidades"
        println "2 - ver matches"
        println "3 - Minhas competencias"
        println "0 - Sair"
        println ""
        println "Digite o codigo do comando"
        Scanner input = new Scanner(System.in);
        String command = input.nextLine();
        CandidateController candidateController = new CandidateController(new CandidateDAO())


        switch (command) {
            case "0":
                Menu.start()
                break
            case "1":
                candidateController.getOpportunities(user)
                break
            case "2":
                candidateController.match(user)
                break
            case "3":
                CandidateQualifications.menu(user)
                break
            default:
                break
        }
    }

}

