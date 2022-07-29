class LinkedListNode<T> {
  public T data;
  protected LinkedListNode<T> next;

  protected LinkedListNode(T data, LinkedListNode<T> next) {
    this.data = data;
    this.next = next;
  }
}
