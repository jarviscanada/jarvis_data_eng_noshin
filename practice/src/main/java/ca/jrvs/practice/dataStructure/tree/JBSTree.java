package ca.jrvs.practice.dataStructure.tree;

import java.util.Comparator;
import java.util.Objects;

/**
 * A simplified BST implementation
 *
 * @param <E> type of object to be stored
 */
public class JBSTree<E extends Comparable<E>> implements JTree<E> {

  private Node<E> root;

  /**
   * The comparator used to maintain order in this tree map Comparator cannot be null
   */
  private Comparator<E> comparator;

  /**
   * Create a new BST with null root
   *
   * @param comparator the comparator that will be used to order this tree.
   * @throws IllegalArgumentException if comparator is null
   */
  public JBSTree(Comparator<E> comparator) {
    if (comparator == null) {
      throw new IllegalArgumentException("A comparator must be provided");
    }
    this.comparator = comparator;
  }

  /**
   * Creates new BST with provided root
   *
   * @param comparator the comparator that will be used to order this tree
   * @param root       provided root of the tree
   * @throws IllegalArgumentException if comparator is null
   */
  public JBSTree(Comparator<E> comparator, Node<E> root) {
    if (comparator == null) {
      throw new IllegalArgumentException("A comparator must be provided");
    }
    this.comparator = comparator;
    this.root = root;
  }

  public static void main(String[] args) {
    Comparator<Integer> integerComparator = new Comparator<Integer>() {
      @Override
      public int compare(Integer o1, Integer o2) {
        return o1 - o2;
      }
    };
    JBSTree.Node<Integer> root = new JBSTree.Node<Integer>(5, null);

    JBSTree<Integer> jbsTree = new JBSTree<Integer>(integerComparator, root);

    System.out.println("Inserting objects...");
    jbsTree.insert(2);
    jbsTree.insert(1);
    jbsTree.insert(3);
    jbsTree.insert(7);
    jbsTree.insert(8);
    jbsTree.insert(6);

    System.out.println("In order result:");
    jbsTree.inOrder(root);
    System.out.println();

    System.out.println("Searching for 6..");
    if (jbsTree.search(6) == 6) {
      System.out.println("Found 6!");
    }
    System.out.println("Searching for 0..");
    if (jbsTree.search(0) == null) {
      System.out.println("Did not find 0.");
    }

    System.out.println("Height of tree:");
    System.out.println(jbsTree.findHeight());

    System.out.println("Removing 8..");
    if (jbsTree.remove(8) == 8) {
      System.out.println("Removed 8");
    }

    System.out.println("Removing 7..");
    if (jbsTree.remove(7) == 7) {
      System.out.println("Removed 7");
    }

    System.out.println("Removing 2..");
    if (jbsTree.remove(2) == 2) {
      System.out.println("Removed 2");
    }

    System.out.println("In order result:");
    jbsTree.inOrder(root);
    System.out.println();

    System.out.println("Height of tree:");
    System.out.println(jbsTree.findHeight());
  }

  /**
   * Insert an object into the BST. Please review the BST property.
   *
   * @param object item to be inserted
   * @return inserted item
   * @throws IllegalArgumentException if the object already exists
   */
  @Override
  public E insert(E object) {
    if (root == null) {
      root = new Node<E>(object, null);
    } else if (search(object) != null) {
      throw new IllegalArgumentException("This tree does not support duplicates.");
    } else {
      Node parent = null;
      Node n = root;
      boolean leftChild = false;
      while (n != null) {
        if (n.value.compareTo(object) < 0) {
          parent = n;
          n = n.right;
          leftChild = false;
        } else {
          parent = n;
          n = n.left;
          leftChild = true;
        }
      }
      if (leftChild) {
        parent.setLeft(new Node(object, parent));
      } else {
        parent.setRight(new Node(object, parent));
      }
    }
    return object;
  }

  /**
   * Search and return an object, return null if not found
   *
   * @param object to be found
   * @return the object if exists or null if not
   */
  @Override
  public E search(E object) {
    Node n = root;
    while (n != null) {
      if (n.value.equals(object)) {
        return object;
      } else if (n.value.compareTo(object) < 0) {
        n = n.right;
      } else {
        n = n.left;
      }
    }
    return null;
  }

