/**
 * An implementation of stack using static array.
 *
 * <p>Operations with their time complexities are:
 *
 * <ul>
 *   <li><code>push(T)</code>: O(1)
 *   <li><code>pop()</code>: O(1)
 *   <li><code>peek()</code>: O(1)
 * </ul>
 */
public class ArrayStack<T> implements Stack<T> {
  Object[] array;
  int length;

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

  @SuppressWarnings("unchecked")
  public T push(T data) {
    if (this.length == this.array.length) return null;

    this.array[this.length] = data;
    return (T) this.array[this.length++];
  }

  @SuppressWarnings("unchecked")
  public T pop() {
    if (this.length == 0) return null;

    T poppedData = (T) this.array[--this.length];
    this.array[this.length] = null;
    return poppedData;
  }

  @SuppressWarnings("unchecked")
  public T peek() {
    return (T) this.array[(this.length - 1) % (this.length + 1)];
  }
}
