/**
 * Performs the three join operations.
 */
class Joiner {

    /**
     * The first list of Line objects that take part in the join operation.
     */
    List<Line> firstJoinable

    /**
     * The second list of Line objects that take part in the join operation.
     */
    List<Line> secondJoinable

    /**
     * The result after the join operation.
     */
    def result

    /**
     * Create a new Joiner instance.
     */
    Joiner() {
        firstJoinable = []
        secondJoinable = []
        result = []
    }

    /**
     * Perform an inner join operation.
     */
    void join() {
        result = []
        firstJoinable.each { k1 ->
            def matchingEntry = secondJoinable.find { k2 ->
                k1.key == k2.key
            }
            if (matchingEntry)
                result << [k1.record, matchingEntry.record]
        }
    }

    /**
     * Perform a left join operation.
     */
    void leftJoin() {
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

    /**
     * Perform a diff operation.
     */
    void diff() {
        result = []
        def secondJoinableKeys = secondJoinable*.key
        firstJoinable.each { k1 ->
            if (!secondJoinableKeys.contains(k1.key))
                result << [k1.record]
        }
    }

}
