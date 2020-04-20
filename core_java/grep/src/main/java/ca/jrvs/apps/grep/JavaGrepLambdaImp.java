package ca.jrvs.apps.grep;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import org.apache.log4j.BasicConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JavaGrepLambdaImp extends JavaGrepImp {

  final Logger logger = LoggerFactory.getLogger(JavaGrepLambdaImp.class);

  public static void main(String[] args) {
    if (args.length != 3) {
      throw new IllegalArgumentException("USAGE: JavaGrep regex rootPath outFile");
    }
    BasicConfigurator.configure();

    // Creating JavaGrepLambdaImp instead of JavaGrepImp
    // JavaGrepLambdaImp inherits all methods of JavaGrepImp except for two override methods
    JavaGrepLambdaImp javaGrepLambdaImp = new JavaGrepLambdaImp();
    javaGrepLambdaImp.setRegex(args[0]);
    javaGrepLambdaImp.setRootPath(args[1]);
    javaGrepLambdaImp.setOutFile(args[2]);

    try {
      javaGrepLambdaImp.process();
    } catch (Exception ex) {
      javaGrepLambdaImp.logger.error(ex.getMessage(), ex);
    }
  }

  /**
   * Implement using lambda and stream APIs
   */
  @Override
  public List<File> listFiles(String rootDir) {
    List<File> files = new LinkedList<>();
    try {
      files = Files.walk(Paths.get(rootDir))
          .map(Path::toFile)
          .filter(File::isFile)
          .collect(Collectors.toList());
    } catch (IOException e) {
      logger.error(e.getMessage(), e);
    }
    return files;
  }

  /**
   * Implement using lambda and stream APIs
   */
  @Override
  public List<String> readLines(File inputFile) {
    if (!inputFile.isFile()) {
      throw new IllegalArgumentException(inputFile + " is not a file");
    }
    List<String> lines = new LinkedList<>();
    try {
      lines = Files.lines(inputFile.toPath(), StandardCharsets.UTF_8).collect(Collectors.toList());
    } catch (IOException e) {
      logger.error(e.getMessage(), e);
    }
    return lines;
  }
}
