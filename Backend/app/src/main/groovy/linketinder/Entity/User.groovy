package linketinder.Entity

import groovy.transform.Canonical

@Canonical
  class User {
    int id
    String name, email, country, state, password, category
    BigInteger doc
    int zipCode

}
