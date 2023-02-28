package linketinder.Utils

import linketinder.DAO.CandidateDAO
import linketinder.DAO.QualificationDAO
import linketinder.Entity.User

class NewUser {

    static User candidateForm() {
        Scanner input = new Scanner(System.in)
        println "Digite o nome"
        String name = input.nextLine()
        while (!(name =~ Regex.name)) {
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
            doc = new BigInteger(input.nextLine())
        }
        catch (NumberFormatException e) {
            println('Apenas numeros sao aceitos')
        }

        while (!(doc =~ Regex.doc)) {
            println "Digite o CPF"
            try {
                doc = new BigInteger(input.nextLine())
            }
            catch (NumberFormatException e) {
                println('Apenas numeros sao aceitos')
            }
        }

        println "Digite o email"
        String email = input.nextLine()
        while (!(email =~ Regex.email)) {
            println "Digite o email"
            email = input.nextLine()
        }

        println "Digite o pais"
        String country = input.nextLine()
        while (!(country =~ Regex.country)) {
            println "Digite o pais"
            country = input.nextLine()
        }

        println "Digite o estado"
        String state = input.nextLine()
        while (!(state =~ Regex.state)) {
            println "Digite o estado"
            state = input.nextLine()
        }

        println "Digite o Cep"
        def zipCode
        try {
            zipCode = Integer.parseInt(input.nextLine())
        }
        catch (NumberFormatException e) {
            println('Apenas numeros sao aceitos')
        }
        while (!(zipCode =~ Regex.zipCode)) {
            println "Digite o Cep"
            try {
                zipCode = Integer.parseInt(input.nextLine())
            }
            catch (NumberFormatException e) {
                println('Apenas numeros sao aceitos')
            }
        }
        User newCandidate = new User(name: name, email: email, country: country, zipCode: zipCode, state: state, doc: doc as int, password: password, category: "candidate")
        return newCandidate

    }

    static User enterpriseForm() {
        Scanner input = new Scanner(System.in)

        println "Digite o nome"
        String name = input.nextLine()
        while (!(name =~ Regex.name)) {
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
            doc = new BigInteger(input.nextLine())
        }
        catch (NumberFormatException e) {
            println('Apenas numeros sao aceitos')
        }

        while (!(doc ==~ Regex.doc)) {
            println "Digite o CNPJ"
            try {
                doc = new BigInteger(input.nextLine())
            }
            catch (NumberFormatException e) {
                println('Apenas numeros sao aceitos')
            }
        }

        println "Digite o email"
        String email = input.nextLine()
        while (!(email =~ Regex.email)) {
            println "Digite o email"
            email = input.nextLine()
        }

        println "Digite o pais"
        String country = input.nextLine()
        while (!(country =~ Regex.country)) {
            println "Digite o pais"
            country = input.nextLine()
        }

        println "Digite o estado"
        String state = input.nextLine()
        while (!(state =~ Regex.state)) {
            println "Digite o estado"
            state = input.nextLine()
        }

        println "Digite o Cep"
        def zipCode
        try {
            zipCode = Integer.parseInt(input.nextLine())
        }
        catch (NumberFormatException e) {
            println('Apenas numeros sao aceitos')
        }
        while (!(zipCode ==~ Regex.zipCode)) {
            println "Digite o Cep"
            try {
                zipCode = Integer.parseInt(input.nextLine())
            }
            catch (NumberFormatException e) {
                println('Apenas numeros sao aceitos')
            }
        }

        User newEnterprise = new User(name: name, email: email, country: country, zipCode: zipCode, state: state, doc: doc, password: password, category: "enterprise")
        return newEnterprise
    }


    static newCandidateQualificationForm(def user, int id){
        CandidateDAO dbCandidate = new CandidateDAO()
        QualificationDAO dbQualification = new QualificationDAO()
        Scanner input = new Scanner(System.in)
        println("Voce precisar terminar seu cadastro para comecar a navegar")

        println("Qual sua idade?")
        String age = input.nextLine()
        while(!(age ==~ Regex.age)){
            println("Qual sua idade?")
            age = input.nextLine()
        }
        dbCandidate.editAge(user.id as int, Integer.parseInt(age))

        println("Qual sua formacao?")
        String description = input.nextLine()
        while(description == ""){
            println("Qual sua formacao?")
            description = input.nextLine()
        }
        dbCandidate.editEducation(user.id as int, description)

        println("Possui conhecimento em JAVA? (0-NAO/1-SIM)")
        String java = input.nextLine()
        while(!(java ==~ /[01]/)){
            println("Possui conhecimento em JAVA? (0-NAO/1-SIM)")
            java = input.nextLine()
        }
        if(java ==~ /1/){
            dbQualification.addCandidateQualifications(id, 1)
        }

        println("Possui conhecimento em Groovy? (0-NAO/1-SIM)")
        String groovy = input.nextLine()
        while(!(groovy ==~ /[01]/)){
            println("Possui conhecimento em Groovy? (0-NAO/1-SIM)")
            groovy = input.nextLine()
        }
        if(groovy ==~ /1/){
            dbQualification.addCandidateQualifications(id, 2)
        }

        println("Possui conhecimento em Javascript? (0-NAO/1-SIM)")
        String javascript = input.nextLine()
        while(!(javascript ==~ /[01]/)){
            println("Possui conhecimento em Javascript? (0-NAO/1-SIM)")
            javascript = input.nextLine()
        }
        if(javascript ==~ /1/){
            dbQualification.addCandidateQualifications(id, 3)
        }

        println("Possui conhecimento em Phyton? (0-NAO/1-SIM)")
        String phyton = input.nextLine()
        while(!(javascript ==~ /[01]/)){
            println("Possui conhecimento em Phyton? (0-NAO/1-SIM)")
            phyton = input.nextLine()
        }
        if(phyton ==~ /1/){
            dbQualification.addCandidateQualifications(id, 4)
        }
    }

}
