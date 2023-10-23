package com.example.projetoed.implementations;

import java.util.ArrayList;

public class Search_Binary_Tree implements DSBinary_Tree {
    private SBTNode<Integer> root;
    private ArrayList<String> history;
    private int numberOfElements;

    public Search_Binary_Tree() {
        this.numberOfElements = 0;
        this.history = new ArrayList<String>();
        this.root = null;
    }

    public boolean isEmpty() {
        return this.numberOfElements == 0;
    }

    public int size() {
        return this.numberOfElements;
    }

    public SBTNode<Integer> root() {
        return this.root;
    }

    public ArrayList<String> getHistory() {
        return this.history;
    }

    public boolean search(int v) {
        this.history.clear();
        SBTNode<Integer> aux = this.root;

        while (aux != null) {
            this.history.add(Integer.toString(aux.getContent()));
            if (v == aux.getContent()) {
                this.history.remove(this.history.size() - 1);
                return true;
            }

            if (v < aux.getContent()) {
                aux = aux.getLeft();
            } else {
                aux = aux.getRight();
            }
        }

        return false;
    }

    public void insert(int v) {
        if (search(v)) {
            return;
        }
        this.history.clear();

        SBTNode<Integer> newNode = new SBTNode<Integer>();
        newNode.setContent(v);

        if (isEmpty()) {
            this.root = newNode;
            this.numberOfElements++;
            return;
        }

        SBTNode<Integer> aux = this.root;

        privateInsert(newNode, aux);
        this.numberOfElements++;
    }

    private void privateInsert(SBTNode<Integer> node, SBTNode<Integer> aux) {
        this.history.add(Integer.toString(aux.getContent()));

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

    public int remove(int v) {
        this.history.clear();
        if (this.isEmpty())
            return 0;
        if (this.size() == 1) {
            int aux = this.root.getContent();
            this.root = null;
            this.numberOfElements--;
            return aux;
        }

        this.numberOfElements--;
        return remove(this.root, v).getContent();
    }

    private SBTNode<Integer> remove(SBTNode<Integer> node, int v) {
        if (node != null && node.getContent() != v)
            this.history.add(Integer.toString(node.getContent()));
        if (node == null) return null;
        else if (v < node.getContent()) node.setLeft(remove(node.getLeft(), v));
        else if (v > node.getContent()) node.setRight(remove(node.getRight(), v));
        else if (node.getRight() == null && node.getLeft() == null) {
            node = null;
            return node;
        }
        else if (node.getLeft() == null) {
            SBTNode<Integer> aux = node.getRight();
            if (this.root == node)
                this.root = aux;
            node = null;

            return aux;
        }
        else if (node.getRight() != null) {
            SBTNode<Integer> aux = findMin(node.getRight());
            remove(node, aux.getContent());
            node.setContent(aux.getContent());
        } else {
            SBTNode<Integer> aux = node.getLeft();
            if (this.root == node)
                this.root = aux;
            node = null;

            return aux;
        }
        return node;
    }

    private SBTNode<Integer> findMin(SBTNode<Integer> node) {
        if (node.getLeft() == null) {
            return node;
        }

        return findMin(node.getLeft());
    }

    public ArrayList<String> preOrder_Traversal() {
        this.history.clear();
        return preOrder_Traversal(this.root);
    }

    private ArrayList<String> preOrder_Traversal(SBTNode<Integer> node) {
        if (node == null) {
            return this.history;
        }

        this.history.add(Integer.toString(node.getContent()));
        preOrder_Traversal(node.getLeft());
        preOrder_Traversal(node.getRight());

        return this.history;
    }

   public ArrayList<String> inOrder_Traversal() {
        this.history.clear();
        return inOrder_Traversal(this.root);
    }
    private ArrayList<String> inOrder_Traversal(SBTNode<Integer> node) {
        if (node == null) {
            return this.history;
        }

        inOrder_Traversal(node.getLeft());
        this.history.add(Integer.toString(node.getContent()));
        inOrder_Traversal(node.getRight());

        return this.history;
    }

    public ArrayList<String> postOrder_Traversal() {
        this.history.clear();
        return postOrder_Traversal(this.root);
    }

    private ArrayList<String> postOrder_Traversal(SBTNode<Integer> node) {
        if (node == null) {
            return this.history;
        }

        postOrder_Traversal(node.getLeft());
        postOrder_Traversal(node.getRight());
        this.history.add(Integer.toString(node.getContent()));

        return this.history;
    }

    public void clear() {
        this.history.clear();
        this.root = null;
        this.numberOfElements = 0;
    }
}
