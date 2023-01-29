package Data

import Entity.Candidate

class Candidates {

    static List<Candidate> list = []

     static add(Candidate candidate){
        list += candidate
    }

    static get(){
        list
    }

}