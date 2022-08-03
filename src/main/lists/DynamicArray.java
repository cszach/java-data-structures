/**
 * A dynamic array implementation based on a fixed-size array.
 *
 * <p>
 * Operations with their time complexities are:
 *   <ul>
 *     <li><code>shrinkCapacity()</code>: O(n)</li>
 *     <li><code>growCapacity()</code>: O(n)</li>
 *     <li><code>append(T)</code>: O(1)</li>
 *     <li><code>insert(T, int)</code>: O(n)</li>
 *     <li><code>remove()</code>: O(1)</li>
 *     <li><code>remove(int)</code>: O(n)</li>
 *     <li><code>get</code>: O(1)</li>
 *   </ul>
 */
public class DynamicArray<T> {
  Object[] array; // The underlying fixed-size array
  int length; // The number of elements in this array

  public DynamicArray() {
    this.array = new Object[10];
    this.length = 0;
  }

  public DynamicArray(int initialCapacity) {
    this.array = new Object[initialCapacity];
    this.length = 0;
  }

  public int getLength() {
    return this.length;
  }

  public int getCapacity() {
    return this.array.length;
  }

  public DynamicArray<T> shrinkCapacity() {
    Object[] newArray = new Object[this.length];

    for (int i = 0; i < this.length; i++)
      newArray[i] = this.array[i];

    this.array = newArray;
    return this;
  }

  public DynamicArray<T> growCapacity() {
    Object[] newArray = new Object[this.array.length * 2];

    for (int i = 0; i < this.length; i++)
      newArray[i] = this.array[i];

    this.array = newArray;
    return this;
  }

  public DynamicArray<T> append(T data) {
    if (this.length + 1 > this.array.length)
      this.growCapacity();

    this.array[this.length++] = data;
    return this;
  }

  public DynamicArray<T> insert(T data, int index) {
    if (this.length + 1 > this.array.length)
      this.growCapacity();

    for (int i = this.length++; i > index; i--)
      this.array[i] = this.array[i - 1];

    this.array[index] = data;
    return this;
  }

  public DynamicArray<T> remove() {
    this.array[--this.length] = null;
    return this;
  }

  public DynamicArray<T> remove(int index) {
    for (int i = index + 1; i < this.length; i++)
      this.array[i - 1] = this.array[i];

    return this.remove();
  }

  @SuppressWarnings("unchecked")
  public T get(int index) {
    return (T) this.array[index];
  }
}
