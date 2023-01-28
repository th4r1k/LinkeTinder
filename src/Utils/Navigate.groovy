package Utils

class Navigate {

    static start() {

        println ""
        println "Bem vindo ao LinkeTinder"
        println "1 - Criar novo usuario"
        println "2 - Entrar"
        println "3 - Ver todos candidatos"
        println "4 - Ver todas empresas"
        println "0 - Sair"
        println ""
        println "Digite o codigo do comando"
        Scanner input = new Scanner(System.in);
        String command = input.nextLine();


        switch (command) {
            case "0":
                break
            case "1":
//                newUser()
                break
            case "2":
//                login()
                break
            case "3":
//                getCandidates()
                break
            case "4":
//                getEnterprises()
                break
            default:
                break

        }
    }


    static void newUser() {

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
                start()
            case "1":
//                createCandidate()
//                newUser()

            case "2":
//                createEnterprise()
//                newUser()
            default:
                break
        }
    }

    static candidateMenu(String name) {
        println ""
        println "Ola $name"
        println "1 - Ver oportunidades"
        println "2 - ver matches"
        println "0 - Sair"
        println ""
        println "Digite o codigo do comando"
        Scanner input = new Scanner(System.in);
        String command = input.nextLine();

        switch (command) {
            case "0":
                start()
                break
            case "1":
//                getOpportunities(name)
//                candidateMenu(name)
                break
            case "2":
//                matches(name)
//                candidateMenu(name)
                break
            default:
                break
        }
    }

    static enterpriseMenu(String name) {
        println ""
        println "Ola $name"
        println "1 - Ver candidatos"
        println "2 - ver matches"
        println "0 - Sair"
        println ""
        println "Digite o codigo do comando"
        Scanner input = new Scanner(System.in);
        String command = input.nextLine();


        switch (command) {
            case "0":
                start()
                break
            case "1":
//                getCandidates(name)
//                enterpriseMenu(name)
                break
            case "2":
//                enterpriseMatches(name)
//                enterpriseMenu(name)
                break
            default:
                break
        }
    }
}
