package linketinder

import groovy.test.GroovyTestCase
import linketinder.Controller.EnterpriseController
import linketinder.DAO.EnterpriseDAO
import linketinder.DAO.UserDAO
import linketinder.DAO.Interfaces.UserDAOInterface
import linketinder.Entity.User
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test

import static org.mockito.Mockito.mock
import static org.mockito.Mockito.when

class EnterpriseControllerTest extends GroovyTestCase{


    static EnterpriseController enterpriseController
    static UserDAOInterface userDAO

    @BeforeAll
    static void instanciaEnterpriseController() {
        userDAO = mock(UserDAO)
        enterpriseController = new EnterpriseController(new EnterpriseDAO(), userDAO)
    }
    @Test
    void createCandidateTest() {
        //given
        User newEnterprise = new User(id: 1, name: "empresaTeste", email: "empresateste@gmail.com", password: "123321", doc: 12345678911, zipCode: 13246578, country: "brasil", state: "pr", category: "candidate")
        when(userDAO.create(newEnterprise)).thenReturn(newEnterprise)

        //when
        User result = enterpriseController.create(newEnterprise)

        //then
        assertEquals(newEnterprise, result)

    }
}




