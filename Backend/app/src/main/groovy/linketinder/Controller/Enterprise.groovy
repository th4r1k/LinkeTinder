package linketinder.Controller

import linketinder.DAO.CandidateDAO
import linketinder.DAO.EnterpriseDAO
import linketinder.DAO.QualificationDAO
import linketinder.DAO.UserDAO
import linketinder.Entity.User
import linketinder.View.Menu

class Enterprise {

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
        User newEnterprise = new User(name: name, email: email, country: country, zipCode: zipCodex, state: state, doc: docx, password: password, category: "enterprise")
        UserDAO db= new UserDAO()
        db.create(newEnterprise)


        println "Empresa cadastrada com sucesso"
        Menu.start()

    }

    static get() {
        EnterpriseDAO dbEnteprise = new EnterpriseDAO()

        println ""
        println "Lista empresas:"
        dbEnteprise.printAll()
        Scanner input = new Scanner(System.in)
        println("Digite 0 para voltar")
        def cmd = input.nextLine()

        if (cmd) {
            Menu.start()
        }
    }

    static getOpportunities(def enterprise) {
        EnterpriseDAO dbEnterprise = new EnterpriseDAO()
        CandidateDAO dbCandidate = new CandidateDAO()
        QualificationDAO dbQualification = new QualificationDAO()


        def id = dbEnterprise.getId(enterprise.id)

        def candidates = dbCandidate.list()
        candidates.forEach {
            dbQualification.candidate(it)
            Scanner input = new Scanner(System.in)
            println "0 - Like"
            println "1 - Proximo"
            String cmd = input.nextLine()
            if (cmd == "0") {
                dbEnterprise.like(id.id, it.id)
                println("curtiu o candidato")
            }
        }
        println("Nao ha mais usuarios")
        linketinder.View.Enterprise.menu(enterprise)
    }

    static match(def enterprise) {
        EnterpriseDAO dbEnterprise = new EnterpriseDAO()

        def id = dbEnterprise.getId(enterprise.id)
        def matches = dbEnterprise.match()
        matches.forEach {

            if (id.id == it.enterprise_id)
                println("Match com o usuario ${it.name} entrar em contato pelo email ${it.email}")
        }
        linketinder.View.Enterprise.menu(enterprise)
    }
}
