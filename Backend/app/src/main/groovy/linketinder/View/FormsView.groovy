package linketinder.View

import linketinder.Controller.CandidateQualificationController
import linketinder.Model.DAO.CandidateDAO
import linketinder.Model.DAO.CandidateQualificationDAO
import linketinder.Model.Entity.CandidateQualification
import linketinder.Model.Entity.User
import linketinder.Utils.Regex

class FormsView {

    static User registrationForm(String category) {
        Scanner input = new Scanner(System.in)

        String name = InputsView.validateUser(input)
        String password = InputsView.validadePassword(input)
        BigInteger doc = InputsView.validadeDoc(input)
        String email = InputsView.validateEmail(input)
        String country = InputsView.validateCountry(input)
        String state = InputsView.validateState(input)
        Integer zipCode = InputsView.validateZipcode(input)

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
        int age = InputsView.validateAge(input)
        dbCandidate.editAge(user.id, age)

        String description = InputsView.validateDescription(input)

        dbCandidate.editEducation(user.id, description)
    }

    static void addCandidateSkill(User user, int candidate_id){
        CandidateQualificationController candidateQualificationController = new CandidateQualificationController(new CandidateQualificationDAO())
        Scanner input = new Scanner(System.in)
        String java = knowsJava(input)
        if(java ==~ Regex.optionYes){
            CandidateQualification candidateQualification = new CandidateQualification(candidate_id: candidate_id, qualification_id: 1)
            candidateQualificationController.addQualifications(candidateQualification)
        }

        String groovy = knowsGroovy(input)
        if(groovy ==~ Regex.optionYes){
            CandidateQualification candidateQualification = new CandidateQualification(candidate_id: candidate_id, qualification_id: 2)
            candidateQualificationController.addQualifications(candidateQualification)
        }

        String javascript = knowsJavascript(input)
        if(javascript ==~ Regex.optionYes){
            CandidateQualification candidateQualification = new CandidateQualification(candidate_id: candidate_id, qualification_id: 3)
            candidateQualificationController.addQualifications(candidateQualification)
        }

        String phyton = knowsPhyton(input)
        if(phyton ==~ Regex.optionYes){
            CandidateQualification candidateQualification = new CandidateQualification(candidate_id: candidate_id, qualification_id: 4)
            candidateQualificationController.addQualifications(candidateQualification)
        }
    }
}
