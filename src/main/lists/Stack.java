/**
 * An implementation of a stack based on a linked list.
 *
 * <p>
 * The head of the linked list is the top of the stack. All operations are O(1)
 * in time and space complexity. The length (or size) of the stack is not stored
 * and thus cannot be retrieved in constant time.
 *
 * <p>
 * This implementation also does not contain any if statement thanks to two
 * techniques: not storing the length (as described above) and having a tail
 * whose <code>next</code> pointer points to itself.
 *
 * <p>
 * Operations with their time complexities are:
 *   <ul>
 *     <li><code>push</code>: O(1)</li>
 *     <li><code>pop</code>: O(1)</li>
 *     <li><code>peek</code>: O(1)</li>
 *   </ul>
 */
public class Stack<T> {
  private LinkedListNode<T> head;

  public Stack() {
    LinkedListNode<T> tail = new LinkedListNode<T>(null, null);
    tail.next = tail;

    this.head = new LinkedListNode<T>(null, tail);
  }

  public Stack(T[] data) {
    LinkedListNode<T> tail = new LinkedListNode<T>(null, null);
    tail.next = tail;

    this.head = new LinkedListNode<T>(null, tail);

    for (T d: data)
      this.push(d);
  }

  public boolean isEmpty() {
    return this.head.next == this.head.next.next;
  }

  public Stack<T> push(T data) {
    this.head.next = new LinkedListNode<T>(data, this.head.next);
    return this;
  }

  public Stack<T> pop() {
    this.head.next = this.head.next.next;
    return this;
  }

  public T peek() {
    return this.head.next.data;
  }
}
