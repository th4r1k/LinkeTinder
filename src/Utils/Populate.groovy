package Utils

import Data.Candidates
import Data.Enterprises
import Entity.Candidate
import Entity.Enterprise
import Entity.Opportunity

class Populate {

    static void candidates() {

        Candidate c01 = new Candidate(name: "candidato01", email: "candidato01@gmail.com", doc: 123456, country: "Brasil", state: "PR", zipCode: 12312321, age: 33, qualification: ['Java', 'Groovy'])
        Candidate c02 = new Candidate(name: "candidato02", email: "candidato02@gmail.com", doc: 123456, country: "Brasil", state: "SP", zipCode: 32132132, age: 26, qualification: ['Javascript', 'Angular'])
        Candidate c03 = new Candidate(name: "candidato03", email: "candidato03@gmail.com", doc: 123456, country: "Brasil", state: "MT", zipCode: 54354313, age: 28, qualification: ['Java', 'Spring_Framework'])
        Candidate c04 = new Candidate(name: "candidato04", email: "candidato04@gmail.com", doc: 123456, country: "Brasil", state: "RN", zipCode: 65732234, age: 29, qualification: ['Python'])
        Candidate c05 = new Candidate(name: "candidato05", email: "candidato05@gmail.com", doc: 123456, country: "Brasil", state: "RN", zipCode: 65732234, age: 29, qualification: ['Angular'])

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
