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
 *     <li><code>enqueue(T)</code>: O(1)</li>
 *     <li><code>dequeue()</code>: O(1)</li>
 *     <li><code>peek()</code>: O(1)</li>
 *   </ul>
 */
public class ArrayQueue<T> implements Queue<T> {
  Object[] array;
  int length;
  int frontIndex;
  int rearIndex;

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

  public boolean isEmpty() {
    return this.length == 0;
  }

  @SuppressWarnings("unchecked")
  public T enqueue(T data) {
    if (this.length == this.array.length)
      return null;

    this.rearIndex = (this.rearIndex + 1) % this.array.length;
    this.array[this.rearIndex] = data;

    this.length++;
    return (T) this.array[this.rearIndex];
  }

  @SuppressWarnings("unchecked")
  public T dequeue() {
    if (this.length == 0)
      return null;

    T dequeuedData = (T) this.array[this.frontIndex];
    this.array[this.frontIndex] = null;
    this.frontIndex = (this.frontIndex + 1) % this.array.length;

    this.length--;
    return dequeuedData;
  }

  @SuppressWarnings("unchecked")
  public T peek() {
    return (T) this.array[this.frontIndex];
  }
}
