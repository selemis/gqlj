import spock.lang.Specification

class FileJoinerSpec extends Specification {

    def 'joining 2 csv streams'() {
        given: 'a file joiner with two streams'
        FileJoiner fj = new FileJoiner()
        fj.stream1 = getClass().getResourceAsStream('/a-small.csv')
        fj.stream2 = getClass().getResourceAsStream('/b-small.csv')

        when: 'joining'
        fj.join()

        then: 'we get the joined result'
        fj.getResult() == innerJoinedResult()
    }

    def 'left joining 2 csv streams'() {
        given: 'a file joiner with two streams'
        FileJoiner fj = new FileJoiner()
        fj.stream1 = getClass().getResourceAsStream('/a-small.csv')
        fj.stream2 = getClass().getResourceAsStream('/b-small.csv')

        when: 'left joining'
        fj.leftJoin()

        then: 'we get the left joined result'
        fj.getResult() == leftJoinedResult()
    }

    def 'joining 2 csv files'() {
        given: 'a file joiner with two files'
        FileJoiner fj = new FileJoiner()
        File a = new File('./src/test/resources/a-small.csv')
        File b = new File('./src/test/resources/b-small.csv')

        when: 'joining'
        fj.join(a, b)

        then: 'we get the joined result'
        fj.getResult() == innerJoinedResult()
    }

    def 'left left joining 2 csv files'() {
        given: 'a file joiner with two files'
        FileJoiner fj = new FileJoiner()
        File a = new File('./src/test/resources/a-small.csv')
        File b = new File('./src/test/resources/b-small.csv')

        when: 'left joining'
        fj.leftJoin(a, b)

        then: 'we get the left joined result'
        fj.getResult() == leftJoinedResult()
    }

    def 'joining 2 csv files using file path'() {
        given: 'a file joiner with two file paths'
        FileJoiner fj = new FileJoiner()

        when: 'joining'
        fj.join('./src/test/resources/a-small.csv', './src/test/resources/b-small.csv')

        then: 'we get the joined result'
        fj.getResult() == innerJoinedResult()
    }

    def 'left joining 2 csv files'() {
        given: 'a file joiner with two file paths'
        FileJoiner fj = new FileJoiner()

        when: 'left joining'
        fj.leftJoin('./src/test/resources/a-small.csv', './src/test/resources/b-small.csv')

        then: 'we get the left joined result'
        fj.getResult() == leftJoinedResult()
    }

    def 'join output'(){
        given: 'a file joiner with two streams'
        FileJoiner fj = new FileJoiner()
        fj.stream1 = getClass().getResourceAsStream('/a-small.csv')
        fj.stream2 = getClass().getResourceAsStream('/b-small.csv')

        when: 'joining'
        fj.join()

        then: 'output returns the joined records for the ouput file'
        fj.output() == [
                'ΑΤΤΙΚΗ;ATTICA',
                'ΑΘΗΝΑ;ATTICA',
                'ΑΘΗΝΑ;ATTICA',
                'ΑΘΗΝΑ;ATTICA',
                'ΑΘΗΝΑ;ATTICA',
                'ΑΛΟΝΗΣΟΣ;MAGNISIAS'
        ]
    }

    def 'left join output'(){
        given: 'a file joiner with two streams'
        FileJoiner fj = new FileJoiner()
        fj.stream1 = getClass().getResourceAsStream('/a-small.csv')
        fj.stream2 = getClass().getResourceAsStream('/b-small.csv')

        when: 'joining'
        fj.leftJoin()

        then: 'output returns the joined records for the ouput file'
        fj.output() == [
                'ΑΤΤΙΚΗ;ATTICA',
                'ΚΩΣ',
                'ΑΘΗΝΑ;ATTICA',
                'ΗΛΙΟΥΠΟΛΗ',
                'ΡΟΔΟΣ',
                'ΓΛΥΦΑΔΑ',
                'ΛΟΥΤΡΑΚΙ',
                'ΑΘΗΝΑ;ATTICA',
                'ΚΩΣ',
                'ΧΟΛΑΡΓΟΣ',
                'ΡΟΔΟΣ',
                'ENGLAND',
                'ΜΥΚΟΝΟΣ',
                'ΚΑΡΠΑΘΟΣ',
                'ΑΝΤΙΡΡΙΟ',
                'ΑΘΗΝΑ;ATTICA',
                'BRUXELLES',
                'ΑΘΗΝΑ;ATTICA',
                'ΝΑΥΠΛΙΟΝ',
                'ΒΑΡΥΜΠΟΜΠΗ',
                'ΑΛΟΝΗΣΟΣ;MAGNISIAS'
        ]
    }

    ArrayList<ArrayList<ArrayList<String>>> innerJoinedResult() {
        [
                [['ΑΤΤΙΚΗ'], ['ΑΤΤΙΚΗ', 'ATTICA']],
                [['ΑΘΗΝΑ'], ['ΑΘΗΝΑ', 'ATTICA']],
                [['ΑΘΗΝΑ'], ['ΑΘΗΝΑ', 'ATTICA']],
                [['ΑΘΗΝΑ'], ['ΑΘΗΝΑ', 'ATTICA']],
                [['ΑΘΗΝΑ'], ['ΑΘΗΝΑ', 'ATTICA']],
                [['ΑΛΟΝΗΣΟΣ'], ['ΑΛΟΝΗΣΟΣ', 'MAGNISIAS']]
        ]
    }

    ArrayList<ArrayList<ArrayList<String>>> leftJoinedResult() {
        [
                [['ΑΤΤΙΚΗ'], ['ΑΤΤΙΚΗ', 'ATTICA']],
                [['ΚΩΣ']],
                [['ΑΘΗΝΑ'], ['ΑΘΗΝΑ', 'ATTICA']],
                [['ΗΛΙΟΥΠΟΛΗ']],
                [['ΡΟΔΟΣ']],
                [['ΓΛΥΦΑΔΑ']],
                [['ΛΟΥΤΡΑΚΙ']],
                [['ΑΘΗΝΑ'], ['ΑΘΗΝΑ', 'ATTICA']],
                [['ΚΩΣ']],
                [['ΧΟΛΑΡΓΟΣ']],
                [['ΡΟΔΟΣ']],
                [['ENGLAND']],
                [['ΜΥΚΟΝΟΣ']],
                [['ΚΑΡΠΑΘΟΣ']],
                [['ΑΝΤΙΡΡΙΟ']],
                [['ΑΘΗΝΑ'], ['ΑΘΗΝΑ', 'ATTICA']],
                [['BRUXELLES']],
                [['ΑΘΗΝΑ'], ['ΑΘΗΝΑ', 'ATTICA']],
                [['ΝΑΥΠΛΙΟΝ']],
                [['ΒΑΡΥΜΠΟΜΠΗ']],
                [['ΑΛΟΝΗΣΟΣ'], ['ΑΛΟΝΗΣΟΣ', 'MAGNISIAS']]
        ]
    }

}
