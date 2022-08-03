import java.lang.reflect.Array;

/**
 * Simple linked list implementation.
 *
 * <p>
 * Operations with their complexities are:
 *   <ul>
 *     <li><code>get(int)</code>: O(n)</li>
 *     <li><code>insert(T, int)</code>: O(n)</li>
 *     <li><code>insertAtHead(T)</code>: O(1)</li>
 *     <li><code>delete(int)</code>: O(n)</li>
 *     <li><code>deleteAtHead()</code>: O(1)</li>
 *   </ul>
 */
public class LinkedList<T> {
  LinkedNode<T> head;
  int length;

  @SuppressWarnings("unchecked")
  public static <T> T[] toArray(LinkedNode<T> head, int length, Class<T> c) {
    T[] array = (T[]) Array.newInstance(c, length);
    LinkedNode<T> node = head;

    for (int i = 0; i < length; i++) {
      node = node.next;
      array[i] = node.data;
    }

    return array;
  }

  public LinkedList() {
    this.head = new LinkedNode<T>(null, null);
    this.length = 0;
  }

  public LinkedNode<T> getHead() {
    return this.head.next;
  }

  public int getLength() {
    return this.length;
  }

  public boolean isEmpty() {
    return this.length == 0;
  }

  public T get(int index) {
    if (index < 0 || index >= this.length)
      throw new IndexOutOfBoundsException();

    LinkedNode<T> node = this.head.next;
    for (int i = 0; i < index; i++)
      node = node.next;

    return node.data;
  }

  public LinkedNode<T> insert(T data, int index) {
    if (index < 0 || index > this.length)
      throw new IndexOutOfBoundsException();

    LinkedNode<T> newNode = new LinkedNode<T>(data, null);
    LinkedNode<T> prevNode = this.head;

    for (int i = 0; i < index; i++)
      prevNode = prevNode.next;

    if (index != this.length) newNode.next = prevNode.next;
    prevNode.next = newNode;

    this.length++;
    return newNode;
  }

  public LinkedNode<T> insertAtHead(T data) {
    LinkedNode<T> newNode = new LinkedNode<T>(data, this.head.next);
    this.head.next = newNode;

    this.length++;
    return newNode;
  }

  public LinkedNode<T> delete(int index) {
    if (index < 0 || index >= this.length)
      throw new IndexOutOfBoundsException();

    LinkedNode<T> prevNode = this.head;

    for (int i = 0; i < index; i++)
      prevNode = prevNode.next;

    LinkedNode<T> deletedNode = prevNode.next;
    if (index != this.length) prevNode.next = deletedNode.next;

    this.length--;
    return deletedNode;
  }

  public LinkedNode<T> deleteAtHead() {
    if (this.head.next != null) {
      LinkedNode<T> deletedNode = this.head.next;
      this.head.next = this.head.next.next;

      this.length--;
      return deletedNode;
    } else {
      return null;
    }
  }
}
