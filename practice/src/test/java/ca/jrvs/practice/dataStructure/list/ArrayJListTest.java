package ca.jrvs.practice.dataStructure.list;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import java.util.Optional;
import org.junit.Test;

public class ArrayJListTest {

  ArrayJList<Integer> arrayJList = new ArrayJList(3);

  @Test
  public void shouldAddTwoIntegers() {
    System.out.println("Testing add()");

    arrayJList.add(1);
    arrayJList.add(2);

    assertEquals(java.util.Optional.of(1), Optional.ofNullable(arrayJList.get(0)));
    assertEquals(java.util.Optional.of(2), Optional.ofNullable(arrayJList.get(1)));
  }

  @Test
  public void shouldResizeToAndAddSixIntegers() {
    System.out.println("Testing add() (resizing of array)");

    arrayJList.add(1);
    arrayJList.add(2);
    arrayJList.add(3);
    arrayJList.add(4);
    arrayJList.add(5);
    arrayJList.add(6);

    assertEquals(6, arrayJList.size());
  }

  @Test
  public void toArray() {
    System.out.println("Testing toArray()");

    arrayJList.add(1);
    arrayJList.add(2);
    arrayJList.add(3);
    arrayJList.add(4);
    arrayJList.add(5);
    arrayJList.add(6);

    Integer[] expectedArray = {1, 2, 3, 4, 5, 6};
    assertArrayEquals(expectedArray, arrayJList.toArray());
  }

  @Test
  public void size() {
    System.out.println("Testing size()");

    //empty list
    assertEquals(0, arrayJList.size());

    //list with three elements
    arrayJList.add(1);
    arrayJList.add(2);
    arrayJList.add(3);

    assertEquals(3, arrayJList.size());
  }

  @Test
  public void isEmpty() {
    System.out.println("Testing isEmpty()");

    //empty list
    assertEquals(true, arrayJList.isEmpty());

    //list with an element
    arrayJList.add(1);

    assertEquals(false, arrayJList.isEmpty());
  }

  @Test
  public void indexOf() {
    System.out.println("Testing indexOf()");

    arrayJList.add(1);
    arrayJList.add(2);
    arrayJList.add(3);
    arrayJList.add(4);
    arrayJList.add(5);
    arrayJList.add(6);

    //element present
    assertEquals(1, arrayJList.indexOf(2));

    //element absent
    assertEquals(-1, arrayJList.indexOf(12));
  }

  @Test
  public void contains() {
    System.out.println("Testing contains()");

    arrayJList.add(1);
    arrayJList.add(2);
    arrayJList.add(3);
    arrayJList.add(4);
    arrayJList.add(5);
    arrayJList.add(6);

    //element present
    assertEquals(true, arrayJList.contains(2));

    //element absent
    assertEquals(false, arrayJList.contains(12));
  }

  @Test(expected = IndexOutOfBoundsException.class)
  public void get() {
    System.out.println("Testing get()");

    arrayJList.add(1);
    arrayJList.add(2);
    arrayJList.add(3);
    arrayJList.add(4);
    arrayJList.add(5);

    //element present
    assertEquals(Optional.of(3), Optional.ofNullable(arrayJList.get(2)));

    //index out of range
    arrayJList.get(20);
  }

  @Test
  public void remove() {
    System.out.println("Testing remove()");

    arrayJList.add(1);
    arrayJList.add(2);
    arrayJList.add(3);
    arrayJList.add(4);
    arrayJList.add(5);
    arrayJList.add(6);

    //element present
    assertEquals(Optional.of(3), Optional.ofNullable(arrayJList.remove(2)));

    //new array
    Integer[] expectedArray = {1, 2, 4, 5, 6};
    assertArrayEquals(expectedArray, arrayJList.toArray());
  }

  @Test
  public void clear() {
    System.out.println("Testing clear()");

    arrayJList.add(1);
    arrayJList.add(2);
    arrayJList.add(3);
    arrayJList.add(4);
    arrayJList.add(5);
    arrayJList.add(6);

    //before clear
    assertEquals(6, arrayJList.size());

    //after clear
    arrayJList.clear();
    assertEquals(0, arrayJList.size());
  }
}