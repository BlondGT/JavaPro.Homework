package bank.hm34.variation2;

import java.util.TreeMap;

public class TreeMapTree<T> {

    private final TreeMap<Integer, T> tree = new TreeMap<>();

    public void add(int key, T value) {
        tree.put(key, value);
    }

    public void inOrder() {
        for(T value: tree.values()) {
            System.out.println(value);
        }
    }
}
