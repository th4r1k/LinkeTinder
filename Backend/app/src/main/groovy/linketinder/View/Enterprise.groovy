package linketinder.View

class Enterprise {

    static menu(def enterprise) {
        println ""
        println "Ola $enterprise.name"
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
                linketinder.Controller.Enterprise.getOpportunities(enterprise)
                break
            case "2":
                linketinder.Controller.Enterprise.match(enterprise)
                break
            default:
                break
        }
    }
}
