import java.util.function.ToIntFunction;

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

@SuppressWarnings("unchecked")
public class HashTable<K, V> {
  Object[] buckets;
  float loadFactor = 0.75f; // The maximum load factor
  ToIntFunction<Integer> hashFunction = k -> k % this.capacity; // h(k)
  int length;
  int capacity;

  public HashTable() {
    this.buckets = new Object[this.capacity = 16];
    this.length = 0;
  }

  public HashTable(int initialCapacity) {
    this.buckets = new Object[this.capacity = initialCapacity];
    this.length = 0;
  }

  public HashTable(int initialCapacity, float loadFactor) {
    if (loadFactor <= 0)
      throw new IllegalArgumentException("Illegal load factor: " + loadFactor);

    this.buckets = new Object[this.capacity = initialCapacity];
    this.loadFactor = loadFactor;
    this.length = 0;
  }

  public int getLength() {
    return this.length;
  }

  public int getCapacity() {
    return this.capacity;
  }

  public ToIntFunction<Integer> getHashFunction() {
    return this.hashFunction;
  }

  public void setHashFunction(ToIntFunction<Integer> hashFunction) {
    this.hashFunction = hashFunction;
  }

  private boolean put(HashTableNode<K, V> newNode, Object[] buckets) {
    newNode.next = null;
    int index = this.hashFunction.applyAsInt(newNode.key.hashCode());

    if (buckets[index] == null) {
      buckets[index] = newNode;
      return true;
    }
    else {
      HashTableNode<K, V> node = (HashTableNode<K, V>) buckets[index];

      while (true) {
        if (node.key.equals(newNode.key)) {
          node.value = newNode.value;
          return false;
        }

        if (node.next != null)
          node = node.next;
        else
          break;
      }

      node.next = newNode;
      return true;
    }
  }

  public void put(K key, V value) {
    if ((float) (this.length + 1) / this.capacity > this.loadFactor)
      this.expand();

    if (this.put(new HashTableNode<K, V>(key, value, null), this.buckets))
      this.length++;
  }

  public boolean remove(K key) {
    int index = this.hashFunction.applyAsInt(key.hashCode());
    HashTableNode<K, V> node = (HashTableNode<K, V>) this.buckets[index];

    if (node == null)
      return false;

    if (node.key.equals(key)) {
      this.buckets[index] = node.next;
      return true;
    }

    while (node.next != null) {
      if (node.next.key.equals(key)) {
        node.next = node.next.next;
        return true;
      }

      node = node.next;
    }

    // TODO: this.length--;
    return false;
  }

  public V get(K key) {
    int index = this.hashFunction.applyAsInt(key.hashCode());
    HashTableNode<K, V> node = (HashTableNode<K, V>) this.buckets[index];

    while (node != null) {
      if (node.key.equals(key))
        return node.value;

      node = node.next;
    }

    return null;
  }

  private void rehash(int newCapacity) {
    Object[] newBuckets = new Object[this.capacity = newCapacity];

    for (Object bucket: this.buckets) {
      HashTableNode<K, V> node = (HashTableNode<K, V>) bucket;

      if (node == null)
        continue;
      else
        do {
          this.put(node, newBuckets);
          node = node.next;
        } while (node != null);
    }

    this.buckets = newBuckets;
  }

  private void expand() {
    this.rehash(this.capacity * 2);
  }

  public void shrink(float minimumLoadFactor) {
    this.rehash((int) Math.ceil(this.length / minimumLoadFactor));
  }

  public float measureClustering() {
    int squaresSum = 0;

    for (Object bucket: this.buckets) {
      int numElements = 0; // Number of elements in this bucket
      HashTableNode<K, V> node = (HashTableNode<K, V>) bucket;

      while (node != null) {
        numElements++;
        node = node.next;
      }

      squaresSum += Math.pow(numElements, 2);
    }

    int m = this.capacity;
    float n = this.length;

    return (m / (n - 1)) * (squaresSum / n - 1);
  }
}
