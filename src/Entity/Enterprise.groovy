package Entity

import Data.Candidates
import Data.Enterprises
import Utils.Navigate

class Enterprise extends User{

    List<Opportunity> opportunity = []


    void create(String name, String email, String country, int zipCode, String state, int doc){
        Enterprise newEnterprise = new Enterprise(name: name, email: email, country: country, zipCode: zipCode, state: state, doc: doc)
        Enterprises.list += newEnterprise
    }

    void like(String name, def candidateName) {
        Scanner input = new Scanner(System.in)

        println "0 - Like"
        println "1 - Proximo"
        String cmd = input.nextLine()
        if (cmd == "0") {

            for (int i = 0; i < Enterprises.list.size(); i++) {
                if (Enterprises.list[i].name == name) {
                    Enterprises.list[i].likes += [candidateName]

                }
            }
        }
    }


    void match(String name){
        boolean match = false
        for (int i = 0; i < Enterprises.list.size(); i++) {
            if (Enterprises.list[i].name == name) {

                for (int j = 0; j < Candidates.list.size(); j++) {
                    for (int k = 0; k < Enterprises.list[i].opportunity.size(); k++) {
                        if (Candidates.list[j].likes.contains(Enterprises.list[i].opportunity[k].id)) {
                            if(Enterprises.list[i].likes.contains(Candidates.list[j].name)) {

                            println "***********************************************"
                            println "Match com o candidato ${Candidates.list[j].name} na vaga ${Enterprises.list[i].opportunity[k].description}"
                            println "***********************************************"
                            match = true
                              }
                         }
                    }
                }
//                break
            }
        }
        if(!match) {
            println("")
            println("Nenhum match ate o momento")
        }
        Navigate.enterpriseMenu(name)
    }


    void getOpportunities(String name) {
        Enterprise enterprise = new Enterprise()
        Candidates.list.forEach {
            if (it.qualification) {

                println ""
                println "Habilidades do candidato"
                println it.qualification
                enterprise.like(name, it.name)


            } else {
                println "Nao ha mais candidatos"

            }
        }
        Navigate.enterpriseMenu(name)
    }
}
