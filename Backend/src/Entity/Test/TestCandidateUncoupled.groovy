package Entity.Test


import Entity.Test.CandidateUncoupled
import Entity.Test.FakeCandidatesUncoupled


class TestCandidateUncoupled {

//Nao deveria ter duas verificacoes em um mesmo teste, porem tive que fazer um mock tanto para a insercao do candidato a lista
//e tambem um mock para resgatar o valor da lista

    boolean candidatoCriadoEstaNaListaComMock() {
        CandidateUncoupled candidato = new CandidateUncoupled(new FakeCandidatesUncoupled())
        List<Serializable> esperado = ["name", "email", "country", "state", 123, 123, 30, ["Java, Javascript"]]

        List<CandidateUncoupled> lista = candidato.create(candidato)

        assert esperado == lista
        assert candidato.getCandidates()==lista

    }


}
