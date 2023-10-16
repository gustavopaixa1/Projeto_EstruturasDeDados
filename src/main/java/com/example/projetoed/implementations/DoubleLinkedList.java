package com.example.projetoed.implementations;

public class DoubleLinkedList<T> implements DSList<T> {
    class Node {
        private T content;
        private Node next;
        private Node prev;

        public Node() {
            this.content = null;
            this.next = null;
            this.prev = null;
        }

        public T getContent() {
            return this.content;
        }

        public Node getNext() {
            return this.next;
        }

        public Node getPrev() {
            return this.prev;
        }

        public void setContent(T content) {
            this.content = content;
        }

        public void setNext(Node next) {
            this.next = next;
        }

        public void setPrev(Node prev) {
            this.prev = prev;
        }
    }

    private Node head;
    private Node tail;
    private int numberOfElements;

    public DoubleLinkedList() {
        this.head = null;
        this.tail = null;
        this.numberOfElements = 0;
    }

    public boolean isEmpty() {
        return this.size() == 0;
    }

    public int size() {
        return this.numberOfElements;
    }

    public T get(int index) {
        if (this.isEmpty() || index < 0 || index >= this.size())
            return null;

        if (index < this.size() / 2) {
            Node aux = this.head;

            for (int i = 0; i < index; i++)
                aux = aux.getNext();

            return aux.getContent();
        }

        Node aux = this.tail;

        for (int i = this.size() - 1; i > index; i--)
            aux = aux.getPrev();

        return aux.getContent();
    }

    public int indexOf(T v) {
        if (this.isEmpty())
            return -1;

        Node aux = this.head;

        for (int i = 0; i < this.size(); i++) {
            if (aux.getContent().equals(v))
                return i;
            aux = aux.getNext();
        }
        return -1;
    }

    public int indexOf(T v, int offset) {
        if (this.isEmpty() || offset < 0 || offset >= this.size())
            return -1;

        Node aux = this.head;

        for (int i = 0; i < offset; i++)
            aux = aux.getNext();

        for (int i = offset; i < this.size(); i++) {
            if (aux.getContent().equals(v))
                return i;
            aux = aux.getNext();
        }

        return -1;
    }

    public int indexOf(T v, int offset, int targetOccurrence) {
        if (this.isEmpty() || offset < 0 || offset >= this.size()
                || targetOccurrence > this.size())
            return -1;

        int numberOfOccurrences = 0;
        Node aux = this.head;

        for (int i = 0; i < offset; i++)
            aux = aux.getNext();

        for (int i = offset; i < this.size(); i++) {
            if (aux.getContent().equals(v)) {
                numberOfOccurrences++;
                if (numberOfOccurrences == targetOccurrence)
                    return i;
            }
            aux = aux.getNext();
        }

        return -1;
    }

    private boolean insertOnEmpty(T v) {
        Node newNode = new Node();
        newNode.setContent(v);
        this.head = newNode;
        this.tail = newNode;
        return true;
    }

    private boolean insertOnStart(T v) {
        Node newNode = new Node();
        newNode.setContent(v);
        newNode.setNext(this.head);
        newNode.getNext().setPrev(newNode);
        this.head = newNode;
        return true;
    }

    private boolean insertOnEnd(T v) {
        Node newNode = new Node();
        newNode.setContent(v);
        newNode.setPrev(this.tail);
        newNode.getPrev().setNext(newNode);
        this.tail = newNode;
        return true;
    }

    private boolean insertOnMiddle(T v, int index) {
        if (index < this.size() / 2) {
            Node predecessor = this.head;

            for (int i = 0; i < index - 1; i++)
                predecessor = predecessor.getNext();

            Node newNode = new Node();
            newNode.setContent(v);
            newNode.setPrev(predecessor);
            newNode.setNext(predecessor.getNext());
            newNode.getPrev().setNext(newNode);
            newNode.getNext().setPrev(newNode);
        } else {
            Node successor = this.tail;

            for (int i = this.size() - 1; i > index; i--)
                successor = successor.getPrev();

            Node newNode = new Node();
            newNode.setContent(v);
            newNode.setNext(successor);
            newNode.setPrev(successor.getPrev());
            newNode.getPrev().setNext(newNode);
            newNode.getNext().setPrev(newNode);
        }
        return true;
    }

    public boolean insert(T v, int index) {
        if (index < 0 || index > this.size())
            return false;

        if (this.isEmpty()) {
            this.insertOnEmpty(v);
        } else if (index == 0) {
            this.insertOnStart(v);
        } else if (index == this.size()) {
            this.insertOnEnd(v);
        } else {
            this.insertOnMiddle(v, index);
        }

        this.numberOfElements++;
        return true;
    }

    private T removeOnMiddle(int index) {
        T removedElement;
        if (index < this.size() / 2) {
            Node predecessor = this.head;

            for (int i = 0; i < index - 1; i++)
                predecessor = predecessor.getNext();

            removedElement = predecessor.getNext().getContent();

            predecessor.setNext(predecessor.getNext().getNext());
            predecessor.getNext().setPrev(predecessor);
        } else {
            Node successor = this.tail;

            for (int i = this.size() - 1; i > index + 1; i--)
                successor = successor.getPrev();

            removedElement = successor.getPrev().getContent();

            successor.setPrev(successor.getPrev().getPrev());
            successor.getPrev().setNext(successor);
        }
        return removedElement;
    }

    public T remove(int index) {
        if (this.isEmpty() || index < 0 || index >= this.size())
            return null;

        T removedElement;
        if (index == 0) {
            removedElement = this.head.getContent();
            this.head = this.head.getNext();
            if (this.size() == 1)
                this.tail = null;
            else
                this.head.setPrev(null);
        } else if (index == this.size() - 1) {
            removedElement = this.tail.getContent();
            this.tail = this.tail.getPrev();
            this.tail.setNext(null);
        } else {
            removedElement = this.removeOnMiddle(index);
        }

        this.numberOfElements--;
        return removedElement;
    }

    public void clear() {
        if (!this.isEmpty())
            while (this.size() > 0)
                this.remove(0);
    }

    public String toString() {
        if (this.isEmpty())
            return "[]";

        StringBuilder sb = new StringBuilder();
        sb.append('[');

        Node aux = this.head;
        while (aux.getNext() != null) {
            sb.append(aux.getContent().toString());
            sb.append(", ");
            aux = aux.getNext();
        }
        sb.append(aux.getContent().toString());
        return sb.append(']').toString();
    }
}
