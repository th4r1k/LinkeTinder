package linketinder

import linketinder.Controller.CandidateController
import linketinder.Model.DAO.CandidateDAO
import linketinder.Model.DAO.UserDAO
import linketinder.Model.DAO.Interfaces.UserDAOInterface
import linketinder.Model.Entity.User
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import static org.mockito.Mockito.*
import groovy.test.GroovyTestCase

class CandidateControllerTest extends GroovyTestCase {

    static CandidateController candidateController
    static UserDAOInterface userDAO

    @BeforeAll
    static void instanciaCandidateController() {
        userDAO = mock(UserDAO)
        candidateController = new CandidateController(new CandidateDAO(), userDAO)
    }
    @Test
    void createCandidateTest() {
        //given
        User newCandidate = new User(id: 1, name: "candidatoTeste", email: "candidatoteste@gmail.com", password: "123321", doc: 12345678911, zipCode: 13246578, country: "brasil", state: "pr", category: "candidate")
        when(userDAO.create(newCandidate)).thenReturn(newCandidate)

        //when
        User result = candidateController.create(newCandidate)

        //then
        assertEquals(newCandidate, result)

    }
}
