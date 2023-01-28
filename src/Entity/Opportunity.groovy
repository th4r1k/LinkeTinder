package Entity

import groovy.transform.Canonical

@Canonical
class Opportunity {

    int id
    String description
    List<String> qualification

}
