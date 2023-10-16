package com.example.projetoed.implementations;

public class Linked_Queue<T> implements DSQueue<T> {

    class Node {
        private T content;
        private Node next;

        public Node() {
            this.content = null;
            this.next = null;
        }

        public T getContent() {
            return this.content;
        }

        public Node getNext() {
            return this.next;
        }

        public void setContent(T content) {
            this.content = content;
        }

        public void setNext(Node next) {
            this.next = next;
        }
    }
    private int numberOfElements;
    private Node head;
    private Node tail;

    public Linked_Queue() {
        this.numberOfElements = 0;
        this.head = null;
        this.tail = null;
    }

    public boolean isEmpty() {
        return this.numberOfElements == 0;
    }

    public int size() {
        return this.numberOfElements;
    }

    public T front() {
        if (isEmpty()) {
            return null;
        }

        return this.head.getContent();
    }

    public T end() {
        if (isEmpty()) {
            return null;
        }

        return this.tail.getContent();
    }

    private void pushEmptyQueue(T v) {
        Node newNode = new Node();
        newNode.setContent(v);

        this.head = newNode;
        this.tail = newNode;
        this.numberOfElements++;
    }

    private T popUnitQueue() {
        Node aux = this.head;

        this.head = null;
        this.tail = null;
        this.numberOfElements--;

        return aux.getContent();
    }

    public void push(T v) {
        if (isEmpty()) {
            pushEmptyQueue(v);
            return;
        }

        Node newNode = new Node();
        newNode.setContent(v);

        this.tail.setNext(newNode);
        this.tail = newNode;
        this.numberOfElements++;
    }

    public T pop() {
        if (isEmpty()) {
            return null;
        }
        if (this.numberOfElements == 1) {
            return popUnitQueue();
        }

        Node aux = this.head;
        this.head = aux.getNext();
        aux.setNext(null);
        this.numberOfElements--;

        return aux.getContent();
    }

    public void clear() {
        while (numberOfElements > 0) {
            pop();
        }
    }
}
