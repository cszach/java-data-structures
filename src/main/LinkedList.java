public class LinkedListNode {
  public byte data;
  protected LinkedListNode next;

  public LinkedListNode(byte data, LinkedListNode next) {
    this.data = data;
    this.next = next;
  }
}

public class LinkedList {
  private LinkedListNode head;
  private int length;

  public LinkedList() {
    this.head = null;
    this.length = 0;
  }

  public LinkedListNode getHead() {
    return this.head;
  }

  public int getLength() {
    return this.length;
  }

  /**
   * Insert new data at a specified index, shifting existing node at the index
   * and all nodes after it to the right.
   *
   * <p>
   * O(1) complexity for inserting at the head, O(i) complexity for other cases.
   * @param data
   * @param index A valid index for the new node that is between 0 and
   * <code>this.length</code> (inclusive).
   * @return
  */
  public LinkedListNode insert(byte data, int index) {
    if (index < 0 || index > this.length) return null;

    LinkedListNode newNode = new LinkedListNode(data, null);

    if (index == 0) {
      if (this.head != null) newNode.next = this.head;
      this.head = newNode;

      return newNode;
    }

    LinkedListNode prevNode = this.head;

    for (int i = 1; i < index; i++)
      prevNode = prevNode.next;

    if (index != this.length)
      newNode.next = prevNode.next;

    prevNode.next = newNode;
    this.length++;

    return newNode;
  }

  /**
   * Deletes the node at the specified index.
   *
   * <p>
   * O(n) complexity
   * @param index
   * @return
  */
  public LinkedListNode delete(int index) {
    if (index < 0 || index >= this.length || this.length == 0) return null;

    LinkedListNode prevNode = this.head;

    for (int i = 1; i < index; i++)
      prevNode = prevNode.next;

    LinkedListNode node = prevNode.next;
    prevNode.next = node.next;

    return node;
  }
}
