package bank.hm34.variation1;

public interface Tree<T>{

    void add(T insertData, int key);

    void inOrder(Node<T> current);
}
