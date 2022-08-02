/**
 * An implementation of stack using static array.
 *
 * <p>
 * Operations with their time complexities are:
 *   <ul>
 *     <li><code>push</code>: O(1)</li>
 *     <li><code>pop</code>: O(1)</li>
 *     <li><code>peek</code>: O(1)</li>
 *   </ul>
 *
 */
public class ArrayStack<T> {
  private Object[] array;
  private int length;

  public ArrayStack(int capacity) {
    this.array = new Object[capacity];
    this.length = 0;
  }

  public int getLength() {
    return this.length;
  }

  public int getCapacity() {
    return this.array.length;
  }

  public ArrayStack<T> push(T data) {
    if (this.length == this.array.length)
      return this;

    this.array[this.length++] = data;
    return this;
  }

  public ArrayStack<T> pop() {
    if (this.length == 0)
      return this;

    this.array[--this.length] = null;
    return this;
  }

  @SuppressWarnings("unchecked")
  public T peek() {
    return (this.length == 0) ? null : (T) this.array[this.length - 1];
  }
}
