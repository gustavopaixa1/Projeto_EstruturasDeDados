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

    Node root;
    int numberOfElements;

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

    private Node getNodeByValue(T v, Node aux) {
        if (aux == null) {
            return null;
        }

        if (aux.getContent().compareTo(v) == 0) {
            return aux;
        }

        if (aux.getContent().compareTo(v) < 0) {
            return getNodeByValue(v, aux.getLeft());
        } else {
            return getNodeByValue(v, aux.getRight());
        }
    }

    private T removeRoot() {
        Node aux;
        aux = this.root;

        if (this.root.getLeft() == null && this.root.getRight() == null) {
            this.root = null;

            return aux.getContent();
        }

        if (this.root.getLeft() != null) {
            this.root = aux.getLeft();
            aux.setLeft(null);

            return aux.getContent();
        }

        if (this.root.getRight() != null) {
            this.root = aux.getRight();
            aux.setLeft(null);

            return aux.getContent();
        }

        Node sucessor = findSucessor(aux);
        int valorAux;

        valorAux
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
    public T remove(T v) {
        Node aux = getNodeByValue(v, this.root);

        if (aux == null) {
            return null;
        }

        if (aux.getContent().compareTo(this.root.getContent()) == 0) {
            return removeRoot();
        }
    }
    public void clear();
}
