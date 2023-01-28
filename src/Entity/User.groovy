package Entity

import groovy.transform.Canonical

@Canonical
abstract class User {

    String name, email, country, state
    int doc, zipCode
    List<User> matches = []
    List<User> likes = []


}
