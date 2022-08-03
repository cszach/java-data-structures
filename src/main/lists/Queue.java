public interface Queue<T> {
  public T enqueue(T data);

  public T dequeue();

  public T peek();
}
