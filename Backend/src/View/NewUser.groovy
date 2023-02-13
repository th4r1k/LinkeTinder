package View

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

        switch (command) {
            case "0":
                Menu.start()
                break
            case "1":
                Controller.Candidate.create()
                break
            case "2":
                Controller.Enterprise.create()
                break
            default:
                break
        }
    }
}
