package ca.jrvs.practice.dataStructure.map;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.Set;

public class HashJMap<K, V> implements JMap<K, V> {

  /**
   * The default initial capacity - MUST be a power of two.
   */
  static final int DEFAULT_INITIAL_CAPACITY = 1 << 4; // aka 16

  /**
   * The load factor used when none specified in constructor.
   */
  static final float DEFAULT_LOAD_FACTOR = 0.75f;
  /**
   * The load factor for the hash table.
   *
   * @serial
   */
  final float loadFactor;
  /**
   * The table, initialized on first use, and resized as necessary. When allocated, length is always
   * a power of two. (We also tolerate length zero in some operations to allow bootstrapping
   * mechanics that are currently not needed.)
   */
  public Node<K, V>[] table;
  /**
   * Holds cached entrySet(). Note that AbstractMap fields are used for keySet() and values().
   */
  Set<Map.Entry<K, V>> entrySet;
  /**
   * The number of key-value mappings contained in this map.
   */
  int size;
  /**
   * The next size value at which to resize (capacity * load factor). Use #capacity() to compute
   * capacity
   *
   * @serial
   */
  // (The javadoc description is true upon serialization.
  // Additionally, if the table array has not been allocated, this
  // field holds the initial array capacity, or zero signifying
  // DEFAULT_INITIAL_CAPACITY.)
  int threshold;

  public HashJMap() {
    this(DEFAULT_INITIAL_CAPACITY, DEFAULT_LOAD_FACTOR);
  }

  /**
   * Constructs an empty <tt>HashMap</tt> with the specified initial capacity and load factor.
   *
   * @param initialCapacity the initial capacity
   * @param loadFactor      the load factor
   * @throws IllegalArgumentException if the initial capacity is negative or the load factor is
   *                                  nonpositive
   */
  public HashJMap(int initialCapacity, float loadFactor) {
    if (initialCapacity <= 0 || loadFactor <= 0 || Float.isNaN(loadFactor)) {
      throw new IllegalArgumentException("Illegal arguments");
    }
    this.loadFactor = loadFactor;
    this.threshold = (int) ((float) initialCapacity * loadFactor);
    //init this.table
    this.table = new Node[threshold];
    this.entrySet = new HashSet<>();
  }

  public static void main(String[] args) {
    HashJMap<String, String> map = new HashJMap<String, String>(3, 0.75f);

    map.put("1", "Amy");
    map.put("2", "Bob");
    map.put("3", "Jane");
    map.put("4", "Ben");

    System.out.println("2 exists : " + map.containsKey("2"));
    System.out.println("5 exists : " + map.containsKey("5"));

    System.out.println("2 is : " + map.get("2"));
    System.out.println("5 is : " + map.get("5"));

    System.out.println("Size of HashJMap : " + map.size());

  }

  /**
   * Associates the specified value with the specified key in this map (optional operation).  If the
   * map previously contained a mapping for the key, the old value is replaced by the specified
   * value.  (A map
   * <tt>m</tt> is said to contain a mapping for a key <tt>k</tt> if and only
   * if {@link #containsKey(Object) m.containsKey(k)} would return
   * <tt>true</tt>.)
   * <p>
   * Key cannot be null
   *
   * @param key   key with which the specified value is to be associated
   * @param value value to be associated with the specified key
   * @return the previous value associated with <tt>key</tt>, or
   * <tt>null</tt> if there was no mapping for <tt>key</tt>.
   * (A <tt>null</tt> return can also indicate that the map previously associated <tt>null</tt>
   * with
   * <tt>key</tt>, if the implementation supports <tt>null</tt> values.)
   * @throws NullPointerException if the specified key or value is null and this map does not permit
   *                              null keys or values
   */
  @Override
  public V put(K key, V value) {
    if (key == null || value == null) {
      throw new NullPointerException("Key and value cannot be null");
    }

    //using key.hashcode to compute the bucket location (this.table)
    int index = hash(key) % (table.length - 1);

    //store KV in the table[index] (as Node<K,V>)
    //if key already exist (use #containsKey) update the value instead
    //if table[index] is taken, link the KV pair next to the exiting KV pair
    HashJMap.Node<K, V> newNode = new HashJMap.Node<K, V>(hash(key), key, value, null);
    if (table[index] == null) {
      this.table[index] = newNode;
    } else {
      HashJMap.Node<K, V> n = this.table[index];
      if (containsKey(key)) {
        while (n != null) {
          if (key.equals(n.getKey())) {
            n.setValue(value);
          }
          n = n.next;
        }
      } else {
        newNode.next = n;
        n = newNode;
      }
    }

    //add KV pair to this.entrySet
    this.entrySet.add(newNode);

    //if this.size is greater than threshold, double table and re-hash
    //(iterate through this.entrySet for re-hashing )
    if (this.size() > this.threshold) {
      table = new Node[2 * table.length];
      this.threshold = (int) ((float) table.length * loadFactor);
      Set<Map.Entry<K, V>> newEntrySet = new HashSet<>();
      newEntrySet.addAll(this.entrySet);
      this.entrySet.clear();
      Iterator<Entry<K, V>> iterator = newEntrySet.iterator();
      while (iterator.hasNext()) {
        Entry<K, V> entry = iterator.next();
        K entryKey = entry.getKey();
        V entryValue = entry.getValue();
        put(entryKey, entryValue);
      }

    }

    return null;
  }

