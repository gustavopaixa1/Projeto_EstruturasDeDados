package com.example.projetoed.implementations;

public class Search_Binary_Tree<T extends Comparable<T>> implements DSBinary_Tree<T> {
    private SBTNode<T> root;
    private int numberOfElements;

    public Search_Binary_Tree() {
        this.numberOfElements = 0;
        this.root = null;
    }

    public boolean isEmpty() {
        return this.numberOfElements == 0;
    }
    public int size() {
        return this.numberOfElements;
    }

    public SBTNode<T> root() {
        return this.root;
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
        SBTNode<T> aux = this.root;

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

        SBTNode<T> newNode = new SBTNode<T>();
        newNode.setContent(v);

        if (isEmpty()) {
            this.root = newNode;
            this.numberOfElements++;
            return;
        }

        SBTNode<T> aux = this.root;

        privateInsert(newNode, aux);
        this.numberOfElements++;
    }

    private void privateInsert(SBTNode<T> node, SBTNode<T> aux) {
        if (node.getContent().compareTo(aux.getContent()) < 0 && aux.getLeft() == null) {
            aux.setLeft(node);
            return;
        }

        if (node.getContent().compareTo(aux.getContent()) > 0 && aux.getRight() == null) {
            aux.setRight(node);
            return;
        }

        if (node.getContent().compareTo(aux.getContent()) < 0) {
            privateInsert(node, aux.getLeft());
        } else {
            privateInsert(node, aux.getRight());
        }
    }

    private SBTNode<T> findMin(SBTNode<T> node) {
        if (node.getLeft() == null) {
            return node;
        }

        return findMin(node.getLeft());
    }

    private SBTNode<T> remove(SBTNode<T> node, T v) {
        if (node == null) return null;
        else if (v.compareTo(node.getContent()) < 0) node.setLeft(remove(node.getLeft(), v));
        else if (v.compareTo(node.getContent()) > 0) node.setRight(remove(node.getRight(), v));
        else if (node.getRight() == null && node.getLeft() == null) {
            node = null;
            return node;
        }
        else if (node.getLeft() == null) {
            SBTNode<T> aux = node.getRight();
            node = null;

            return aux;
        }
        else {
            SBTNode<T> aux = findMin(node.getRight());
            node.setContent(aux.getContent());
            remove(node.getRight(), node.getContent());
        }
        return node;
    }

    private void preOrder_Traversal(SBTNode<T> node) {
        if (node == null) {
            return;
        }

        System.out.print(node.getContent() + " ");

        preOrder_Traversal(node.getLeft());
        preOrder_Traversal(node.getRight());
    }

    private void inOrder_Traversal(SBTNode<T> node) {
        if (node == null) {
            return;
        }

        inOrder_Traversal(node.getLeft());
        System.out.print(node.getContent() + " ");
        inOrder_Traversal(node.getRight());
    }

    private void postOrder_Traversal(SBTNode<T> node) {
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
