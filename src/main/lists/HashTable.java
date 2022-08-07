import java.util.function.ToIntFunction;

/** A special (singly) linked list node class for hash tables. */
class HashTableNode<K, V> {
  K key;
  V value;
  HashTableNode<K, V> next;

  HashTableNode(K key, V value, HashTableNode<K, V> next) {
    this.key = key;
    this.value = value;
    this.next = next;
  }
}

/**
 * An implementation of the hash table data structure that uses chaining to resolve collisions.
 *
 * <p>The implementation uses a static array whose individual elements we call "buckets". Each
 * bucket contains a linked list of {@code HashTableNode}. Each node stores a key-value pair. The
 * bucket is {@code null} if no key-value pair whose key hashes to the bucket's index has been
 * added.
 *
 * <p>All operations ({@code put}, {@code remove}, and {@code get}) are O(1) at best (when the load
 * factor is good and the hash function is random) and O(n) at worst (when a hash function produces
 * highest clustering).
 */
@SuppressWarnings("unchecked")
public class HashTable<K, V> {
  /**
   * The buckets of this hash table, each containing a singly linked list for storing data, or
   * {@code null}.
   */
  Object[] buckets;
  /**
   * The maximum load factor allowed for this hash table.
   *
   * <p>If putting a new key would cause the hash table to exceed this max amount, the table will
   * double its size.
   *
   * <p>Good load factors are between 0.5 and 2.0 where the hash table would not become too sparse
   * or too dense. This implementation defaults to 0.75.
   *
   * @see #put(Object, Object)
   */
  float loadFactor = 0.75f;
  /**
   * The hash function used to calculate the index for an incoming key-value pair.
   *
   * <p>Hash function takes only one parameter, usually the number of buckets (also known as
   * capacity), and always returns an integer.
   */
  ToIntFunction<Integer> hashFunction = k -> k % this.capacity; // h(k)
  /** The number of key-value pairs that are stored by this hash table. */
  int length;
  /**
   * The number of buckets in this hash table.
   *
   * <p>While this is usually the same as {@code this.buckets.length}, this variable was introduced
   * so the hashing function could refer to the new capacity instead of the current capacity.
   *
   * @see #resize(int)
   */
  int capacity;

  /** Initializes a new hash table with 16 buckets and a maximum load factor of 0.75. */
  public HashTable() {
    this.buckets = new Object[this.capacity = 16];
    this.length = 0;
  }

  /**
   * Initializes a new hash table with the specified initial capacity and a maximum load factor of
   * 0.75.
   *
   * @param initialCapacity
   */
  public HashTable(int initialCapacity) {
    this.buckets = new Object[this.capacity = initialCapacity];
    this.length = 0;
  }

  /**
   * Initializes a new hash table with the specified initial capacity and maximum load factor.
   *
   * @param initialCapacity
   * @param loadFactor
   * @throws IllegalArgumentException if the load factor is or is below 0.
   */
  public HashTable(int initialCapacity, float loadFactor) {
    if (loadFactor <= 0) throw new IllegalArgumentException("Illegal load factor: " + loadFactor);

    this.buckets = new Object[this.capacity = initialCapacity];
    this.loadFactor = loadFactor;
    this.length = 0;
  }

  /**
   * Returns the number of key-value pairs in this hash table.
   *
   * @return the number of key-value pairs in this hash table
   */
  public int getLength() {
    return this.length;
  }

  /**
   * Returns the number of buckets in this hash table.
   *
   * @return the number of buckets in this hash table
   */
  public int getCapacity() {
    return this.capacity;
  }

  /**
   * Returns the active hash function of this hash table.
   *
   * @return the active hash function of this hash table
   */
  public ToIntFunction<Integer> getHashFunction() {
    return this.hashFunction;
  }

  /**
   * Sets the hash function for this hash table.
   *
   * @param hashFunction
   */
  public void setHashFunction(ToIntFunction<Integer> hashFunction) {
    this.hashFunction = hashFunction;
  }

  /**
   * Puts a {@code HashTableNode} into the specified array of buckets.
   *
   * <p>A new node will be created if the key does not already exist among the buckets. Otherwise
   * assigns the {@code value} field of the existing node to the specified value.
   *
   * <p>This function is created for internal use and is shared by {@code put} and {@code resize}.
   * In particular, it helps {@code resize} avoid using O(n) space.
   *
   * @param newNode
   * @param buckets
   * @return {@code true} if a new node was created, {@code false} if an existing node was
   *     overridden.
   * @see HashTableNode
   */
  private boolean put(HashTableNode<K, V> newNode, Object[] buckets) {
    // Since we are adding to the end of the corresponding linked list, make
    // sure .next is null.
    newNode.next = null;
    // The index which is the h(k) or the return value of the hash function.
    int index = this.hashFunction.applyAsInt(newNode.key.hashCode());

    if (buckets[index] == null) {
      buckets[index] = newNode;
      return true;
    } else {
      HashTableNode<K, V> node = (HashTableNode<K, V>) buckets[index];

      while (true) {
        if (node.key.equals(newNode.key)) { // A node with the same key exists
          node.value = newNode.value;
          return false;
        }

        // Otherwise move to the end of the linked list
        if (node.next != null) node = node.next;
        else break;
      }

      node.next = newNode;
      return true;
    }
  }

