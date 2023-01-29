package Entity.Test

import Data.Candidates
import Entity.Candidate


class TestCandidate {


    boolean candidatoCriadoAumentaLista() {
        Candidates.list = []
        Candidate candidato = new Candidate( "name",  "email",  "country", "state",  123,  123,  30 , ["Java, Javascript"])
        int tamanhoAtualDaLista = Candidates.list.size()

        candidato.create(candidato)

        assert Candidates.list.size() == tamanhoAtualDaLista + 1

    }

    boolean candidatoCriadoEstaNaLista() {
        Candidates.list = []
        Candidate candidato = new Candidate("name",  "email",  "country", "state",  123,  123,  30 , ["Java, Javascript"])

        candidato.create(candidato)

        assert Candidates.list.contains(candidato)

    }



}
