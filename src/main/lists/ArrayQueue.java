/**
 * An implementation of queue using a static array.
 *
 * <p>
 * Operations and their time complexities are:
 *   <ul>
 *     <li><code>enqueue</code>: O(1)</li>
 *     <li><code>dequeue</code>: O(n)</li>
 *     <li><code>peek</code>: O(1)</li>
 *   </ul>
 */
public class ArrayQueue<T> {
  private Object[] array;
  private int length;

  public ArrayQueue(int maxLength) {
    this.array = new Object[maxLength];
    this.length = 0;
  }

  public ArrayQueue<T> enqueue(T data) {
    if (this.length == this.array.length)
      return this;

    this.array[this.length++] = data;
    return this;
  }

  public ArrayQueue<T> dequeue() {
    if (this.length == 0)
      return this;

    int i;
    for (i = 1; i < this.length; i++)
      this.array[i - 1] = this.array[i];

    this.array[i - 1] = null;
    this.length--;
    return this;
  }

  @SuppressWarnings("unchecked")
  public T peek() {
    return (T) this.array[0];
  }

  public static void main(String[] args) {
    ArrayQueue<Integer> queue = new ArrayQueue<Integer>(10);

    for (int i = 0; i < 15; i++)
      queue.enqueue(i);

    for (int i = 0; i < 20; i++) {
      System.out.println(queue.peek());
      queue.dequeue();
    }
  }
}
