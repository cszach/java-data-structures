@SuppressWarnings("unchecked")
public class DynamicArray<T> {
  private T[] array; // The underlying fixed-size array
  private int size = 0; // The number of elements inside this dynamic array

  /** Initializes a new dynamic array with an initial capacity of 10. */
  public DynamicArray() {
    this.array = (T[]) new Object[10];
  }

  /**
   * Initializes a new dynamic array with the given initial capacity.
   *
   * @param initialCapacity the initial capacity of the new dynamic array
   */
  public DynamicArray(int initialCapacity) {
    this.array = (T[]) new Object[initialCapacity];
  }

  /**
   * Returns the number of elements in this dynamic array.
   *
   * @return the number of elements in this dynamic array.
   */
  public int getSize() {
    return this.size;
  }

  /**
   * Adds a new element to the end of this dynamic array.
   *
   * @param e the new element to be added to the end
   */
  public void add(T e) {
    // Create a new fixed-length array with doubled capacity when space is run
    // out
    if (this.size + 1 > this.array.length) {
      T[] newArray = (T[]) new Object[this.array.length * 2]; // double in size

      for (int i = 0; i < this.size; i++) newArray[i] = this.array[i];

      this.array = newArray;
    }

    this.array[this.size++] = e;
  }

  /** Removes the last element in this dynamic array. */
  public void remove() {
    this.array[--this.size] = null;
  }

  /**
   * Removes the element at the specified index in this dynamic array.
   *
   * @param index the index of the element to be removed
   */
  public void removeAt(int index) {
    // Shift to the right starting at the specified index
    for (int i = index; i < this.size - 1; i++) this.array[i] = this.array[i + 1];

    this.remove();
  }

  /**
   * Returns the element at the specified index in this dynamic array.
   *
   * @param index the index of the element to be returned
   */
  public T getAt(int index) {
    return (T) this.array[index];
  }
}
