package ca.jrvs.practice.dataStructure.list;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

import java.util.Optional;
import org.junit.Test;

public class LinkedJListTest {

  JList<Integer> linkedlist = new LinkedJList<>();

  @Test
  public void shouldAddTwoIntegers() {
    System.out.println("Testing add()");

    linkedlist.add(1);
    linkedlist.add(2);

    assertEquals(Optional.of(1), Optional.ofNullable(linkedlist.get(0)));
    assertEquals(Optional.of(2), Optional.ofNullable(linkedlist.get(1)));
  }

  @Test
  public void toArray() {
    System.out.println("Testing toArray()");

    linkedlist.add(1);
    linkedlist.add(2);
    linkedlist.add(3);
    linkedlist.add(4);
    linkedlist.add(5);
    linkedlist.add(6);

    Integer[] expectedArray = {1, 2, 3, 4, 5, 6};
    assertArrayEquals(expectedArray, linkedlist.toArray());
  }

  @Test
  public void size() {
    System.out.println("Testing size()");

    //empty list
    assertEquals(0, linkedlist.size());

    //list with three elements
    linkedlist.add(1);
    linkedlist.add(2);
    linkedlist.add(3);

    assertEquals(3, linkedlist.size());
  }

  @Test
  public void isEmpty() {
    System.out.println("Testing isEmpty()");

    //empty list
    assertEquals(true, linkedlist.isEmpty());

    //list with an element
    linkedlist.add(1);

    assertEquals(false, linkedlist.isEmpty());
  }

  @Test
  public void indexOf() {
    System.out.println("Testing indexOf()");

    linkedlist.add(1);
    linkedlist.add(2);
    linkedlist.add(3);
    linkedlist.add(4);
    linkedlist.add(5);
    linkedlist.add(6);

    //element present
    assertEquals(1, linkedlist.indexOf(2));

    //element absent
    assertEquals(-1, linkedlist.indexOf(12));
  }

  @Test
  public void contains() {
    System.out.println("Testing contains()");

    linkedlist.add(1);
    linkedlist.add(2);
    linkedlist.add(3);
    linkedlist.add(4);
    linkedlist.add(5);
    linkedlist.add(6);

    //element present
    assertEquals(true, linkedlist.contains(2));

    //element absent
    assertEquals(false, linkedlist.contains(12));
  }

  @Test(expected = IndexOutOfBoundsException.class)
  public void get() {
    System.out.println("Testing get()");

    linkedlist.add(1);
    linkedlist.add(2);
    linkedlist.add(3);
    linkedlist.add(4);
    linkedlist.add(5);

    //element present
    assertEquals(Optional.of(3), Optional.ofNullable(linkedlist.get(2)));

    //index out of range
    linkedlist.get(20);
  }

  @Test
  public void remove() {
    System.out.println("Testing remove()");

    linkedlist.add(1);
    linkedlist.add(2);
    linkedlist.add(3);
    linkedlist.add(4);
    linkedlist.add(5);
    linkedlist.add(6);

    //element present
    assertEquals(Optional.of(3), Optional.ofNullable(linkedlist.remove(2)));

    //new array
    Integer[] expectedArray = {1, 2, 4, 5, 6};
    assertArrayEquals(expectedArray, linkedlist.toArray());
  }

  @Test
  public void shouldRemoveDuplicates() {
    System.out.println("Testing removeDuplicates()");

    linkedlist.add(1);
    linkedlist.add(2);
    linkedlist.add(2);
    linkedlist.add(5);
    linkedlist.add(1);
    linkedlist.add(3);
    linkedlist.add(4);
    linkedlist.add(3);
    linkedlist.removeDuplicates();

    Integer[] expectedArray = {1,2,5,3,4};
    assertArrayEquals(expectedArray, linkedlist.toArray());

  }

  @Test
  public void clear() {
    System.out.println("Testing clear()");

    linkedlist.add(1);
    linkedlist.add(2);
    linkedlist.add(3);
    linkedlist.add(4);
    linkedlist.add(5);
    linkedlist.add(6);

    //before clear
    assertEquals(6, linkedlist.size());

    //after clear
    linkedlist.clear();
    assertEquals(0, linkedlist.size());
  }

}