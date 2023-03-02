package linketinder

import groovy.test.GroovyTestCase
import linketinder.Utils.Regex
import org.junit.jupiter.api.Test

class RegexTest extends GroovyTestCase{

    @Test
    void isValidNameTest(){
        //given
        String name = "joao"
        String pattern = Regex.name
        boolean isValid

        //when
        isValid = name.matches(pattern)

        //then
        assertEquals(true, isValid)
    }

    @Test
    void isNotValidNameTest(){
        //given
        String name = "joao123"
        String pattern = Regex.name
        boolean isValid

        //when
        isValid = name.matches(pattern)

        //then
        assertEquals(false, isValid)
    }

    @Test
    void isValidEmailTest(){
        //given
        String email = "joao@gmail.com"
        String pattern = Regex.email
        boolean isValid

        //when
        isValid = email.matches(pattern)

        //then
        assertEquals(true, isValid)
    }

    @Test
    void isNotValidEmailTest(){
        //given
        String email = "joao@gmail.co"
        String pattern = Regex.name
        boolean isValid

        //when
        isValid = email.matches(pattern)

        //then
        assertEquals(false, isValid)
    }

    @Test
    void isValidDocTest(){
        //given
        String doc = "18099539940"
        String pattern = Regex.doc
        boolean isValid

        //when
        isValid = doc.matches(pattern)

        //then
        assertEquals(true, isValid)
    }

    @Test
    void isNotValidDocTest(){
        //given
        String doc = "321321"
        String pattern = Regex.doc
        boolean isValid

        //when
        isValid = doc.matches(pattern)

        //then
        assertEquals(false, isValid)
    }

    @Test
    void isValidZipCodeTest(){
        //given
        String zipCode = "86730000"
        String pattern = Regex.zipCode
        boolean isValid

        //when
        isValid = zipCode.matches(pattern)

        //then
        assertEquals(true, isValid)
    }

    @Test
    void isNotValidZipCodeTest(){
        //given
        String zipCode = "8670"
        String pattern = Regex.zipCode
        boolean isValid

        //when
        isValid = zipCode.matches(pattern)

        //then
        assertEquals(false, isValid)
    }


}
