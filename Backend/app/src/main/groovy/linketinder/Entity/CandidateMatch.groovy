package linketinder.Entity

import groovy.transform.Canonical

@Canonical
class CandidateMatch {
    int id
    int candidate_id
    int job_id
    String name
    String email
}
