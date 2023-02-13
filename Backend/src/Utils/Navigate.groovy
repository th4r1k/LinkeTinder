package Utils

import Data.Candidates
import Data.Enterprises
import Entity.Candidate
import Entity.Enterprise

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
                newUser()
                break
            case "2":
                login()
                break
            case "3":
                getCandidates()
                break
            case "4":
                getEnterprises()
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
                break
            case "1":
                createCandidate()
                break
            case "2":
                createEnterprise()
                break
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
        Candidate candidate = new Candidate()

        switch (command) {
            case "0":
                start()
                break
            case "1":
                candidate.getOpportunities(name)
                break
            case "2":
                candidate.match(name)
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
        Enterprise enterprise = new Enterprise()

        switch (command) {
            case "0":
                start()
                break
            case "1":
                enterprise.getOpportunities(name)
                break
            case "2":
                enterprise.match(name)
                break
            default:
                break
        }
    }


    static createCandidate() {
        Scanner input = new Scanner(System.in)

        println "Digite o nome"
        String name = input.nextLine()
        while (!(name =~ /[A-z]{4,15}/)) {
            println "Digite o nome"
            name = input.nextLine()
        }

<<<<<<< Updated upstream
=======
        println "Digite a senha"
        String password = input.nextLine()
        while (password == "") {
            println "Digite a senha"
            password = input.nextLine()
        }

>>>>>>> Stashed changes
        println "Digite o CPF"
        def doc
        try {
            doc = input.nextLine()
        }
        catch (NumberFormatException e) {
            println('Apenas numeros sao aceitos')
        }

        while (!(doc ==~ /([0-9]{2}[\.]?[0-9]{3}[\.]?[0-9]{3}[\/]?[0-9]{4}[-]?[0-9]{2})|([0-9]{3}[\.]?[0-9]{3}[\.]?[0-9]{3}[-]?[0-9]{2})/)) {
            println "Digite o CPF"
            try {
                doc = input.nextLine()
            }
            catch (NumberFormatException e) {
                println('Apenas numeros sao aceitos')
            }
        }

        println "Digite o email"
        String email = input.nextLine()
        while (!(email =~ /[\w-\.]+@([\w-]+\.)+[\w-]{2,4}/)) {
            println "Digite o email"
            email = input.nextLine()
        }

        println "Digite a idade"
        int age = Integer.parseInt(input.nextLine())
        while (age == "") {
            println "Digite a idade"
            age = Integer.parseInt(input.nextLine())
        }

        println "Digite o pais"
        String country = input.nextLine()
        while (!(country =~ /[A-z]{4,15}/)) {
            println "Digite o pais"
            country = input.nextLine()
        }

        println "Digite o estado"
        String state = input.nextLine()
        while (!(state =~ /[A-z]{2,15}/)) {
            println "Digite o estado"
            state = input.nextLine()
        }

        println "Digite o Cep"
        def zipCode
        try {
            zipCode = input.nextLine()
        }
        catch (NumberFormatException e) {
            println('Apenas numeros sao aceitos')
        }
        while (!(zipCode ==~ /^[0-9]{5}-?[0-9]{3}$/)) {
            println "Digite o Cep"
            try {
                zipCode = input.nextLine()
            }
            catch (NumberFormatException e) {
                println('Apenas numeros sao aceitos')
            }
        }

<<<<<<< Updated upstream
        println "Quais das habilidades possue? "
        String qualification = input.nextLine();
        while (qualification == "") {
            println "Digite o estado"
            qualification = input.nextLine()
        }

        Candidate newCandidate = new Candidate(name: name, email: email, age: age, country: country, zipCode: zipCode, state: state, doc: doc, qualification: qualification.split(","))
//        Candidate newCandidate = new Candidate(name, email, country, state, doc, zipCode, age, [qualification])
        newCandidate.create(newCandidate)
=======
        def docx = new BigInteger(doc)
        def zipCodex = Integer.parseInt(zipCode)
        User newCandidate = new User(name: name, email: email, country: country, zipCode: zipCodex, state: state, doc: docx, password: password, category: "candidate")
        Db db = new Db()
        db.createUser(newCandidate)
>>>>>>> Stashed changes

        println "Candidato cadastrado com sucesso"
        start()

    }


    static createEnterprise() {
        Scanner input = new Scanner(System.in);

        println "Digite o nome"
<<<<<<< Updated upstream
        String name = input.nextLine();
        while (name == "") {
            println "Digite o nome"
            name = input.nextLine();
        }
        println "Digite o CNPJ"
        int doc = Integer.parseInt(input.nextLine());
        while (doc == "") {
            println "Digite o CNPJ"
            doc = Integer.parseInt(input.nextLine());
        }

        println "Digite o email"
        String email = input.nextLine();
        while (email == "") {
=======
        String name = input.nextLine()
        while (!(name =~ /[A-z]{4,15}/)) {
            println "Digite o nome"
            name = input.nextLine()
        }

        println "Digite a senha"
        String password = input.nextLine()
        while (password == "") {
            println "Digite a senha"
            password = input.nextLine()
        }

        println "Digite o CNPJ"
        def doc
        try {
            doc = input.nextLine()
        }
        catch (NumberFormatException e) {
            println('Apenas numeros sao aceitos')
        }

        while (!(doc ==~ /([0-9]{2}[\.]?[0-9]{3}[\.]?[0-9]{3}[\/]?[0-9]{4}[-]?[0-9]{2})|([0-9]{3}[\.]?[0-9]{3}[\.]?[0-9]{3}[-]?[0-9]{2})/)) {
            println "Digite o CNPJ"
            try {
                doc = input.nextLine()
            }
            catch (NumberFormatException e) {
                println('Apenas numeros sao aceitos')
            }
        }

        println "Digite o email"
        String email = input.nextLine()
        while (!(email =~ /[\w-\.]+@([\w-]+\.)+[\w-]{2,4}/)) {
>>>>>>> Stashed changes
            println "Digite o email"
            email = input.nextLine();
        }

        println "Digite o pais"
<<<<<<< Updated upstream
        String country = input.nextLine();
        while (country == "") {
=======
        String country = input.nextLine()
        while (!(country =~ /[A-z]{4,15}/)) {
>>>>>>> Stashed changes
            println "Digite o pais"
            country = input.nextLine();
        }

        println "Digite o estado"
<<<<<<< Updated upstream
        String state = input.nextLine();
        while (state == "") {
=======
        String state = input.nextLine()
        while (!(state =~ /[A-z]{2,15}/)) {
>>>>>>> Stashed changes
            println "Digite o estado"
            state = input.nextLine();
        }

        println "Digite o Cep"
        def zipCode
        try {
            zipCode = input.nextLine()
        }
        catch (NumberFormatException e) {
            println('Apenas numeros sao aceitos')
        }
        while (!(zipCode ==~ /^[0-9]{5}-?[0-9]{3}$/)) {
            println "Digite o Cep"
            try {
                zipCode = input.nextLine()
            }
            catch (NumberFormatException e) {
                println('Apenas numeros sao aceitos')
            }
        }

<<<<<<< Updated upstream
        Enterprise newEnterprise = new Enterprise(name: name, email: email, country: country, zipCode: zipCode, state: state, doc: doc)
        newEnterprise.create(newEnterprise)
=======
        def docx = new BigInteger(doc)
        def zipCodex = Integer.parseInt(zipCode)
        User newEnterprise = new User(name: name, email: email, country: country, zipCode: zipCodex, state: state, doc: docx, password: password, category: "enterprise")
        Db db = new Db()
        db.createUser(newEnterprise)

>>>>>>> Stashed changes
        println "Empresa cadastrada com sucesso"
        start()

    }


    static login() {

        println ""
        println "Lista de empresas:"
        Enterprises.list.forEach { println it.name }

        println "Lista de candidatos:"
        Candidates.list.forEach { println it.name }

        Scanner input = new Scanner(System.in);
        println "Digite algum dos nomes acima para fazer login:"
        String name = input.nextLine();
        verify(name)

    }


    static verify(String name) {

        List<Candidate> candidato = Candidates.list.findAll { it.name == name }
        List<Candidate> empresa = Enterprises.list.findAll { it.name == name }
        if (candidato) {
            candidateMenu(name)
        } else if(empresa) {
            enterpriseMenu(name)
        } else{
            println "Usuario nao encontrado"
            login()
        }
    }


    static getCandidates() {
        println ""
        println "Lista de candidatos:"
        Candidates.list.forEach { println it.name }
        println("Digite 0 para voltar")
        Scanner input = new Scanner(System.in);
        def cmd = input.nextLine()


        if (cmd) {
            start()
        }
    }


    static getEnterprises() {
        println ""
        println "Lista empresas:"
        Enterprises.list.forEach { println it.name }
        Scanner input = new Scanner(System.in);
        println("Digite 0 para voltar")
        def cmd = input.nextLine()

        if (cmd) {
            start()
        }
    }
}
