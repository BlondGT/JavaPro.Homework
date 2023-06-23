package bank.hm34.variation1;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Node<T> {

    private T data;
    private int key;
    private Node<T> leftChild;
    private Node<T> rightChild;

    public Node(T data, int key) {
        this.data = data;
        this.key = key;
    }
}
