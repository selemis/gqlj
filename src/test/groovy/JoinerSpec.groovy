import spock.lang.Specification
import spock.lang.Unroll

class JoinerSpec extends Specification {

    Joiner j = new Joiner()

    def setup() {
        j = new Joiner()
    }

    @Unroll
    def 'joining #j1 and #j2 returns #r'() {
        given: 'joinables #j1 and #j2'
        j.firstJoinable = j1
        j.secondJoinable = j2

        when: 'joining'
        j.join()

        then: 'the result is #r'
        j.result == r

        where: "j1 #j1 and j2 #j3 and result #r"
        j1                               | j2                      || r
        [:]                              | [:]                     || []
        [a: 1]                           | [:]                     || []
        [a: 1]                           | [b: 2]                  || []
        [a: 1]                           | [a: 2]                  || [[1, 2]]
        [a: 1, b: 2, c: 3]               | [a: 2, c: 'a', b: '12'] || [[1, 2], [2, '12'], [3, 'a']]
        [a: 1, b: 2, d: 4, c: 3, e: 125] | [a: 2, c: 'a', e: '32'] || [[1, 2], [3, 'a'], [125, '32']]
    }

    @Unroll
    def 'left joining #j1 and #j2 returns #r'() {
        given: 'joinables #j1 and #j2'
        j.firstJoinable = j1
        j.secondJoinable = j2

        when: 'joining'
        j.leftJoin()

        then: 'the result is #r'
        j.result == r

        where: "j1 #j1 and j2 #j3 and result #r"
        j1                               | j2                      || r
        [:]                              | [:]                     || []
        [a: 1]                           | [:]                     || [[1]]
        [:]                              | [a: 1]                  || []
        [a: 1]                           | [b: 2]                  || [[1]]
        [b: 2]                           | [a: 1]                  || [[2]]
        [a: 1]                           | [a: 2]                  || [[1, 2]]
        [a: 1, b: 2, c: 3]               | [a: 2, c: 'a', b: '12'] || [[1, 2], [2, '12'], [3, 'a']]
        [a: 1, b: 2, d: 4, c: 3, e: 125] | [a: 2, c: 'a', e: '32'] || [[1, 2], [2], [4], [3, 'a'], [125, '32']]
    }

}
