public class LinkedListAlgorithms {
  /**
   * Detects the cycle, if exists, in a given linked list, using Floyd's
   * turtoise and hare algorithm, or two-pointer technique.
   *
   * <p>
   * Runs in O(n) time and O(1) memory.
   *
   * @param list
   * @return The first node in the cycle.
   */
  public static <T> LinkedListNode<T> detectCycle(LinkedList<T> list) {
    LinkedListNode<T> rabbit = list.getHead();
    LinkedListNode<T> turtle = list.getHead();

    while (rabbit != null && rabbit.next != null) {
      rabbit = rabbit.next.next;
      turtle = turtle.next;

      if (rabbit == turtle) {
        LinkedListNode<T> bear = list.getHead();

        while (bear != rabbit) {
          bear = bear.next;
          rabbit = rabbit.next;
        }

        return bear;
      }
    }

    return null;
  }

  /**
   * Returns the intersection of two linked lists.
   *
   * <p>
   * Given lists A and B, first starts at the head of list B and travels to the
   * end (the last node after the intersection), which we call <code>tail</code>.
   * Then routes tail to A's head to form a cycle. Proceeds to use Floyd's
   * turtoise and hare algorithm starting from B's head.
   *
   * <p>
   * Runs in linear time and constant memory.
   * 
   * @param listA
   * @param listB
   * @return The intersection of two given lists, or <code>null</code> if there
   * is no intersection.
   */
  public static <T> LinkedListNode<T> getIntersection(LinkedList<T> listA, LinkedList<T> listB) {
    LinkedListNode<T> tail = listB.getHead(); // The last node after the intersection (if exists)

    while (tail.next != null)
      tail = tail.next;

    tail.next = listA.getHead();
    LinkedListNode<T> intersection = detectCycle(listB);

    tail.next = null;
    return intersection;
  }
}
