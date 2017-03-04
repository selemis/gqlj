import groovy.transform.Canonical

@Canonical
class Line {

    String key
    def record

    Line(String key, def record) {
        this.key = key
        this.record = record
    }

}
