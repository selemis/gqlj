import groovy.transform.Canonical

/**
 * A data structure used for joining. The key is the part of the record that takes part in the join operation.
 */
@Canonical
class Line {

    /**
     * The part of the record that takes part on the join operation.
     */
    String key

    /**
     * The whole record
     */
    def record

    /**
     * Create a new Line.
     *
     * @param key part of the record that takes part on the join operation
     * @param record whole record
     */
    Line(String key, def record) {
        this.key = key
        this.record = record
    }

}
