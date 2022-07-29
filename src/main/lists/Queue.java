/**
 * An implementation of the queue data structure based on linked list.
 *
 * <p>
 * This implementation is basically a linked list with a tail, where the head
 * of the list is the front of the queue and the tail is the rear. Both the head
 * and the tail are empty nodes themselves. The rear (the last enqueued element)
 * points to the tail and the tail points back to the rear, forming a cycle.
 * This helps achieve both enqueueing and dequeueing in constant time.
 *
 * <p>
 * Operations with their complexities are:
 *   <ul>
 *     <li><code>enqueue</code>: O(1)</li>
 *     <li><code>dequeue</code>: O(1)</li>
 *     <li><code>peek</code>: O(1)</li>
 *   </ul>
 */
public class Queue<T> {
  private LinkedListNode<T> head;
  private LinkedListNode<T> tail;
  private int length;

  public Queue() {
    this.head = new LinkedListNode<T>(null, null);
    this.tail = new LinkedListNode<T>(null, this.head);
    this.head.next = this.tail;
    this.length = 0;
  }

  public Queue(T[] data) {
    this.head = new LinkedListNode<T>(null, null);
    this.tail = new LinkedListNode<T>(null, this.head);
    this.head.next = this.tail;
    this.length = 0;

    for (T d: data)
      this.enqueue(d);
  }

  public int getLength() {
    return this.length;
  }

  public boolean isEmpty() {
    return this.length == 0;
  }

  public Queue<T> enqueue(T data) {
    this.tail.next.next = new LinkedListNode<T>(data, this.tail);
    this.tail.next = this.tail.next.next;

    this.length++;
    return this;
  }

  public Queue<T> dequeue() {
    if (this.length == 0) return this;

    this.head.next = this.head.next.next;

    this.length--;
    if (this.length == 0) this.tail.next = this.head;
    return this;
  }

  public T peek() {
    return this.head.next.data;
  }
}
