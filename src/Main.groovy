import Entity.TestCandidate
import Entity.TestCandidateUncoupled
import Entity.TestEnterprise
import Utils.Navigate
import Utils.Populate

static void main(String[] args) {

//  Feito sem mock, com acoplamento
  TestCandidate test = new TestCandidate()
  test.candidatoCriadoEstaNaLista()
  test.candidatoCriadoAumentaLista()

  TestEnterprise spec = new TestEnterprise()
  spec.empresaCriadaAumentaLista()
  spec.empresaCriadaEstaNaLista()

//  Foi feito mock para funcoes get e create de Candidades
  TestCandidateUncoupled testWithMock = new TestCandidateUncoupled()
  testWithMock.candidatoCriadoEstaNaListaComMock()


  Populate.candidates();
  Populate.enterprises();
  Navigate.start()

}