/**
 * An implementation of the queue data structure based on linked list.
 *
 * <p>
 * This implementation is basically a linked list with a tail, where the head
 * of the list is the front of the queue and the tail is the rear. Both the
 * front and the rear are empty nodes themselves. The last enqueued element
 * points to the rear and the rear points back to the element, forming a little
 * cycle. This helps achieve both enqueueing and dequeueing in constant time.
 *
 * <p>
 * Operations with their complexities are:
 *   <ul>
 *     <li><code>enqueue(T)</code>: O(1)</li>
 *     <li><code>dequeue()</code>: O(1)</li>
 *     <li><code>peek()</code>: O(1)</li>
 *   </ul>
 */
public class LinkedQueue<T> implements Queue<T> {
  private LinkedNode<T> front; // head of linked list
  private LinkedNode<T> rear; // tail of linked list
  private int length;

  public LinkedQueue() {
    this.front = new LinkedNode<T>(null, null);
    this.rear = new LinkedNode<T>(null, this.front);
    this.front.next = this.rear;
    this.length = 0;
  }

  public int getLength() {
    return this.length;
  }

  public boolean isEmpty() {
    return this.length == 0;
  }

  public T enqueue(T data) {
    this.rear.next.next = this.rear.next = new LinkedNode<T>(data, this.rear);

    this.length++;
    return this.rear.next.data;
  }

  public T dequeue() {
    if (this.length == 0)
      return null;

    T dequeuedData = this.front.next.data;
    this.front.next = this.front.next.next;

    if (--this.length == 0) this.rear.next = this.front;
    return dequeuedData;
  }

  public T peek() {
    return this.front.next.data;
  }
}
