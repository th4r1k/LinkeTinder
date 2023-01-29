package Entity


class TestCandidateUncoupled {

//Nao deveria ter duas verificacoes em um mesmo teste, porem tive que fazer um mock tanto para a insercao do candidato a lista
//e tambem um mock para resgatar o valor da lista

    boolean candidatoCriadoEstaNaListaComMock() {
        CandidateUncoupled candidato = new CandidateUncoupled(new FakeCandidatesUncoupled())

        List<Serializable> esperado = ["name", "email", "country", "state", 123, 123, 30, ["Java, Javascript"]]
        candidato.create(candidato)


        assert candidato.create(candidato) == esperado
        assert candidato.create(candidato) == candidato.getCandidates()

    }


}
