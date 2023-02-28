package linketinder.View

import linketinder.Controller.EnterpriseController

class Enterprise {

    static menu(def user) {
        println ""
        println "Ola $user.name"
        println "1 - Ver candidatos"
        println "2 - ver matches"
        println "0 - Sair"
        println ""
        println "Digite o codigo do comando"
        Scanner input = new Scanner(System.in);
        String command = input.nextLine();

        switch (command) {
            case "0":
                Menu.start()
                break
            case "1":
                EnterpriseController.getOpportunities(user)
                break
            case "2":
                EnterpriseController.match(user)
                break
            default:
                break
        }
    }
}