  /**
   * Puts a new key-value pair into this hash table.
   *
   * <p>If a node with the same key already exists, overrides the {@code value} field of that node
   * only. Otherwise create a new {@code HashTableNode}.
   *
   * <p>If adding would cause the hash table to exceed the maximum load factor, double the capacity
   * before adding.
   *
   * @param key
   * @param value
   * @see #put(HashTableNode, Object[])
   */
  public void put(K key, V value) {
    // Double in size if the load factor would be exceeded.
    if ((float) (this.length + 1) / this.capacity > this.loadFactor) this.expand();

    // Increase the length only if a node was created (as oppossed to overridden)
    if (this.put(new HashTableNode<K, V>(key, value, null), this.buckets)) this.length++;
  }

  /**
   * Removes a key-value pair from this hash table, if it exists.
   *
   * @param key
   * @return the value that was matched with the specified key in the hash table or {@code null} if
   *     the key was not found in the table.
   */
  public V remove(K key) {
    int index = this.hashFunction.applyAsInt(key.hashCode());
    HashTableNode<K, V> node = (HashTableNode<K, V>) this.buckets[index];

    if (node == null) return null;

    if (node.key.equals(key)) {
      this.buckets[index] = node.next;

      this.length--;
      return node.value;
    }

    while (node.next != null) {
      if (node.next.key.equals(key)) {
        node.next = node.next.next;

        this.length--;
        return node.next.value;
      }

      node = node.next;
    }

    return null;
  }

  /**
   * Retrieves the value in this table that corresponds to the specified key.
   *
   * @param key
   * @return the value in this table that matches with the key, or {@code null} if such a value does
   *     not exist in the table.
   */
  public V get(K key) {
    int index = this.hashFunction.applyAsInt(key.hashCode());
    HashTableNode<K, V> node = (HashTableNode<K, V>) this.buckets[index];

    while (node != null) {
      if (node.key.equals(key)) return node.value;

      node = node.next;
    }

    return null;
  }

  /**
   * Resizes this hash table to a new capacity, rehashing and copying all existing key-value pairs
   * to a new array.
   *
   * <p>Copying is done by creating new references to the existing nodes instead of creating new
   * nodes to maintain a O(1) space usage.
   *
   * @param newCapacity
   */
  private void resize(int newCapacity) {
    Object[] newBuckets = new Object[this.capacity = newCapacity];

    for (Object bucket : this.buckets) {
      HashTableNode<K, V> node = (HashTableNode<K, V>) bucket;

      while (node != null) {
        this.put(node, newBuckets);
        node = node.next;
      }
    }

    this.buckets = newBuckets;
  }

  /**
   * Resizes this hash table such that the new load factor is equal to or higher than (but remains
   * closest to) the specified load factor.
   *
   * @param minimumLoadFactor
   */
  public void resize(float minimumLoadFactor) {
    this.resize((int) Math.ceil(this.length / minimumLoadFactor));
  }

  /**
   * Doubles the capacity, or number of buckets.
   *
   * <p>Used internally only for the {@code put} method.
   *
   * @see #put(Object, Object)
   */
  private void expand() {
    this.resize(this.capacity * 2);
  }

  /**
   * Returns the clustering measure of this hash table, which tells us how (not) random the key
   * distribution is.
   *
   * <p>The meanings of the clustering is as follows:
   *
   * <ul>
   *   <li>around 1.0: the hash function is uniform;
   *   <li>greater than 1.0: clustering slows down the performance by a factor of the clustering
   *       measure C;
   *   <li>less than 1.0: the hash function is spreading elements out more evenly than a random hash
   *       function would;
   *   <li>0.0: the hash function is perfect and every key-value pair is in its own bucket.
   * </ul>
   *
   * @return the clustering of this hash table
   */
  public float measureClustering() {
    // First, calculate the sum of the squares of the number of elements in each
    // bucket.

    float squaresSum = 0;

    for (Object bucket : this.buckets) {
      int numElements = 0; // Number of elements in this bucket
      HashTableNode<K, V> node = (HashTableNode<K, V>) bucket;

      while (node != null) {
        numElements++;
        node = node.next;
      }

      squaresSum += Math.pow(numElements, 2);
    }

    int m = this.capacity;
    int n = this.length;

    // C = (m / (n − 1))((∑_i(x_i^2) / n) − 1) where x_i is the number of
    // elements in bucket i.

    return ((float) m / (n - 1)) * (squaresSum / n - 1);
  }
}
