package ca.jrvs.apps.practice;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class LambdaStreamExcImpTest {

  // For testing output stream contents
  private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
  private final PrintStream originalOut = System.out;
  private LambdaStreamExcImp lambdaStreamExcImp = new LambdaStreamExcImp();

  @Before
  public void setUpStreams() {
    System.setOut(new PrintStream(outContent));
  }

  @After
  public void restoreStreams() {
    System.setOut(originalOut);
  }

  @Test
  public void createStrStream() {
    System.out.println("Test case: test createStrStream method from the test class");
    List<String> expected = Stream.of("hi", "hello", "bonjour").collect(Collectors.toList());
    List<String> result =
        lambdaStreamExcImp.createStrStream("hi", "hello", "bonjour").collect(Collectors.toList());
    assertEquals(expected, result);
  }

  @Test
  public void toUpperCase() {
    System.out.println("Test case: test toUpperCase method from the test class");
    List<String> expected = Stream.of("HI", "HELLO", "BONJOUR").collect(Collectors.toList());
    List<String> result =
        lambdaStreamExcImp.toUpperCase("hI", "HELLO", "bonjour").collect(Collectors.toList());
    assertEquals(expected, result);
  }

  @Test
  public void filter() {
    System.out.println("Test case: test filter method from the test class");
    List<String> expected = Stream.of("hi", "HELLO").collect(Collectors.toList());
    List<String> result =
        lambdaStreamExcImp
            .filter(Stream.of("hi", "HELLO", "bonjour"), "n")
            .collect(Collectors.toList());
    assertEquals(expected, result);
  }

  @Test
  public void createIntStream() {
    System.out.println("Test case: test createIntStream method from the test class");
    int[] expected = {1, 2, 3};
    int[] result = lambdaStreamExcImp.createIntStream(new int[]{1, 2, 3}).toArray();
    assertArrayEquals(expected, result);
  }

  @Test
  public void toList() {
    System.out.println("Test case: test toList method from the test class");
    List<String> expected = Stream.of("hI", "HELLO", "bonjour").collect(Collectors.toList());
    List<String> result = lambdaStreamExcImp.toList(Stream.of("hI", "HELLO", "bonjour"));
    assertEquals(expected, result);
  }

  @Test
  public void testToList() {
    System.out.println("Test case: test testToList method from the test class");
    List<Integer> expected = Arrays.asList(1, 2, 3);
    List<Integer> result = lambdaStreamExcImp.toList(IntStream.of(1, 2, 3));
    assertEquals(expected, result);
  }

  @Test
  public void testCreateIntStream() {
    System.out.println("Test case: test testCreateIntStream method from the test class");
    int[] expected = {1, 2, 3, 4, 5};
    int[] result = lambdaStreamExcImp.createIntStream(1, 5).toArray();
    assertArrayEquals(expected, result);
  }

  @Test
  public void squareRootIntStream() {
    System.out.println("Test case: test squareRootIntStream method from the test class");
    double[] expected = {2.0, 3.0, 4.0};
    double[] result = lambdaStreamExcImp.squareRootIntStream(IntStream.of(4, 9, 16)).toArray();
    assertTrue(Arrays.equals(expected, result));
  }

  @Test
  public void getOdd() {
    System.out.println("Test case: test getOdd method from the test class");
    int[] expected = {1, 3, 5};
    int[] result = lambdaStreamExcImp.getOdd(IntStream.of(1, 2, 3, 4, 5)).toArray();
    assertTrue(Arrays.equals(expected, result));
  }

  @Test
  public void getLambdaPrinter() {
    System.out.println("Test case: test getLambdaPrinter method from the test class");
    String expected = "start>Message body<end";
    Consumer<String> printer = lambdaStreamExcImp.getLambdaPrinter("start>", "<end");
    printer.accept("Message body");
    assertEquals(expected, outContent.toString().split("\n")[1].trim());
  }

  @Test
  public void printMessages() {
    System.out.println("Test case: test printMessages method from the test class");
    String expected = "msg:a!\n" + "msg:b!\n" + "msg:c!";
    String[] messages = {"a", "b", "c"};
    lambdaStreamExcImp.printMessages(messages, lambdaStreamExcImp.getLambdaPrinter("msg:", "!"));
    String[] output = outContent.toString().split("\n");
    String result = output[1].trim() + "\n" + output[2].trim() + "\n" + output[3].trim();
    assertEquals(expected, result);
  }

  @Test
  public void printOdd() {
    System.out.println("Test case: test printOdd method from the test class");
    String expected = "odd number:1!\n" + "odd number:3!\n" + "odd number:5!";
    lambdaStreamExcImp.printOdd(
        lambdaStreamExcImp.createIntStream(0, 5),
        lambdaStreamExcImp.getLambdaPrinter("odd number:", "!"));
    String[] output = outContent.toString().split("\n");
    String result = output[1].trim() + "\n" + output[2].trim() + "\n" + output[3].trim();
    assertEquals(expected, result);
  }

  @Test
  public void flatNestedInt() {
    System.out.println("Test case: test flatNestedInt method from the test class");
    List<Integer> expected = Arrays.asList(1, 4, 9, 16, 25);
    List<Integer> result =
        lambdaStreamExcImp
            .flatNestedInt(Stream.of(Arrays.asList(1, 2, 3), Arrays.asList(4, 5)))
            .collect(Collectors.toList());
    assertEquals(expected, result);
  }
}
