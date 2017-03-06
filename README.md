# gqlj

## Summary

Simple utility for joining 2 csv files in an sql-like way. There 3 main
functions that are supported. An inner join (join), a left join and a
diff.

## Usage

The class that joins the files is the FileJoiner. You can use the API 
through a groovy script such as the JoinScript.groovy example.

For the moment the join operation takes into account the first column
of the first file on the first column of the second file.

### Operations

There a 3 main operations supported.

```join (inner join)```

It will join the lines from both csv files that have common first column.

```left join```

It will return a result containing as many lines as the first file has. 
The result will have the lines of the first file joined by the lines of
the second only in case that second file's first column matches the 
first file's first column.

```diff```

It will return the lines that their first column do not match. The lines
of the first file are returned first followed by the lines of the second
file.

## Future development

* Join on the first column by default but be able to specify the columns that take part in join.
* Be able to return specific columns in the result and not only the whole line.