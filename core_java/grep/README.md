# Introduction
JavaGrep is an application that searches all files in a directory recursively for usages of a user
provided regular expression. Lines that contain those expressions are written into a new file. This
application was implemented using Java 8's Stream APIs and Lambda Expressions. JUnit4 was used to 
test the methods of the application.  

# Usage
The program takes three arguments:
1. regex : the regular expression whose match needs to be found
2. rootPath : the directory in which the search is to be made
3. outFile : name of the output file

Example of program arguments: `.*IllegalArgumentException.* ./grep/src /tmp/grep.out`

# Pseudocode
```
matchedLines = []
for file in listFilesRecursively(rootDir)
  for line in readLines(file)
      if containsPattern(line)
        matchedLines.add(line)
writeToFile(matchedLines)
```

# Performance Issue
All lines of a file are stored in a List before they are used for matching patterns with the regular 
expression. As the file sizes become larger, the chances of getting OutOfMemory errors will be 
higher as the List will continue to grow larger in size. One solution could be to read,
pattern-match and write a line to the buffer at the same time. The buffer will be flushed after a 
particular threshold is reached. This way, the memory will not be used up. 

# Improvement
1. Change the JavaGrep interface to allow for simultaneous reading and processing of lines
2. Use parallel streams for faster application
3. Make the application more customizable e.g. options for case insensitive/sensitive search, 
current directory only vs current and all subdirectories etc.