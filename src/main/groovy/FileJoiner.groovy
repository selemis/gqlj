class FileJoiner {

    InputStream stream1
    InputStream stream2
    def result

    void writeOutput(String filepath) {
        def file = new File(filepath)
        output().each {
            file << it
            file << "\n"
        }
    }

    def output() {
        result.collect {
            it.last().join(';')
        }
    }

    def join(String filePath1, String filePath2) {
        join(new File(filePath1), new File(filePath2))
    }

    def leftJoin(String filePath1, String filePath2) {
        leftJoin(new File(filePath1), new File(filePath2))
    }

    def join(File file1, File file2) {
        joinFiles(file1, file2) { join() }
    }

    def leftJoin(File file1, File file2) {
        joinFiles(file1, file2) { leftJoin() }
    }

    def join() {
        joinStreams { it.join() }
    }

    def leftJoin() {
        joinStreams { it.leftJoin() }
    }

    def joinFiles(File file1, File file2, def clj) {
        result = []
        file1.withInputStream { stream1 ->
            this.stream1 = stream1
            file2.withInputStream { stream2 ->
                this.stream2 = stream2
                clj()
            }
        }
    }

    def joinStreams(def clj) {
        result = []
        Joiner j = new Joiner()
        List<Line> j1 = []
        stream1.splitEachLine(';') { tokens ->
            j1 << new Line(tokens[0], tokens)
        }

        List<Line> j2 = []
        stream2.splitEachLine(';') { tokens ->
            j2 << new Line(tokens[0], tokens)
        }

        j.firstJoinable = j1
        j.secondJoinable = j2
        clj(j)
        result = j.result
    }

}
