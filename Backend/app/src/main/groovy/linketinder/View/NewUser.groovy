package linketinder.View

import linketinder.Controller.CandidateController
import linketinder.Controller.EnterpriseController
import linketinder.DAO.CandidateDAO

class NewUser {

    static void menu() {

        println "Criar novo usuario"
        println "1 - Candidato"
        println "2 - Empresa"
        println "0 - Voltar ao menu anterior"
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
                candidateController.create()
                break
            case "2":
                EnterpriseController.create()
                break
            default:
                break
        }
    }
}
