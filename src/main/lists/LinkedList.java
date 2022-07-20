import java.lang.reflect.Array;

class LinkedListNode<T> {
  public T data;
  protected LinkedListNode<T> next;

  public LinkedListNode(T data, LinkedListNode<T> next) {
    this.data = data;
    this.next = next;
  }
}

/**
 * Simple linked list implementation.
 *
 * <p>
 * Operations with their complexities are:
 *   <ul>
 *     <li>Get data at an index: O(n)</li>
 *     <li>Insert data at head: O(1)</li>
 *     <li>Insert data at an index: O(n)</li>
 *     <li>Delete data at an index: O(n)</li>
 *   </ul>
 */
public class LinkedList<T> {
  /**
   * The head of the linked list which is a <code>LinkedListNode</code> that
   * points to the first node of the linked list.
   *
   * <p>
   * Having the head being an empty node rather than the first node in the
   * actual list helps clean the code and reduces if statements.
   */
  private LinkedListNode<T> head;
  /**
   * The number of nodes in this linked list, excluding the head.
   */
  private int length;

  public LinkedList() {
    this.head = new LinkedListNode<T>(null, null);
    this.length = 0;
  }

  public LinkedList(T[] data) {
    this.head = new LinkedListNode<T>(null, null);
    this.length = 0;

    for (T d: data)
      this.insert(d, this.length);
  }

  public LinkedListNode<T> getHead() {
    return this.head;
  }

  public int getLength() {
    return this.length;
  }

  /**
   * Checks if this list is empty.
   * 
   * @return <code>true</code> if this list is empty, <code>false</code> if not.
   */
  public boolean isEmpty() {
    return this.length == 0;
  }

  /**
   * Returns the data contained by the node at the specified index.
   *
   * @param index
   * @return The data stored at the index.
   */
  public T get(int index) {
    if (index < 0 || index >= this.length) throw new IndexOutOfBoundsException();

    LinkedListNode<T> node = this.head.next;

    for (int i = 0; i < index; i++)
      node = node.next;

    return node.data;
  }

  /**
   * Inserts new data at the head.
   *
   * <p>
   * O(1) complexity.
   *
   * @param data
   * @return The new node inserted that contains the data.
   */
  public LinkedListNode<T> insertAtHead(T data) {
    LinkedListNode<T> newNode = new LinkedListNode<T>(data, this.head.next);
    this.head.next = newNode;

    this.length++;
    return newNode;
  }

  /**
   * Inserts new data at a specified index, shifting existing node at the index
   * and all nodes after it to the right.
   *
   * <p>
   * O(n) complexity.
   *
   * @param data
   * @param index A valid index for the new node that is between 0 and
   * <code>this.length</code> (inclusive).
   * @return The new node inserted that contains the data.
   */
  public LinkedListNode<T> insert(T data, int index) {
    if (index < 0 || index > this.length) throw new IndexOutOfBoundsException();

    LinkedListNode<T> newNode = new LinkedListNode<T>(data, null);
    LinkedListNode<T> prevNode = this.head;

    for (int i = 0; i < index; i++)
      prevNode = prevNode.next;

    if (index != this.length) newNode.next = prevNode.next;
    prevNode.next = newNode;

    this.length++;
    return newNode;
  }

  /**
   * Deletes the node at the specified index.
   *
   * <p>
   * O(n) complexity.
   *
   * @param index
   * @return The deleted node.
   */
  public LinkedListNode<T> delete(int index) {
    if (index < 0 || index >= this.length) throw new IndexOutOfBoundsException();

    LinkedListNode<T> prevNode = this.head;

    for (int i = 0; i < index; i++)
      prevNode = prevNode.next;

    LinkedListNode<T> node = prevNode.next;
    if (index != this.length) prevNode.next = node.next;

    this.length--;
    return node;
  }

  /**
   * Exports this list to an array.
   *
   * @return
   */
  @SuppressWarnings("unchecked")
  public T[] toArray(Class<T> c) {
    T[] array = (T[]) Array.newInstance(c, this.length);
    LinkedListNode<T> node = this.head;

    for (int i = 0; i < this.length; i++) {
      node = node.next;
      array[i] = node.data;
    }

    return array;
  }
}
