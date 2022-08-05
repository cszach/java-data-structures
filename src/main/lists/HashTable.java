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
  ToIntFunction<Integer> hashFunction = k -> k % this.buckets.length; // h(k)
  int length;

  public HashTable() {
    this.buckets = new Object[1 << 4]; // Start with 16 buckets by default
    this.length = 0;
  }

  public HashTable(int initialCapacity) {
    this.buckets = new Object[initialCapacity];
    this.length = 0;
  }

  public HashTable(int initialCapacity, float loadFactor) {
    if (loadFactor <= 0)
      throw new IllegalArgumentException("Illegal load factor: " + loadFactor);

    this.buckets = new Object[initialCapacity];
    this.loadFactor = loadFactor;
    this.length = 0;
  }

  public int getLength() {
    return this.length;
  }

  public int getCapacity() {
    return this.buckets.length;
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
    if ((this.length + 1) / this.buckets.length > this.loadFactor)
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
    Object[] newBuckets = new Object[newCapacity];

    for (Object bucket: this.buckets) {
      HashTableNode<K, V> node = (HashTableNode<K, V>) bucket;

      if (node == null)
        continue;
      else
        while (node.next != null) {
          this.put(node, newBuckets);
          node = node.next;
        }
    }

    this.buckets = newBuckets;
  }

  private void expand() {
    this.rehash(this.buckets.length * 2);
  }

  public void shrink(float minimumLoadFactor) {
    this.rehash((int) Math.ceil(this.length / minimumLoadFactor));
  }
}
