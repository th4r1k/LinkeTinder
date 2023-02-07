import Data.Candidates
import Data.Enterprises
import Entity.Test.TestCandidate
import Entity.Test.TestCandidateUncoupled
import Entity.Test.TestEnterprise
import Utils.Navigate
import Utils.Populate

static void main(String[] args) {

////  Feito sem mock, com acoplamento
  TestCandidate test = new TestCandidate()
  test.candidatoCriadoEstaNaLista()
  test.candidatoCriadoAumentaLista()

  TestEnterprise spec = new TestEnterprise()
  spec.empresaCriadaAumentaLista()
  spec.empresaCriadaEstaNaLista()

//  Foi feito mock para funcoes get e create de Candidades
  TestCandidateUncoupled testWithMock = new TestCandidateUncoupled()
  testWithMock.candidatoCriadoEstaNaListaComMock()

//  limpa teste
  Candidates.list=[]
  Enterprises.list=[]

  Populate.candidates();
  Populate.enterprises();
  Navigate.start()

}