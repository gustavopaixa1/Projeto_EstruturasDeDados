package com.example.projetoed.implementations;

public class Linked_Stack<T> implements DSStack<T> {

    class Node {
        private T content;
        private Node bottom;

        public Node() {
            this.content = null;
            this.bottom = null;
        }

        public T getContent() {
            return this.content;
        }

        public Node getBottom() {
            return this.bottom;
        }

        public void setContent(T content) {
            this.content = content;
        }

        public void setBottom(Node bottom) {
            this.bottom = bottom;
        }
    }

    private int numberOfElements;
    private Node top;

    public Linked_Stack() {
        this.numberOfElements = 0;
        this.top = null;
    }

    public boolean isEmpty() {
        return this.numberOfElements == 0;
    }

    public int size() {
        return this.numberOfElements;
    }

    public T top() {
        return this.top.getContent();
    }

    public T push(T v) {
        Node newNode = new Node();

        newNode.setContent(v);
        newNode.setBottom(this.top);

        this.top = newNode;

        this.numberOfElements++;

        return this.top.getContent();
    }

    public T pop() {
        if (this.numberOfElements == 0) {
            return null;
        }

        Node aux;
        aux = this.top;

        this.top = aux.getBottom();
        aux.setBottom(null);

        this.numberOfElements--;

        return aux.getContent();
    }

    public void clear() {
        while (this.numberOfElements > 0) {
            pop();
        }
    }
}