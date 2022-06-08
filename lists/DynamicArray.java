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

  /** Shrinks the capacity of the underlying fixed-size array to free unused memory. */
  public void shrinkSize() {
    T[] newArray = (T[]) new Object[this.size];
    for (int i = 0; i < this.size; i++) newArray[i] = this.array[i];
    this.array = newArray;
  }

  /** Doubles the capacity of the underlying fixed-size array. */
  public void growSize() {
    T[] newArray = (T[]) new Object[this.array.length * 2];
    for (int i = 0; i < this.size; i++) newArray[i] = this.array[i];
    this.array = newArray;
  }

  /**
   * Adds a new element to the end of this dynamic array.
   *
   * @param e the new element to be added to the end
   */
  public void add(T e) {
    if (this.size + 1 > this.array.length) this.growSize();
    this.array[this.size++] = e;
  }

  /**
   * Adds a new element at the specified index in this dynamic array.
   *
   * @param e the new element to be added at the specified index
   * @param index the index to add the new element at
   */
  public void add(T e, int index) {
    if (this.size + 1 > this.array.length) this.growSize();
    for (int i = this.size++; i > index; i--) this.array[i] = this.array[i - 1];
    this.array[index] = e;
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

  /**
   * Prints elements in the specified dynamic array line-by-line.
   *
   * @param array the dynamic array to be printed
   */
  public static void printDynamicArray(DynamicArray array) {
    for (int i = 0; i < array.getSize(); i++)
      System.out.println(array.getAt(i));
  }
}
