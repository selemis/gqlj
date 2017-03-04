class Joiner {

    List<Line> firstJoinable
    List<Line> secondJoinable
    def result

    Joiner() {
        firstJoinable = []
        secondJoinable = []
        result = []
    }

    def join() {
        result = []
        firstJoinable.each { k1 ->
            def matchingEntry = secondJoinable.find { k2 ->
                k1.key == k2.key
            }
            if (matchingEntry)
                result << [k1.record, matchingEntry.record]
        }
    }

    def leftJoin() {
        result = []
        firstJoinable.each { k1 ->
            def matchingEntry = secondJoinable.find { k2 ->
                k1.key == k2.key
            }

            if (matchingEntry) {
                result << [k1.record, matchingEntry.record]
            } else
                result << [k1.record]
        }
    }

}
