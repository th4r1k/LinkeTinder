package Utils

import Data.Candidates
import Data.Enterprises
import Entity.Candidate
import Entity.Enterprise
import Entity.Opportunity

class Populate {

    static void candidates() {

        Candidate c01 = new Candidate("candidato01",  "candidato01@gmail.com",  "Brasil", "PR", 123456,  43242343, 33, ['Java', 'Groovy'])
        Candidate c02 = new Candidate("candidato02",  "candidato02@gmail.com",  "Brasil", "MT", 654665,  3213132, 33, ['Phyton'])
        Candidate c03 = new Candidate("candidato03",  "candidato03@gmail.com",  "Brasil", "RR", 212121,  12312321, 33, ['Javascript', 'Groovy'])
        Candidate c04 = new Candidate("candidato04",  "candidato04@gmail.com",  "Brasil", "RJ", 432133,  12312321, 33, ['Spring_Framework', 'Java'])
        Candidate c05 = new Candidate("candidato05",  "candidato05@gmail.com",  "Brasil", "SP", 644332,  12312321, 33, ['JavaScript', 'TypeScript'])

        Candidates.list += c01
        Candidates.list += c02
        Candidates.list += c03
        Candidates.list += c04
        Candidates.list += c05
    }


    static void enterprises() {
        Enterprise e01 = new Enterprise(name: "empresa01", email: "empresa01@gmail.com", doc: 123456, country: "Brasil", state: "RN", zipCode: 65732234)
        Enterprise e02 = new Enterprise(name: "empresa02", email: "empresa02@gmail.com", doc: 123456, country: "Brasil", state: "RN", zipCode: 65732234)
        Enterprise e03 = new Enterprise(name: "empresa03", email: "empres3@gmail.com", doc: 123456, country: "Brasil", state: "RN", zipCode: 65732234)
        Enterprise e04 = new Enterprise(name: "empresa04", email: "empresa04@gmail.com", doc: 123456, country: "Brasil", state: "RN", zipCode: 65732234)
        Enterprise e05 = new Enterprise(name: "empresa05", email: "empresa05@gmail.com", doc: 123456, country: "Brasil", state: "RN", zipCode: 65732234)

        Opportunity vaga01 = new Opportunity(1, "Programador Java Jr.", [Qualifications.Java, Qualifications.Spring_Framework])
        Opportunity vaga02 = new Opportunity(2, "Programador Java Sr.", [Qualifications.Java, Qualifications.Spring_Framework])
        Opportunity vaga03 = new Opportunity(3, "Programador Groovy Jr.", [Qualifications.Groovy, Qualifications.Angular])
        Opportunity vaga04 = new Opportunity(4, "Analista Phyton", [Qualifications.Python])
        Opportunity vaga05 = new Opportunity(5, "Programador Javascript frontend.", [Qualifications.Angular])

        e01.opportunity += vaga01
        e02.opportunity += vaga02
        e03.opportunity += vaga03
        e04.opportunity += vaga04
        e05.opportunity += vaga05

        Enterprises.list += e01
        Enterprises.list += e02
        Enterprises.list += e03
        Enterprises.list += e04
        Enterprises.list += e05
    }

}
