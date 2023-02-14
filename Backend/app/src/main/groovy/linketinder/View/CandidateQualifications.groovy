package linketinder.View

class CandidateQualifications {

        static menu(def candidate) {
            println ""
            println "Ola $candidate.name"
            println "1 - Ver competencias"
            println "2 - Alterar competencias"
            println "0 - Sair"
            println ""
            println "Digite o codigo do comando"
            Scanner input = new Scanner(System.in);
            String command = input.nextLine();


            switch (command) {
                case "0":
                    Candidate.menu(candidate)
                    break
                case "1":
                    linketinder.Controller.Candidate.getQualifications(candidate)
                    goBack(candidate)
                    break
                case "2":
                    println("funcao nao disponivel no momento")
                    goBack(candidate)
                    break
                default:
                    break
            }
        }

    static goBack(candidate){
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
