package linketinder

import linketinder.Controller.CandidateController
import linketinder.DAO.CandidateDAO
import linketinder.DAO.UserDAO
import linketinder.DAO.UserDAOInterface
import linketinder.Entity.User
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
