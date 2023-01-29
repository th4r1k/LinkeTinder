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
            case "1":
                createCandidate()
            case "2":
                createEnterprise()
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
        while (name == "") {
            println "Digite o nome"
            name = input.nextLine()
        }

        println "Digite o CPF"
        int doc = Integer.parseInt(input.nextLine())
        while (doc == "") {
            println "Digite o CPF"
            doc = Integer.parseInt(input.nextLine())
        }

        println "Digite o email"
        String email = input.nextLine()
        while (email == "") {
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
        while (country == "") {
            println "Digite o pais"
            country = input.nextLine()
        }

        println "Digite o estado"
        String state = input.nextLine()
        while (state == "") {
            println "Digite o estado"
            state = input.nextLine()
        }

        println "Digite o Cep"
        int zipCode = Integer.parseInt(input.nextLine())
        while (zipCode == "") {
            println "Digite o Cep"
            zipCode = Integer.parseInt(input.nextLine())
        }

        println "Quais das habilidades possue? "
        String qualification = input.nextLine();
        while (qualification == "") {
            println "Digite o estado"
            qualification = input.nextLine()
        }

        Candidate novo = new Candidate()
        novo.create(name, email, age, country, zipCode, state, doc, qualification.split(","))

        println "Candidato cadastrado com sucesso"
        start()

    }


    static createEnterprise() {
        Scanner input = new Scanner(System.in);

        println "Digite o nome"
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
            println "Digite o email"
            email = input.nextLine();
        }

        println "Digite o pais"
        String country = input.nextLine();
        while (country == "") {
            println "Digite o pais"
            country = input.nextLine();
        }

        println "Digite o estado"
        String state = input.nextLine();
        while (state == "") {
            println "Digite o estado"
            state = input.nextLine();
        }

        println "Digite o Cep"
        int zipCode = Integer.parseInt(input.nextLine())
        while (zipCode == "") {
            println "Digite o Cep"
            zipCode = Integer.parseInt(input.nextLine())
        }

        Enterprise newEnterprise = new Enterprise()
        newEnterprise.create(name, email, country, zipCode, state, doc)
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

        List<Candidate> data = Candidates.list.findAll { it.name == name }
        if (data) {
            candidateMenu(name)
        } else {
            enterpriseMenu(name)
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
