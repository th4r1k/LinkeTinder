package linketinder.View

import linketinder.Controller.CandidateController
import linketinder.Controller.CandidateQualificationController
import linketinder.Controller.UserController
import linketinder.Model.DAO.CandidateDAO
import linketinder.Model.DAO.CandidateQualificationDAO
import linketinder.Model.DAO.UserDAO
import linketinder.Model.Entity.User

class CandidateQualificationsView {

    static menu(User user) {
        CandidateQualificationController candidateQualificationController = new CandidateQualificationController(new CandidateQualificationDAO())
        Scanner input = new Scanner(System.in)

        println ""
        println "Ola $user.name"
        println "1 - Ver competencias"
        println "2 - Alterar competencias"
        println "0 - Sair"
        println ""
        println "Digite o codigo do comando"
        String command = input.nextLine()

        switch (command) {
            case "0":
                CandidateView.menu(user)
                break
            case "1":
                candidateQualificationController.getQualifications(user)
                goBack(user)
                break
            case "2":
                changeQualificationMenu(user)
                break
            default:
                break
        }
    }

    static changeQualificationMenu(User user){
        CandidateController candidateController = new CandidateController(new CandidateDAO(), new UserDAO())
        CandidateQualificationController candidateQualificationController = new CandidateQualificationController(new CandidateQualificationDAO())

        println "1 - Alterar education"
        println "2 - Alterar skills"
        println "0 - Sair"
        println ""
        println "Digite o codigo do comando"
        Scanner input = new Scanner(System.in)

        String command = input.nextLine()

        switch (command) {
            case "0":
                menu(user)
                break
            case "1":
                println("Descreva o novo valor para education")
                String newEducation = input.nextLine()
                candidateController.editEducation(user.id, newEducation)
                goBack(user)
                break
            case "2":
                int candidate_id = candidateController.getId(user.id)
                candidateQualificationController.deleteQualifications(candidate_id)
                FormsView.addCandidateSkill(user, candidate_id)
                goBack(user)
                break
            default:
                break
        }
    }

    static goBack(User candidate) {
        System.out.println("")
        System.out.println("________________________________")
        System.out.println("Digite 1 para voltar ao menu")
        Scanner input = new Scanner(System.in)
        String data = input.nextLine()

        switch (data) {
            case "1":
                menu(candidate)
                break
            default:
                input.close()
                break
        }
    }
}
