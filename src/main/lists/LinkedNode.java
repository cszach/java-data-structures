class LinkedNode<T> {
  public T data;
  protected LinkedNode<T> next;

  protected LinkedNode(T data, LinkedNode<T> next) {
    this.data = data;
    this.next = next;
  }
}
