package ca.jrvs.practice.dataStructure.stackQueue;

import ca.jrvs.practice.dataStructure.list.LinkedJList;
import java.util.NoSuchElementException;

public class LinkedJListJDeque<E> extends LinkedJList<E> implements JDeque<E> {

  /**
   * Constructs an empty list with the specified initial capacity.
   *
   * @param initialCapacity the initial capacity of the list
   * @throws IllegalArgumentException if the specified initial capacity is negative
   */
  public LinkedJListJDeque(int initialCapacity) {
    super(initialCapacity);
  }

  /**
   * Constructs an empty list with an initial capacity of ten.
   */
  public LinkedJListJDeque() {
  }

  /**
   * This is equivalent dequeue operation in Queue ADT
   * <p>
   * Retrieves and removes the head of the queue represented by this deque (in other words, the
   * first element of this deque).
   *
   * @return the head of the queue represented by this deque
   * @throws NoSuchElementException if this deque is empty
   */
  @Override
  public E remove() {
    if (isEmpty()) {
      throw new NoSuchElementException("JDeque is empty");
    }
    E element = super.remove(0);
    return element;
  }

  /**
   * Pops an element from the stack represented by this deque. In other words, removes and returns
   * the first element of this deque.
   *
   * @return the element at the front of this deque (which is the top of the stack represented by
   * this deque)
   * @throws NoSuchElementException if this deque is empty
   */
  @Override
  public E pop() {
    if (isEmpty()) {
      throw new NoSuchElementException("JDeque is empty");
    }
    E element = super.remove(0);
    return element;
  }

  /**
   * Pushes an element onto the stack represented by this deque (in other words, at the head of this
   * deque) if it is possible to do so immediately without violating capacity restrictions
   *
   * @param o the element to push
   * @throws NullPointerException if the specified element is null and this deque does not permit
   *                              null elements
   */
  @Override
  public void push(Object o) {
    if (o == null) {
      throw new NullPointerException("Cannot enter null values");
    }
    Node head = getHead();
    if (size() == 0) {
      head.setData(o);
    } else {
      Node node = new Node(o, null, head);
      node.prev = node;
      head.prev = node;
      head = node;
      setHead(head);
    }
    setSize(size() + 1);
  }

  /**
   * Retrieves, but does not remove, the head of the queue represented by this deque (in other
   * words, the first element of this deque), or returns {@code null} if this deque is empty.
   *
   * @return the head of the queue represented by this deque, or {@code null} if this deque is empty
   */
  @Override
  public E peek() {
    if (isEmpty()) {
      return null;
    } else {
      return (E) getHead().getData();
    }
  }
}
