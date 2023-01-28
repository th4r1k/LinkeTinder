package Entity

import Utils.Qualifications
import groovy.transform.Canonical

@Canonical
class Opportunity {

    int id
    String description
    List<Qualifications> qualification

}