  final int capacity() {
    return (table != null) ? table.length :
        (threshold > 0) ? threshold :
            DEFAULT_INITIAL_CAPACITY;
  }

  /**
   * Returns <tt>true</tt> if this map contains a mapping for the specified key.  More formally,
   * returns <tt>true</tt> if and only if this map contains a mapping for a key <tt>k</tt> such
   * that
   * <tt>(key==null ? k==null : key.equals(k))</tt>.  (There can be
   * at most one such mapping.)
   *
   * @param key key whose presence in this map is to be tested
   * @return <tt>true</tt> if this map contains a mapping for the specified
   * key
   * @throws NullPointerException if the specified key is null and this map does not permit null
   *                              keys (<a href="{@docRoot}/java/util/Collection.html#optional-restrictions">optional</a>)
   */
  @Override
  public boolean containsKey(Object key) {
    //validate key == null
    if (key == null) {
      throw new NullPointerException("Key cannot be null");
    }

    //using key.hashcode to compute the bucket location (this.table)
    int index = hash((K) key) % (table.length - 1);

    //if there is more than one Node<K,V> in the bucket,
    //traverse through the linkedList and use `equals` to find the key
    if (table[index] == null) {
      return false;
    } else {
      Node n = table[index];
      while (n != null) {
        if (n.getKey().equals(key)) {
          return true;
        }
        n = n.next;
      }
    }
    return false;
  }

  /**
   * Returns the value to which the specified key is mapped, or {@code null} if this map contains no
   * mapping for the key.
   *
   * @param key the key whose associated value is to be returned
   * @return the value to which the specified key is mapped, or {@code null} if this map contains no
   * mapping for the key
   * @throws NullPointerException if the specified key is null and this map does not permit null
   *                              keys (<a href="{@docRoot}/java/util/Collection.html#optional-restrictions">optional</a>)
   */
  @Override
  public V get(Object key) {
    //validate key == null
    if (key == null) {
      throw new NullPointerException("Key cannot be null");
    }

    //using key.hashcode to compute the bucket location (this.table)
    int index = hash((K) key) % (table.length - 1);

    //if there is more than one Node<K,V> in the bucket,
    //traverse through the linkedList and use `equals` to find the key
    if (table[index] == null) {
      return null;
    } else {
      Node n = table[index];
      while (n != null) {
        if (n.getKey().equals(key)) {
          return (V) n.getValue();
        }
        n = n.next;
      }
    }
    return null;
  }

  /**
   * Returns the number of key-value mappings in this map.  If the map contains more than
   * <tt>Integer.MAX_VALUE</tt> elements, returns
   * <tt>Integer.MAX_VALUE</tt>.
   *
   * @return the number of key-value mappings in this map
   */
  @Override
  public int size() {
    if (this.entrySet.size() > Integer.MAX_VALUE) {
      return Integer.MAX_VALUE;
    } else {
      return this.entrySet.size();
    }
  }

  /**
   * Returns a {@link Set} view of the mappings contained in this map. The set is backed by the map,
   * so changes to the map are reflected in the set, and vice-versa.
   *
   * @return a set view of the mappings contained in this map
   */
  @Override
  public Set<Entry<K, V>> entrySet() {
    return this.entrySet;
  }

  /**
   * Generates hashcode for key
   *
   * @param key
   * @return
   */
  public int hash(K key) {
    int h;
    return (h = key.hashCode()) ^ (h >>> 16);
  }

  /**
   * Basic hash bin node, used for most entries.  (See below for TreeNode subclass, and in
   * LinkedHashMap for its Entry subclass.)
   */
  static class Node<K, V> implements Map.Entry<K, V> {

    final int hash;
    final K key;
    V value;
    HashJMap.Node<K, V> next;

    Node(int hash, K key, V value, HashJMap.Node<K, V> next) {
      this.hash = hash;
      this.key = key;
      this.value = value;
      this.next = next;
    }

    public final K getKey() {
      return key;
    }

    public final V getValue() {
      return value;
    }

    public final String toString() {
      return key + "=" + value;
    }

    public final int hashCode() {
      return Objects.hashCode(key) ^ Objects.hashCode(value);
    }

    public final V setValue(V newValue) {
      V oldValue = value;
      value = newValue;
      return oldValue;
    }

    public final boolean equals(Object o) {
      if (o == this) {
        return true;
      }
      if (o instanceof Map.Entry) {
        Map.Entry<?, ?> e = (Map.Entry<?, ?>) o;
        if (Objects.equals(key, e.getKey()) &&
            Objects.equals(value, e.getValue())) {
          return true;
        }
      }
      return false;
    }
  }
}