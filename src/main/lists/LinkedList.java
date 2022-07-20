class LinkedListNode {
  public byte data;
  protected LinkedListNode next;

  public LinkedListNode(byte data, LinkedListNode next) {
    this.data = data;
    this.next = next;
  }
}

/**
 * Simple linked list implementation.
 *
 * <p>
 * Operations with their complexities are:
 *   <ul>
 *     <li>Get data at an index: O(n)</li>
 *     <li>Insert data at head: O(1)</li>
 *     <li>Insert data at an index: O(n)</li>
 *     <li>Delete data at an index: O(n)</li>
 *   </ul>
 */
public class LinkedList {
  /**
   * The head of the linked list which is a <code>LinkedListNode</code> that
   * points to the first node of the linked list.
   *
   * <p>
   * Having the head being an empty node rather than the first node in the
   * actual list helps clean the code and reduces if statements.
   */
  private LinkedListNode head;
  /**
   * The number of nodes in this linked list, excluding the head.
   */
  private int length;

  public LinkedList() {
    this.head = new LinkedListNode((byte) 0, null);
    this.length = 0;
  }

  public LinkedList(byte[] data) {
    this.head = new LinkedListNode((byte) 0, null);
    this.length = 0;

    for (byte d: data)
      this.insert(d, this.length);
  }

  public LinkedListNode getHead() {
    return this.head;
  }

  public int getLength() {
    return this.length;
  }

  /**
   * Checks if this list is empty.
   * 
   * @return <code>true</code> if this list is empty, <code>false</code> if not.
   */
  public boolean isEmpty() {
    return this.length == 0;
  }

  /**
   * Returns the data contained by the node at the specified index.
   *
   * @param index
   * @return The data stored at the index.
   */
  public byte get(int index) {
    if (index < 0 || index >= this.length) throw new IndexOutOfBoundsException();

    LinkedListNode node = this.head.next;

    for (int i = 0; i < index; i++)
      node = node.next;

    return node.data;
  }

  /**
   * Inserts new data at the head.
   *
   * <p>
   * O(1) complexity.
   *
   * @param data
   * @return The new node inserted that contains the data.
   */
  public LinkedListNode insertAtHead(byte data) {
    LinkedListNode newNode = new LinkedListNode(data, this.head.next);
    this.head.next = newNode;

    this.length++;
    return newNode;
  }

  /**
   * Inserts new data at a specified index, shifting existing node at the index
   * and all nodes after it to the right.
   *
   * <p>
   * O(n) complexity.
   *
   * @param data
   * @param index A valid index for the new node that is between 0 and
   * <code>this.length</code> (inclusive).
   * @return The new node inserted that contains the data.
   */
  public LinkedListNode insert(byte data, int index) {
    if (index < 0 || index > this.length) throw new IndexOutOfBoundsException();

    LinkedListNode newNode = new LinkedListNode(data, null);
    LinkedListNode prevNode = this.head;

    for (int i = 0; i < index; i++)
      prevNode = prevNode.next;

    if (index != this.length) newNode.next = prevNode.next;
    prevNode.next = newNode;

    this.length++;
    return newNode;
  }

  /**
   * Deletes the node at the specified index.
   *
   * <p>
   * O(n) complexity.
   *
   * @param index
   * @return The deleted node.
   */
  public LinkedListNode delete(int index) {
    if (index < 0 || index >= this.length) throw new IndexOutOfBoundsException();

    LinkedListNode prevNode = this.head;

    for (int i = 0; i < index; i++)
      prevNode = prevNode.next;

    LinkedListNode node = prevNode.next;
    if (index != this.length) prevNode.next = node.next;

    this.length--;
    return node;
  }

  /**
   * Exports this list to an array.
   *
   * @return
   */
  public byte[] toArray() {
    byte[] array = new byte[this.length];
    LinkedListNode node = this.head;

    for (int i = 0; i < this.length; i++) {
      node = node.next;
      array[i] = node.data;
    }

    return array;
  }

  public static void main(String[] args) {
    LinkedList list = new LinkedList();

    for (byte i = 0; i < 10; i++)
      list.insert(i, list.getLength());

    list.delete(list.getLength());

    byte[] array = list.toArray();

    for (byte b: array) {
      System.out.println(b);
    }
  }
}
