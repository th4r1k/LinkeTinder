package Controller

import DAO.CandidateDAO
import DAO.JobDAO
import DAO.QualificationDAO
import DAO.UserDAO
import Entity.User
import View.Menu

class Candidate {

    static create() {
        Scanner input = new Scanner(System.in)

        println "Digite o nome"
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

        def docx = new BigInteger(doc)
        def zipCodex = Integer.parseInt(zipCode)
        User newCandidate = new User(name: name, email: email, country: country, zipCode: zipCodex, state: state, doc: docx, password: password, category: "candidate")
        UserDAO db= new UserDAO()
        db.create(newCandidate)

        println "Candidato cadastrado com sucesso"
        Menu.start()

    }

    static get() {
        CandidateDAO dbCandidate = new CandidateDAO()
        println ""
        println "Lista de candidatos:"
        dbCandidate.printAll()
        println("Digite 0 para voltar")
        Scanner input = new Scanner(System.in);
        def cmd = input.nextLine()

        if (cmd) {
            Menu.start()
        }
    }

    static getOpportunities(def candidate) {
        CandidateDAO dbCandidate = new CandidateDAO()
        JobDAO dbJob = new JobDAO()
        QualificationDAO dbQualification = new QualificationDAO()

        def id = dbCandidate.getId(candidate.id)
        def jobs = dbJob.list()
        jobs.forEach {
            dbQualification.job(it)
            Scanner input = new Scanner(System.in)

            println "0 - Like"
            println "1 - Proximo"
            String cmd = input.nextLine()
            if (cmd == "0") {
                dbCandidate.like(it.id, id.id)
            }
        }
        println("Nao ha mais vagas cadastradas no momento")
        View.Candidate.menu(candidate)
    }

    static match(def candidate) {
        CandidateDAO dbCandidate = new CandidateDAO()

        def matches = dbCandidate.match()
        def id = dbCandidate.getId(candidate.id)


        matches.forEach {
            if (id.id == it.candidate_id)
                println("Match com a empresa ${it.name} entrar em contato pelo email ${it.email}")
        }
        View.Candidate.menu(candidate)
    }

}
