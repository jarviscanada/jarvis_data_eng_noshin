package ca.jrvs.apps.grep;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import org.junit.Test;

public class JavaGrepLambdaImpTest {

  private JavaGrepLambdaImp javaGrepLambdaImp = new JavaGrepLambdaImp();

  @Test
  public void containsPattern() {
    System.out.println("Test case: test containsPattern method from the test class");
    javaGrepLambdaImp.setRegex(".*red.*");
    assertTrue(javaGrepLambdaImp.containsPattern("apple is red fruit"));
    assertFalse(javaGrepLambdaImp.containsPattern("banana is yellow fruit"));
  }

  @Test
  public void writeToFile() {
    System.out.println("Test case: test writeToFile method from the test class");
    javaGrepLambdaImp.setOutFile("output.txt");
    List<String> lines = Arrays.asList("apple is red", "banana is yellow");
    List<String> result = null;
    try {
      javaGrepLambdaImp.writeToFile(lines);
      result = Files.readAllLines(Paths.get("output.txt"));
    } catch (IOException e) {
      System.err.println(e);
    }
    assertEquals(lines, result);
  }

  @Test
  public void listFiles() {
    System.out.println("Test case: test listFiles method from the test class");
    javaGrepLambdaImp.setRootPath("rootDir");
    List<String> resultfileNames =
        javaGrepLambdaImp.listFiles(javaGrepLambdaImp.getRootPath()).stream()
            .map(file -> file.getName())
            .collect(Collectors.toList());
    List<String> expectedFileNames =
        Arrays.asList(
            "emptyLines",
            "fruitList",
            "veggieList",
            "empty",
            "redColor",
            "Color");
    assertEquals(resultfileNames, expectedFileNames);
  }

  @Test
  public void readLines() {
    System.out.println("Test case: test readLines method from the test class");
    List<String> resultLines =
        javaGrepLambdaImp.readLines(new File("output.txt"));
    List<String> expectedLines = Arrays.asList("apple is red", "banana is yellow");
    assertEquals(resultLines, expectedLines);
  }
}
