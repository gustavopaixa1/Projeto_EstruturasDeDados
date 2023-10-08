package com.example.projetoed.implementations;

public class Search_Binary_Tree<T extends Comparable<T>> implements DEBinary_Tree<T>{
    class Node {
        private T content;
        private Node left;
        private Node right;

        public Node() {
            this.content = null;
            this.left = null;
            this.right = null;
        }

        public T getContent() {
            return this.content;
        }

        public Node getLeft() {
            return this.left;
        }

        public Node getRight() {
            return this.right;
        }

        public void setContent(T content) {
            this.content = content;
        }

        public void setLeft(Node left) {
            this.left = left;
        }

        public void setRight(Node right) {
            this.right = right;
        }
    }

    private Node root;
    private int numberOfElements;

    public  Search_Binary_Tree() {
        this.numberOfElements = 0;
        this.root = null;
    }

    public boolean isEmpty() {
        return this.numberOfElements == 0;
    }
    public int size() {
        return this.numberOfElements;
    }

    public T root() {
        return this.root.getContent();
    }

    public void preOrder_Traversal() {
        preOrder_Traversal(this.root);
        System.out.println();
    }

    public void postOrder_Traversal() {
        postOrder_Traversal(this.root);
        System.out.println();
    }

    public void inOrder_Traversal() {
        inOrder_Traversal(this.root);
        System.out.println();
    }

    public T remove(T v) {
        if (!isEmpty()) this.numberOfElements--;
        return remove(this.root, v).getContent();
    }

    public boolean search(T v) {
        Node aux = this.root;

        while (aux != null) {
            if (v.compareTo(aux.getContent()) == 0) {
                return true;
            }

            if (v.compareTo(aux.getContent()) < 0) {
                aux = aux.getLeft();
            } else {
                aux = aux.getRight();
            }
        }

        return false;
    }

    public void insert(T v) {
        if (search(v)) {
            return;
        }

        Node newNode = new Node();
        newNode.content = v;

        if (isEmpty()) {
            this.root = newNode;
            this.numberOfElements++;
            return;
        }

        Node aux = this.root;

        privateInsert(newNode, aux);
        this.numberOfElements++;
    }

    private void privateInsert(Node node, Node aux) {
        if (node.getContent().compareTo(aux.getContent()) < 0 && aux.getLeft() == null) {
            aux.setLeft(node);
            return;
        }

        if (node.getContent().compareTo(aux.getContent()) > 0 && aux.getRight() == null) {
            aux.setRight(aux);
            return;
        }

        if (node.getContent().compareTo(aux.getContent()) < 0) {
            privateInsert(node, aux.getLeft());
        } else {
            privateInsert(node, aux.getRight());
        }
    }

    private Node findMin(Node node) {
        if (node.getLeft() == null) {
            return node;
        }

        return findMin(node.getLeft());
    }

    private Node remove(Node node, T v) {
        if (node == null) return null;
        else if (v.compareTo(node.getContent()) < 0) node.setLeft(remove(node.getLeft(), v));
        else if (v.compareTo(node.getContent()) > 0) node.setRight(remove(node.getRight(), v));
        else if (node.getRight() == null && node.getLeft() == null) {
            node = null;
            return node;
        }
        else if (node.getLeft() == null) {
            Node aux = node.getRight();
            node = null;

            return aux;
        }
        else {
            Node aux = findMin(node.getRight());
            node.setContent(aux.getContent());
            remove(node.getRight(), node.getContent());
        }
        return node;
    }

    private void preOrder_Traversal(Node node) {
        if (node == null) {
            return;
        }

        System.out.print(node.getContent() + " ");

        preOrder_Traversal(node.getLeft());
        preOrder_Traversal(node.getRight());
    }

    private void inOrder_Traversal(Node node) {
        if (node == null) {
            return;
        }

        inOrder_Traversal(node.getLeft());
        System.out.print(node.getContent() + " ");
        inOrder_Traversal(node.getRight());
    }

    private void postOrder_Traversal(Node node) {
        if (node == null) {
            return;
        }

        postOrder_Traversal(node.getLeft());
        postOrder_Traversal(node.getRight());

        System.out.print(node.getContent() + " ");
    }

    public void clear() {
        while (!isEmpty()) remove(this.root, this.root.getContent());
    }
}
