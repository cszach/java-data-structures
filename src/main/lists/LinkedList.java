import java.lang.reflect.Array;

/**
 * Simple linked list implementation.
 *
 * <p>
 * Operations with their complexities are:
 *   <ul>
 *     <li><code>get</code>: O(n)</li>
 *     <li><code>insert</code>: O(n)</li>
 *     <li><code>insertAtHead</code>: O(1)</li>
 *     <li><code>delete</code>: O(n)</li>
 *     <li><code>deleteAtHead</code>: O(1)</li>
 *   </ul>
 */
public class LinkedList<T> {
  private LinkedListNode<T> head;
  private int length;

  @SuppressWarnings("unchecked")
  public static <T> T[] toArray(LinkedListNode<T> head, int length, Class<T> c) {
    T[] array = (T[]) Array.newInstance(c, length);
    LinkedListNode<T> node = head;

    for (int i = 0; i < length; i++) {
      node = node.next;
      array[i] = node.data;
    }

    return array;
  }

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
    return this.head.next;
  }

  public int getLength() {
    return this.length;
  }

  public boolean isEmpty() {
    return this.length == 0;
  }

  public T get(int index) {
    if (index < 0 || index >= this.length) throw new IndexOutOfBoundsException();

    LinkedListNode<T> node = this.head.next;

    for (int i = 0; i < index; i++)
      node = node.next;

    return node.data;
  }

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

  public LinkedListNode<T> insertAtHead(T data) {
    LinkedListNode<T> newNode = new LinkedListNode<T>(data, this.head.next);
    this.head.next = newNode;

    this.length++;
    return newNode;
  }

  public LinkedListNode<T> delete(int index) {
    if (index < 0 || index >= this.length) throw new IndexOutOfBoundsException();

    LinkedListNode<T> prevNode = this.head;

    for (int i = 0; i < index; i++)
      prevNode = prevNode.next;

    LinkedListNode<T> deletedNode = prevNode.next;
    if (index != this.length) prevNode.next = deletedNode.next;

    this.length--;
    return deletedNode;
  }

  public LinkedListNode<T> deleteAtHead() {
    if (this.head.next != null) {
      LinkedListNode<T> deletedNode = this.head.next;
      this.head.next = this.head.next.next;

      this.length--;
      return deletedNode;
    } else {
      return null;
    }
  }
}
