package linketinder.Utils

class Sanitize {

    static String validateUser(Scanner input) {
        println "Digite o nome"
        String name = input.nextLine()
        while (!(name =~ Regex.name)) {
            println "Digite o nome"
            name = input.nextLine()
        }
        return name
    }

    static String validadePassword(Scanner input) {
        println "Digite a senha"
        String password = input.nextLine()
        while (password == "") {
            println "Digite a senha"
            password = input.nextLine()
        }
        return password
    }

    static BigInteger validadeDoc(Scanner input) {
        println "Digite o CPF|CNPJ"
        BigInteger doc
        try {
            doc = new BigInteger(input.nextLine())
        }
        catch (NumberFormatException e) {
            println('Apenas numeros sao aceitos')
        }
        while (!(doc =~ Regex.doc)) {
            println "Digite o CPF|CNPJ"
            try {
                doc = new BigInteger(input.nextLine())
            }
            catch (NumberFormatException e) {
                println('Apenas numeros sao aceitos')
            }
        }
        return doc
    }

    static String validateEmail(Scanner input) {
        println "Digite o email"
        String email = input.nextLine()
        while (!(email =~ Regex.email)) {
            println "Digite o email"
            email = input.nextLine()
        }
        return email
    }

    static String validateCountry(Scanner input) {
        println "Digite o pais"
        String country = input.nextLine()
        while (!(country =~ Regex.country)) {
            println "Digite o pais"
            country = input.nextLine()
        }
        return country
    }

    static String validateState(Scanner input) {
        println "Digite o estado"
        String state = input.nextLine()
        while (!(state =~ Regex.state)) {
            println "Digite o estado"
            state = input.nextLine()
        }
        return state
    }

    static Integer validateZipcode(Scanner input) {
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
        return zipCode
    }

    static int validateAge(Scanner input) {
        println("Qual sua idade?")
        String age = input.nextLine()
        while (!(age ==~ Regex.age)) {
            println("Qual sua idade?")
            age = input.nextLine()
        }
        return Integer.parseInt(age)
    }

    static String validateDescription(Scanner input) {
        println("Qual sua formacao?")
        String description = input.nextLine()
        while (description == "") {
            println("Qual sua formacao?")
            description = input.nextLine()
        }
        return description
    }
}
