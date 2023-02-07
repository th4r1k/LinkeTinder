package Entity.Test

class FakeCandidatesUncoupled implements ICandidatesUncoupled {

    @Override
    List<Serializable> add(CandidateUncoupled candidate) {
        ["name", "email", "country", "state", 123, 123, 30, ["Java, Javascript"]]
    }

    @Override
    List<Serializable> get() {
        ["name", "email", "country", "state", 123, 123, 30, ["Java, Javascript"]]
    }
}