  /**
   * Remove an object from the tree.
   *
   * @param object to be removed
   * @return removed object
   * @throws IllegalArgumentException if the object not exists
   */
  @Override
  public E remove(E object) {
    Node n = root;
    Node parent = null;
    boolean leftChild = false;
    while (n != null) {
      if (n.value.equals(object)) {
        //Case 1: Node is leaf node
        if (n.left == null && n.right == null) {
          if (leftChild) {
            parent.setLeft(null);
          } else {
            parent.setRight(null);
          }
        }
        //Case 2: Node has two children
        else if ((n.left != null) && (n.right != null)) {
          Node successor = findLeftmost(n.right);
          n.value = successor.value;
          if (successor.parent.left.equals(successor)) {
            successor.parent.left = null;
          } else {
            successor.parent.right = null;
          }
        }
        //Case 2: Node has one child
        else {
          if (n.left != null) {
            if (leftChild) {
              parent.left = n.left;
            } else {
              parent.right = n.left;
            }
          } else {
            if (leftChild) {
              parent.left = n.right;
            } else {
              parent.right = n.right;
            }
          }
        }
        return object;
      } else if (n.value.compareTo(object) < 0) {
        parent = n;
        n = n.right;
        leftChild = false;
      } else {
        parent = n;
        n = n.left;
        leftChild = true;
      }
    }
    throw new IllegalArgumentException("Object not found in tree");
  }

  private Node findLeftmost(Node node) {
    Node n = node;
    while (n.left != null) {
      n = n.left;
    }
    return n;
  }

  /**
   * traverse the tree recursively
   */
  @Override
  public void preOrder(Node n) {
    if (n != null) {
      System.out.print(n.value + " ");
      preOrder(n.left);
      preOrder(n.right);
    }
  }

  /**
   * traverse the tree recursively
   *
   * @return all objects in-order
   */
  @Override
  public void inOrder(Node n) {
    if (n != null) {
      inOrder(n.left);
      System.out.print(n.value + " ");
      inOrder(n.right);
    }
  }

  /**
   * traverse the tree recursively
   *
   * @return all objects pre-order
   */
  @Override
  public void postOrder(Node n) {
    if (n != null) {
      postOrder(n.left);
      postOrder(n.right);
      System.out.print(n.value + " ");
    }
  }

  /**
   * traverse through the tree and find out the tree height
   *
   * @return height
   * @throws NullPointerException if the BST is empty
   */
  @Override
  public int findHeight() {
    return maxHeight(root);
  }

  private int maxHeight(Node<E> root) {
    Node n = root;
    if (n == null) {
      return 0;
    }
    return Math.max(maxHeight(n.left), maxHeight(n.right)) + 1;
  }

  static final class Node<E extends Comparable<E>> {

    E value;
    Node<E> left;
    Node<E> right;
    Node<E> parent;

    public Node(E value, Node<E> parent) {
      this.value = value;
      this.parent = parent;
    }

    public E getValue() {
      return value;
    }

    public void setValue(E value) {
      this.value = value;
    }

    public Node<E> getLeft() {
      return left;
    }

    public void setLeft(Node<E> left) {
      this.left = left;
    }

    public Node<E> getRight() {
      return right;
    }

    public void setRight(Node<E> right) {
      this.right = right;
    }

    public Node<E> getParent() {
      return parent;
    }

    public void setParent(Node<E> parent) {
      this.parent = parent;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) {
        return true;
      }
      if (!(o instanceof Node)) {
        return false;
      }
      Node<?> node = (Node<?>) o;
      return getValue().equals(node.getValue()); //&&
      //Objects.equals(getLeft(), node.getLeft()) &&
      //Objects.equals(getRight(), node.getRight()) &&
      //getParent().equals(node.getParent());
    }

    @Override
    public int hashCode() {
      return Objects.hash(getValue(), getLeft(), getRight(), getParent());
    }
  }

}