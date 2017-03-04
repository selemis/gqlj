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
        j1                                                                | j2                                                  || r
        []                                                                | []                                                  || []
        j([['a', 1]])                                                     | []                                                  || []
        j([['a', 1]])                                                     | j([['b', 2]])                                       || []
        j([['a', 1]])                                                     | j([['a', 2]])                                       || [[1, 2]]
        j([['a', 1], ['b', 2], ['c', 3]])                                 | j([['a', 2], ['c', 'a'], ['b', '12']])              || [[1, 2], [2, '12'], [3, 'a']]
        j([['a', 1], ['b', 2], ['d', 4], ['c', 3], ['e', 125]])           | j([['a', 2], ['c', 'a'], ['e', '32']])              || [[1, 2], [3, 'a'], [125, '32']]
        j([['a', 1], ['b', 2], ['c', 3], ['c', 4], ['c', 3], ['a', 123]]) | j([['a', 'a'], ['b', 'b'], ['c', 'c'], ['d', 'd']]) || [[1, 'a'], [2, 'b'], [3, 'c'], [4, 'c'], [3, 'c'], [123, 'a']]
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
        j1                                                              | j2                                     || r
        []                                                              | []                                     || []
        j([['a', 1]])                                                   | []                                     || [[1]]
        []                                                              | j([['a', 1]])                          || []
        j([['a', 1]])                                                   | j([['b', 2]])                          || [[1]]
        j([['b', 2]])                                                   | j([['a', 1]])                          || [[2]]
        j([['a', 1]])                                                   | j([['a', 2]])                          || [[1, 2]]
        j([['a', 1], ['b', 2], ['c', 3]])                               | j([['a', 2], ['c', 'a'], ['b', '12']]) || [[1, 2], [2, '12'], [3, 'a']]
        j([['a', 1], ['b', 2], ['d', 4], ['c', 3], ['e', 125]])         | j([['a', 2], ['c', 'a'], ['e', '32']]) || [[1, 2], [2], [4], [3, 'a'], [125, '32']]
        j([['a', 1], ['b', 2], ['a', 3], ['c', 4], ['a', 5], ['d', 8]]) | j([['a', 'a'], ['d', 'd']])            || [[1, 'a'], [2], [3, 'a'], [4], [5, 'a'], [8, 'd']]
    }

    def j(list) {
        list.collect { new Line(it[0], it[1]) }
    }

}
