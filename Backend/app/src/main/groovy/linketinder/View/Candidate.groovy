package linketinder.View

class Candidate {

    static menu(def candidate) {



        println ""
        println "Ola $candidate.name"
        println "1 - Ver oportunidades"
        println "2 - ver matches"
        println "3 - Minhas competencias"
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
                linketinder.Controller.Candidate.getOpportunities(candidate)
                break
            case "2":
                linketinder.Controller.Candidate.match(candidate)
                break
            case "3":
                CandidateQualifications.menu(candidate)
                break
            default:
                break
        }
    }

}

