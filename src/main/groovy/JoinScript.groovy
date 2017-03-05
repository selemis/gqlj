FileJoiner fj = new FileJoiner()
String filepath1 = '../../test/resources/a.csv'
String filepath2 = '../../test/resources/b.csv'
fj.leftJoin(filepath1, filepath2)
fj.writeOutput('/home/selemis/Dev/temp/out.csv')