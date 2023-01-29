package Entity.Test

import Data.Enterprises
import Entity.Enterprise

class TestEnterprise {


    boolean empresaCriadaAumentaLista() {
        Enterprises.list = []

        Enterprise empresa = new Enterprise(name: "name", email: "email", country: "country", zipCode: 2, state: "state", doc: 1)
        int tamanhoAtualDaLista = Enterprises.list.size()

        empresa.create(empresa)

        assert Enterprises.list.size() == tamanhoAtualDaLista + 1

    }

    boolean empresaCriadaEstaNaLista() {
        Enterprises.list = []

        Enterprise empresa = new Enterprise(name: "name", email: "email", country: "country", zipCode: 2, state: "state", doc: 1)

        empresa.create(empresa)

        assert Enterprises.list.contains(empresa)

    }
}
