package linketinder.View

import linketinder.DAO.CandidateDAO
import linketinder.DAO.QualificationDAO
import linketinder.Entity.User
import linketinder.Utils.Regex
import linketinder.Utils.Sanitize

class FormsView {

    static User registrationForm(String category) {
        Scanner input = new Scanner(System.in)

        String name = Sanitize.validateUser(input)
        String password = Sanitize.validadePassword(input)
        BigInteger doc = Sanitize.validadeDoc(input)
        String email = Sanitize.validateEmail(input)
        String country = Sanitize.validateCountry(input)
        String state = Sanitize.validateState(input)
        Integer zipCode = Sanitize.validateZipcode(input)

        if (category == "candidate") {
            User newCandidate = new User(name: name, email: email, country: country, zipCode: zipCode, state: state, doc: doc, password: password, category: "candidate")
            return newCandidate
        } else {
            User newEnterprise = new User(name: name, email: email, country: country, zipCode: zipCode, state: state, doc: doc, password: password, category: "enterprise")
            return newEnterprise
        }
    }

    static newCandidateQualificationForm(User user, int id){
        println("Voce precisar terminar seu cadastro para comecar a navegar")
        addCandidateInfo(user)
        addCandidateSkill(user, id)
    }

    static String knowsJava(Scanner input){
        println("Possui conhecimento em JAVA? (0-NAO/1-SIM)")
        String java = input.nextLine()
        while(!(java ==~ Regex.options)){
            println("Possui conhecimento em JAVA? (0-NAO/1-SIM)")
            java = input.nextLine()
        }
        return java
    }

    static String knowsGroovy(Scanner input) {
        println("Possui conhecimento em Groovy? (0-NAO/1-SIM)")
        String groovy = input.nextLine()
        while(!(groovy ==~ Regex.options)){
            println("Possui conhecimento em Groovy? (0-NAO/1-SIM)")
            groovy = input.nextLine()
        }
        return groovy
    }

    static String knowsJavascript(Scanner input) {
        println("Possui conhecimento em Javascript? (0-NAO/1-SIM)")
        String javascript = input.nextLine()
        while(!(javascript ==~ Regex.options)){
            println("Possui conhecimento em Javascript? (0-NAO/1-SIM)")
            javascript = input.nextLine()
        }
        return javascript
    }

    static String knowsPhyton(Scanner input) {
        println("Possui conhecimento em Phyton? (0-NAO/1-SIM)")
        String phyton = input.nextLine()
        while(!(phyton ==~ Regex.options)){
            println("Possui conhecimento em Phyton? (0-NAO/1-SIM)")
            phyton = input.nextLine()
        }
        return phyton
    }

    static void addCandidateInfo(User user){
        CandidateDAO dbCandidate = new CandidateDAO()
        Scanner input = new Scanner(System.in)
        int age = Sanitize.validateAge(input)
        dbCandidate.editAge(user.id, age)

        String description = Sanitize.validateDescription(input)
        dbCandidate.editEducation(user.id, description)
    }

    static void addCandidateSkill(User user, int id){
        QualificationDAO dbQualification = new QualificationDAO()
        Scanner input = new Scanner(System.in)
        String java = knowsJava(input)
        if(java ==~ Regex.optionYes){
            dbQualification.addCandidateQualifications(id, 1)
        }

        String groovy = knowsGroovy(input)
        if(groovy ==~ Regex.optionYes){
            dbQualification.addCandidateQualifications(id, 2)
        }

        String javascript = knowsJavascript(input)
        if(javascript ==~ Regex.optionYes){
            dbQualification.addCandidateQualifications(id, 3)
        }

        String phyton = knowsPhyton(input)
        if(phyton ==~ Regex.optionYes){
            dbQualification.addCandidateQualifications(id, 4)
        }
    }
}
