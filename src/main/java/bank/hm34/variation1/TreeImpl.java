package bank.hm34.variation1;

public class TreeImpl<T> implements Tree<T>{

    private Node<T> root;

    @Override
    public void add(T insertData, int key) {

        Node<T> current = root;
        Node<T> parent;
        Node<T> newNode = new Node<>(insertData, key);
        if (root == null)
            root = newNode;
        else {
            while (true) {
                parent = current;
                if (key < current.getKey()) {
                    current = current.getLeftChild();
                    if (current == null) {
                        parent.setLeftChild(newNode);
                        return;
                    }
                }
                else {
                    current = current.getRightChild();
                    if (current == null) {
                        parent.setRightChild(newNode);
                        return;
                    }
                }
            }
        }
    }

    @Override
    public void inOrder(Node<T> current) {

        if (current != null) {
            inOrder(current.getLeftChild());
            inOrder(current.getRightChild());
        }
    }
}
