package Entity

import Data.Candidates
import Data.Enterprises
import Utils.Navigate

class Candidate extends User {

    int age
    List<String> qualification

    Candidate(String name, String email, String country, String state, int doc, int zipCode, int age, List<String> qualification) {
        super(name, email, country, state, doc, zipCode)
        this.age = age
        this.qualification = qualification

    }

    Candidate(){}


    def create(Candidate candidate){
        Candidates.add(candidate)
    }


    void like(String name, def id){
        Scanner input = new Scanner(System.in);
        println "0 - Like"
        println "1 - Proximo"
        String cmd = input.nextLine();
        if (cmd == "0") {

            for (int i = 0; i < Candidates.list.size(); i++) {
                if (Candidates.list[i].name == name) {
                    Candidates.list[i].likes += id

                }
            }
        }
    }


    void match(String name){
        boolean match = false
        for (int i = 0; i < Candidates.list.size(); i++) {
            if (Candidates.list[i].name == name) {

                for (int j = 0; j < Enterprises.list.size(); j++) {
                    for (int k = 0; k < Enterprises.list[j].opportunity.size(); k++) {
                        if (Candidates.list[i].likes.flatten().contains(Enterprises.list[j].opportunity[k].id)) {
                            if (Enterprises.list[j].likes.contains(name)) {
                                println "***********************************************"
                                println "Match com a empresa ${Enterprises.list[j].name}"
                                println "***********************************************"
                                match = true
                            }
                        }
                    }
                }
            }
        }

        if(!match) {
            println("")
            println("Nenhum match ate o momento")}
        Navigate.candidateMenu(name)
    }

    void getOpportunities(String name) {
        Candidate candidate = new Candidate()
        Enterprises.list.forEach {
            if (it.opportunity) {
                if (it.opportunity.size() > 1) {
                    it.opportunity.forEach {

                        candidate.like(name, it.id)
                    }
                } else {
                    println it.opportunity
                    candidate.like(name, it.opportunity.id)
                }
            } else {
                println "Nao ha mais vagas"

            }
        }
        Navigate.candidateMenu(name)
    }

    List<Candidate> getCandidates (){
        Candidates.get()
    }

}
