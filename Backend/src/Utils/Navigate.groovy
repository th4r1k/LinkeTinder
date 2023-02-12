package Utils

import Entity.User

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

    static candidateMenu(def candidate) {
        println ""
        println "Ola $candidate.name"
        println "1 - Ver oportunidades"
        println "2 - ver matches"
        println "0 - Sair"
        println ""
        println "Digite o codigo do comando"
        Scanner input = new Scanner(System.in);
        String command = input.nextLine();
//        Candidate candidate = new Candidate()

        switch (command) {
            case "0":
                start()
                break
            case "1":
                candidateGetOpportunities(candidate)
//                candidate.getOpportunities(name)
                break
            case "2":
                candidateMatch(candidate)
                break
            default:
                break
        }
    }

    static enterpriseMenu(def enterprise) {
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
                start()
                break
            case "1":

                enterpriseGetOpportunities(enterprise)
                break
            case "2":
                enterpriseMatch(enterprise)
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

        println "Digite a senha"
        String password = input.nextLine()
        while (password == "") {
            println "Digite o nome"
            password = input.nextLine()
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


        User newCandidate = new User(name: name, email: email, country: country, zipCode: zipCode, state: state, doc: doc, password: password, category: "candidate")
        Db db = new Db()
        db.createUser(newCandidate)

        println "Candidato cadastrado com sucesso"
        start()

    }


    static createEnterprise() {
        Scanner input = new Scanner(System.in);

        println "Digite o nome"
        String name = input.nextLine()
        while (name == "") {
            println "Digite o nome"
            name = input.nextLine()
        }

        println "Digite a senha"
        String password = input.nextLine()
        while (password == "") {
            println "Digite o nome"
            password = input.nextLine()
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


        User newEnterprise = new User(name: name, email: email, country: country, zipCode: zipCode, state: state, doc: doc, password: password, category: "enterprise")
        Db db = new Db()
        db.createUser(newEnterprise)

        println "Empresa cadastrada com sucesso"
        start()

    }


    static login() {
        Db db = new Db()
        def candidatesList = db.candidateList()
        def enterpriseList = db.enterpriseList()
        println ""
        println "Lista de empresas:"


        enterpriseList.forEach { println it.name }

        println "Lista de candidatos:"
        candidatesList.forEach { println it.name }


        Scanner input = new Scanner(System.in);
        println "Digite algum dos nomes acima para fazer login:"
        String name = input.nextLine();

        def queryResult = db.getUserInfo(name)
        if (queryResult.category == "candidate") {
            candidateMenu(queryResult)
        } else if (queryResult.category == "enterprise") {
            enterpriseMenu(queryResult)
        } else {
            println "Usuario nao encontrado"
            login()
        }

    }

    static getCandidates() {
        Db db = new Db()
        println ""
        println "Lista de candidatos:"
        db.getCandidates()
        println("Digite 0 para voltar")
        Scanner input = new Scanner(System.in);
        def cmd = input.nextLine()

        if (cmd) {
            start()
        }
    }


    static getEnterprises() {
        Db db = new Db()

        println ""
        println "Lista empresas:"
        db.getEnterprises()
        Scanner input = new Scanner(System.in);
        println("Digite 0 para voltar")
        def cmd = input.nextLine()

        if (cmd) {
            start()
        }
    }

    static candidateGetOpportunities(def candidate) {
        Db db = new Db()
        def id = db.getCandidateId(candidate.id)
        def jobs = db.jobsDescriptions()
        jobs.forEach {
            db.jobsQualifications(it)
            Scanner input = new Scanner(System.in)

            println "0 - Like"
            println "1 - Proximo"
            String cmd = input.nextLine()
            if (cmd == "0") {
                db.candidateLikeJob(it.id, id.id)
            }
        }
        println("Nao ha mais vagas cadastradas no momento")
        candidateMenu(candidate)
    }


    static enterpriseGetOpportunities(def enterprise) {
        Db db = new Db()
        def id = db.getEnterpriseId(enterprise.id)

        def candidates = db.candidateList()
        candidates.forEach {
            db.candidatesQualifications(it)
            Scanner input = new Scanner(System.in)
            println "0 - Like"
            println "1 - Proximo"
            String cmd = input.nextLine()
            if (cmd == "0") {
                db.enterpriseLikeJob(id.id, it.id)
                println("curtiu o candidato")
            }
        }
        println("Nao ha mais usuarios")
        enterpriseMenu(enterprise)
    }

    static candidateMatch(def candidate) {
        Db db = new Db()
        def matches = db.candidateMatches()
        def id = db.getCandidateId(candidate.id)


        matches.forEach {
            if (id.id == it.candidate_id)
                println("Match com a empresa ${it.name} entrar em contato pelo email ${it.email}")
        }
        candidateMenu(candidate)
    }

    static enterpriseMatch(def enterprise) {
        Db db = new Db()
        def id = db.getEnterpriseId(enterprise.id)
        def matches = db.enterpriseMatches()
        matches.forEach {

            if (id.id == it.enterprise_id)
                println("Match com o usuario ${it.name} entrar em contato pelo email ${it.email}")
        }
        enterpriseMenu(enterprise)
    }

}