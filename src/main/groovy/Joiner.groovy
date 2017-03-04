class Joiner {

    def firstJoinable
    def secondJoinable
    def result

    Joiner() {
        firstJoinable = [:]
        secondJoinable = [:]
        result = []
    }

    def join() {
        result = []
        firstJoinable.each { k1, v1 ->
            def matchingEntries = secondJoinable.findAll { k2, v2 ->
                k1 == k2
            }
            matchingEntries.each { k, v ->
                result << [firstJoinable[k], secondJoinable[k]]
            }
        }
    }

    def leftJoin() {
        result = []
        firstJoinable.each { k1, v1 ->
            def matchingEntries = secondJoinable.findAll { k2, v2 ->
                k1 == k2
            }

            if (matchingEntries.size()) {
                matchingEntries.each { k, v ->
                    result << [firstJoinable[k], secondJoinable[k]]
                }
            } else
                result << [firstJoinable[k1]]

        }
    }

}
