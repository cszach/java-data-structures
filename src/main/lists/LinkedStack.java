/**
 * An implementation of a stack based on a linked list.
 *
 * <p>
 * The head of the linked list is the top of the stack. All operations are O(1)
 * in time and space complexity.
 *
 * <p>
 * Operations with their time complexities are:
 *   <ul>
 *     <li><code>push(T)</code>: O(1)</li>
 *     <li><code>pop()</code>: O(1)</li>
 *     <li><code>peek()</code>: O(1)</li>
 *   </ul>
 */
public class LinkedStack<T> implements Stack<T> {
  private LinkedNode<T> top;
  private int length;

  public LinkedStack() {
    this.top = new LinkedNode<T>(null, new LinkedNode<T>(null, null));
    this.length = 0;
  }

  public int getLength() {
    return this.length;
  }

  public boolean isEmpty() {
    return this.length == 0;
  }

  public T push(T data) {
    this.top.next = new LinkedNode<T>(data, this.top.next);

    this.length++;
    return this.top.next.data;
  }

  public T pop() {
    if (this.length == 0)
      return null;

    T poppedData = this.top.next.data;
    this.top.next = this.top.next.next;

    this.length--;
    return poppedData;
  }

  public T peek() {
    return this.top.next.data;
  }
}
