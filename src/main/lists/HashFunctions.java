import java.util.function.ToIntFunction;

public class HashFunctions {
  public static class ModularHash implements ToIntFunction<Integer> {
    int m;

    public ModularHash(int m) {
      this.m = m;
    }

    public int applyAsInt(Integer k) {
      return k % this.m;
    }
  }

  public static class CommonModularHash implements ToIntFunction<Integer> {
    HashTable<?, ?> hashTable;

    public CommonModularHash(HashTable<?, ?> hashTable) {
      this.hashTable = hashTable;
    }

    public int applyAsInt(Integer k) {
      return k % this.hashTable.getCapacity();
    }
  }
}
