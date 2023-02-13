package Entity

import groovy.transform.Canonical

@Canonical
abstract class User {

<<<<<<< Updated upstream
    String name, email, country, state
    int doc, zipCode
//    List<User> matches = []
    List<User> likes = []
=======
    String name, email, country, state, password, category
    BigInteger doc
    int zipCode
>>>>>>> Stashed changes


}
