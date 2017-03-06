/**
 * This class responsibility is to join 2 csv files.
 */
class FileJoiner {

    InputStream stream1
    InputStream stream2
    def result

    /**
     * Write the result to a file.
     *
     * @param filePath path to the output file
     */
    void writeOutput(String filePath) {
        def file = new File(filePath)
        output().each {
            file << it
            file << "\n"
        }
    }

    /**
     * The output of the operation that will be written to a file.
     *
     * @return join operation formatted output
     */
    def output() {
        result.collect {
            it.last().join(';')
        }
    }

    /**
     * Join 2 files based on file paths.
     *
     * @param filePath1 first file file path
     * @param filePath2 second file file path
     */
    void join(String filePath1, String filePath2) {
        join(new File(filePath1), new File(filePath2))
    }

    /**
     * Left join 2 files based on file paths.
     *
     * @param filePath1 first file file path
     * @param filePath2 second file file path
     */
    void leftJoin(String filePath1, String filePath2) {
        leftJoin(new File(filePath1), new File(filePath2))
    }

    /**
     * Join 2 file objects.
     *
     * @param file1 first file
     * @param file2 second file
     */
    void join(File file1, File file2) {
        joinFiles(file1, file2) { join() }
    }

    /**
     * Left join 2 file objects.
     *
     * @param file1 first file
     * @param file2 second file
     */
    void leftJoin(File file1, File file2) {
        joinFiles(file1, file2) { leftJoin() }
    }

    void join() {
        joinStreams { it.join() }
    }

    void leftJoin() {
        joinStreams { it.leftJoin() }
    }

    void joinFiles(File file1, File file2, def clj) {
        result = []
        file1.withInputStream { stream1 ->
            this.stream1 = stream1
            file2.withInputStream { stream2 ->
                this.stream2 = stream2
                clj()
            }
        }
    }

    void joinStreams(def clj) {
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
