/**
 * A class that represents a node.
 *
 * <p>A node contains data of any type and a pointer to the next element. To be used in a linked
 * list implementation.
 *
 * @author Duong Nguyen
 */
class Node<T> {

  T data;
  Node next;

  /**
   * Constructs a new node in a list.
   *
   * @param data the data that this node contains
   * @param next the address of the next node in the list
   */
  Node(T data, Node next) {
    this.data = data;
    this.next = next;
  }
}

/**
 * An implementation of a singly linked list.
 *
 * <p>Implementation includes methods to insert and delete at the head, the tail, and a specified
 * index.
 *
 * @author Duong Nguyen
 */
@SuppressWarnings("unchecked")
class LinkedList<T> {

  Node<T> head;
  Node<T> tail;

  /** Constructs a new linked list with a head node. */
  LinkedList() {
    this.head = new Node<T>(null, null);
    this.tail = head;
  }

  /**
   * Returns the length of this linked list.
   *
   * @return the length of this linked list
   */
  int length() {
    Node node = this.head;
    int length = 0;

    while (node.next != null) {
      node = node.next;
      length++;
    }

    return length;
  }

  boolean isValidIndex(int index) {
    return index > -1 && index < this.length();
  }

  /**
   * Returns the node at the specified index.
   *
   * @param index
   * @return the node at the specified index
   */
  Node get(int index) {
    if (!this.isValidIndex(index)) return null;

    Node node = this.head.next;

    for (int i = 0; i < index; i++)
      if (node.next != null) node = node.next;
      else return null;

    return node;
  }

  /**
   * Inserts a node with the specfied data at the beginning of this list.
   *
   * @param data the data that the node contains
   * @return the inserted node
   */
  Node insertAtStart(T data) {
    Node node = new Node(data, this.head.next);
    this.head.next = node;

    return node;
  }

  /**
   * Inserts a node with the specified data at the end of this list.
   *
   * @param data the data that the node contains
   * @return the inserted node
   */
  Node insertAtEnd(T data) {
    Node node = new Node(data, null);
    this.tail.next = node; // old tail
    this.tail = node; // new tail

    return node;
  }

  /**
   * Inserts a node with the specified data at the specified index.
   *
   * @param data the data that the node contains
   * @param index the index at which to insert the node
   * @return the inserted node
   */
  Node insert(T data, int index) {
    if (!this.isValidIndex(index)) return null;

    Node node = new Node(data, this.get(index));
    this.get(index - 1).next = node;

    return node;
  }

  /**
   * Deletes the first node.
   *
   * @return the deleted node
   */
  Node deleteAtStart() {
    if (this.head == this.tail) return null;

    Node node = this.head.next;
    this.head.next = node.next;

    return node;
  }

  /**
   * Deletes the last node.
   *
   * @return the deleted node
   */
  Node deleteAtEnd() {
    if (this.tail == this.head) return null;

    Node node = this.tail;
    Node newTail = this.get(this.length() - 2);
    newTail.next = null;
    this.tail = newTail;

    return node;
  }

  /**
   * Deletes the node at the specified index.
   *
   * @param index the index of the node to be deleted
   * @return the deleted node
   */
  Node delete(int index) {
    if (!this.isValidIndex(index)) return null;
    if (index == 0) return this.deleteAtStart();

    Node prevNode = this.get(index - 1);
    Node node = prevNode.next;
    prevNode.next = node.next;

    return node;
  }
}
