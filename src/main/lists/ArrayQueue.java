/**
 * An implementation of queue using a static array.
 *
 * <p>
 * This implementation uses the cyclic buffer by storing the indices of the rear
 * and the front of the queue to achieve O(1) complexity on all operations.
 *
 * <p>
 * Operations and their time complexities are:
 *   <ul>
 *     <li><code>enqueue</code>: O(1)</li>
 *     <li><code>dequeue</code>: O(1)</li>
 *     <li><code>peek</code>: O(1)</li>
 *   </ul>
 */
public class ArrayQueue<T> {
  private Object[] array;
  private int length;
  private int frontIndex;
  private int rearIndex;

  public ArrayQueue(int capacity) {
    this.array = new Object[capacity];
    this.length = 0;
    this.frontIndex = 0;
    this.rearIndex = 0;
  }

  public int getLength() {
    return this.length;
  }

  public int getCapacity() {
    return this.array.length;
  }

  public ArrayQueue<T> enqueue(T data) {
    if (this.length == this.array.length)
      return this;

    this.rearIndex = (this.rearIndex + 1) % this.array.length;
    this.array[this.rearIndex] = data;

    this.length++;
    return this;
  }

  public ArrayQueue<T> dequeue() {
    if (this.length == 0)
      return this;

    this.array[this.frontIndex] = null;
    this.frontIndex = (this.frontIndex + 1) % this.array.length;

    this.length--;
    return this;
  }

  @SuppressWarnings("unchecked")
  public T peek() {
    return (T) this.array[this.frontIndex];
  }
}
