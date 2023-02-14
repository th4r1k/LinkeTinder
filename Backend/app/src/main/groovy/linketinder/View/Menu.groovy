package linketinder.View

class Menu {

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
                NewUser.menu()
                break
            case "2":
                linketinder.Controller.User.login()
                break
            case "3":
                linketinder.Controller.Candidate.get()
                break
            case "4":
                linketinder.Controller.Enterprise.get()
                break
            default:
                break

        }
    }
}
