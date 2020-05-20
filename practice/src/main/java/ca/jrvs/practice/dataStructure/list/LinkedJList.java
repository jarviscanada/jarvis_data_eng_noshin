package ca.jrvs.practice.dataStructure.list;

import java.util.HashMap;
import java.util.HashSet;

public class LinkedJList<E> implements JList<E> {

  /**
   * Default initial capacity.
   */
  private static final int DEFAULT_CAPACITY = 10;

  /**
   * The head and tail node of the LinkedList where the data will be stored
   */
  transient Node head;
  transient Node tail;
  /**
   * The size of the LinkedList (the number of elements it contains).
   */
  private int size;

  /**
   * Constructs an empty list with the specified initial capacity.
   *
   * @param initialCapacity the initial capacity of the list
   * @throws IllegalArgumentException if the specified initial capacity is negative
   */
  public LinkedJList(int initialCapacity) {
    if (initialCapacity > 0) {
      this.head = new Node(null, this.head, this.head);
      this.tail = this.head;
    } else {
      throw new IllegalArgumentException("Illegal Capacity: " +
          initialCapacity);
    }
  }

  /**
   * Constructs an empty list with an initial capacity of ten.
   */
  public LinkedJList() {
    this(DEFAULT_CAPACITY);
  }

  public Node getHead() {
    return head;
  }

  public void setHead(Node head) {
    this.head = head;
  }

  public Node getTail() {
    return tail;
  }

  public void setTail(Node tail) {
    this.tail = tail;
  }

  public void setSize(int size) {
    this.size = size;
  }

  /**
   * Appends the specified element to the end of this list (optional operation).
   *
   * @param o element to be appended to this list
   * @return <tt>true</tt>
   * @throws NullPointerException if the specified element is null and this list does not permit
   *                              null elements
   */
  @Override
  public boolean add(Object o) {
    if (o == null) {
      throw new NullPointerException("Cannot enter null values");
    }
    if (size == 0) {
      this.head.setData(o);
    } else {
      Node node = new Node(o, tail, null);
      tail.next = node;
      tail = node;
    }
    size++;
    return true;
  }

  /**
   * Returns an array containing all of the elements in this list in proper sequence (from first to
   * last element).
   *
   * <p>This method acts as bridge between array-based and collection-based
   * APIs.
   *
   * @return an array containing all of the elements in this list in proper sequence
   */
  @Override
  public Object[] toArray() {
    Object[] tempArray = new Object[size];
    Node n = this.head;
    int i = 0;
    while (i<size) {
      tempArray[i] = n.getData();
      i++;
      n = n.next;
    }
    return tempArray;
  }

  /**
   * The size of the ArrayList (the number of elements it contains).
   */
  @Override
  public int size() {
    return size;
  }

  /**
   * Returns <tt>true</tt> if this list contains no elements.
   *
   * @return <tt>true</tt> if this list contains no elements
   */
  @Override
  public boolean isEmpty() {
    if (size == 0) {
      return true;
    } else {
      return false;
    }
  }

  /**
   * Returns the index of the first occurrence of the specified element in this list, or -1 if this
   * list does not contain the element. More formally, returns the lowest index <tt>i</tt> such
   * that
   * <tt>(o==null&nbsp;?&nbsp;get(i)==null&nbsp;:&nbsp;o.equals(get(i)))</tt>,
   * or -1 if there is no such index.
   *
   * @param o
   */
  @Override
  public int indexOf(Object o) {
    Node n = this.head;
    int i = 0;
    while (n != null) {
      if (o.equals(n.getData())) {
        return i;
      }
      i++;
      n = n.next;
    }
    return -1;
  }

  /**
   * Returns <tt>true</tt> if this list contains the specified element. More formally, returns
   * <tt>true</tt> if and only if this list contains at least one element <tt>e</tt> such that
   * <tt>(o==null&nbsp;?&nbsp;e==null&nbsp;:&nbsp;o.equals(e))</tt>.
   *
   * @param o element whose presence in this list is to be tested
   * @return <tt>true</tt> if this list contains the specified element
   * @throws NullPointerException if the specified element is null and this list does not permit
   *                              null elements
   */
  @Override
  public boolean contains(Object o) {
    if (indexOf(o) == -1) {
      return false;
    } else {
      return true;
    }
  }

  /**
   * Returns the element at the specified position in this list.
   *
   * @param index index of the element to return
   * @return the element at the specified position in this list
   * @throws IndexOutOfBoundsException if the index is out of range (<tt>index &lt; 0 || index &gt;=
   *                                   size()</tt>)
   */
  @Override
  public E get(int index) {
    if (index < 0 || index >= size()) {
      throw new IndexOutOfBoundsException("Index range : 0 - " + (size() - 1));
    }
    Node n = this.head;
    int i = 0;
    while (n != null) {
      if (i == index) {
        return (E) n.getData();
      }
      i++;
      n = n.next;
    }
    return null;
  }

  /**
   * Removes the element at the specified position in this list. Shifts any subsequent elements to
   * the left (subtracts one from their indices).
   *
   * @param index the index of the element to be removed
   * @return the element that was removed from the list
   * @throws IndexOutOfBoundsException {@inheritDoc}
   */
  @Override
  public E remove(int index) {
    if (index < 0 || index >= size()) {
      throw new IndexOutOfBoundsException("Index range : 0 - " + (size() - 1));
    }
    Node n = this.head;
    int i = 0;
    while (n != null) {
      if (i == index) {
        E removed = (E) n.getData();
        if (size == 1) {
          this.head.setData(null);
        } else {
          if (i == 0) {
            this.head = n.next;
            this.head.prev = this.head;
            n = null;
          } else if (i == size - 1) {
            this.tail = n.prev;
            this.tail.next = this.tail;
            n = null;
          } else {
            n.prev.next = n.next;
            n.next.prev = n.prev;
            n = null;
          }
        }
        size--;
        return removed;
      }
      i++;
      n = n.next;
    }
    return null;
  }

  /**
   * Big-O: O(n), where n is the length of list
   * Justification: iterates over the list only once
   *
   * Removes duplicates from the list
   */
  public void removeDuplicates(){
    HashSet<Object> elements = new HashSet<>();
    Node n = head;
    int i=0;
    while(n!=null){
      if(elements.contains(n.getData())){
        if (size == 1) {
          this.head.setData(null);
        } else {
          if (n == this.head) {
            this.head = n.next;
            this.head.prev = this.head;
          } else if (n == this.tail) {
            this.tail = n.prev;
            this.tail.next = this.tail;
          } else {
            n.prev.next = n.next;
            n.next.prev = n.prev;
          }
        }
        size--;
      }else{
        elements.add(n.getData());
      }
      n=n.next;
    }
  }

  /**
   * Removes all of the elements from this list (optional operation). The list will be empty after
   * this call returns.
   */
  @Override
  public void clear() {
    this.head.setData(null);
    this.head.next = this.head;
    this.head.prev = this.head;
    this.tail = this.head;
    size = 0;
  }

  public class Node {

    public Node prev;
    public Node next;
    private Object data;

    public Node(Object data) {
      this.data = data;
    }

    public Node(Object data, Node prev, Node next) {
      this.data = data;
      this.prev = prev;
      this.next = next;
    }

    public Object getData() {
      return data;
    }

    public void setData(Object data) {
      this.data = data;
    }
  }
}
