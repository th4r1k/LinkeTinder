package linketinder.View

import linketinder.Controller.CandidateController
import linketinder.DAO.CandidateDAO
import linketinder.DAO.UserDAO
import linketinder.Entity.User

class CandidateQualificationsView {

    static menu(User user) {
        println ""
        println "Ola $user.name"
        println "1 - Ver competencias"
        println "2 - Alterar competencias"
        println "0 - Sair"
        println ""
        println "Digite o codigo do comando"
        Scanner input = new Scanner(System.in);
        String command = input.nextLine();
        CandidateController candidateController = new CandidateController(new CandidateDAO(), new UserDAO())


        switch (command) {
            case "0":
                CandidateView.menu(user)
                break
            case "1":
                candidateController.getQualifications(user)
                goBack(user)
                break
            case "2":
                println("funcao nao disponivel no momento")
                goBack(user)
                break
            default:
                break
        }
    }

    static goBack(User candidate) {
        System.out.println("");
        System.out.println("________________________________");
        System.out.println("Digite 1 para voltar ao menu");
        Scanner input = new Scanner(System.in);
        String data = input.nextLine();

        switch (data) {
            case "1":
                menu(candidate)
                break;
            default:
                input.close();
                break;
        }
    }
}
