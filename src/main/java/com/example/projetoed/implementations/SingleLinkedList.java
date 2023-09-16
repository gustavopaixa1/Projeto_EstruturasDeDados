package com.example.projetoed.implementations;

class Node<T> {
    private T content;
    private Node<T> next;

    public Node() {
        this.content = null;
        this.next = null;
    }

    public T getContent() {
        return this.content;
    }

    public Node<T> getNext() {
        return this.next;
    }

    public void setContent(T content) {
        this.content = content;
    }

    public void setNext(Node<T> next) {
        this.next = next;
    }
}

public class SingleLinkedList<T> implements DEList<T> {
    private Node<T> head;
    private int numberOfElements;

    public SingleLinkedList() {
        this.head = null;
        this.numberOfElements = 0;
    }

    public boolean isEmpty() {
        if (this.size() == 0)
            return true;
        return false;
    }

    public int size() {
        return this.numberOfElements;
    }

    public T get(int index) {
        if (this.isEmpty() || index < 0 || index >= this.size())
            return null;

        Node<T> aux = this.head;

        for (int i = 0; i < index; i++)
            aux = aux.getNext();

        return aux.getContent();
    }

    public int indexOf(T v) {
        if (this.isEmpty())
            return -1;

        Node<T> aux = this.head;

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

        Node<T> aux = this.head;

        for (int i = 0; i < offset; i++)
            aux = aux.getNext();

        for (int i = offset; i < this.size(); i++) {
            if (aux.getContent().equals(v))
                return i;
            aux = aux.getNext();
        }

        return -1;
    }

    public int indexOf(T v, int offset, int targethOccurrence) {
        if (this.isEmpty() || offset < 0 || offset >= this.size()
                || targethOccurrence > this.size())
            return -1;

        int numberOfOccurrences = 0;
        Node<T> aux = this.head;

        for (int i = 0; i < offset; i++)
            aux = aux.getNext();

        for (int i = offset; i < this.size(); i++) {
            if (aux.getContent().equals(v)) {
                numberOfOccurrences++;
                if (numberOfOccurrences == targethOccurrence)
                    return i;
            }
            aux = aux.getNext();
        }

        return -1;
    }

    public boolean insert(T v, int index) {
        if (index < 0 || index > this.size())
            return false;

        if (index == 0) {
            Node<T> newNode = new Node<T>();
            newNode.setContent(v);
            newNode.setNext(this.head);
            this.head = newNode;
        } else {
            Node<T> predecessor = this.head;

            for (int i = 0; i < index - 1; i++)
                predecessor = predecessor.getNext();

            Node<T> newNode = new Node<T>();
            newNode.setContent(v);
            newNode.setNext(predecessor.getNext());
            predecessor.setNext(newNode);
        }

        this.numberOfElements++;
        return true;
    }

    public T remove(int index) {
        if (this.isEmpty() || index < 0 || index >= this.size())
            return null;

        T removedElement;
        if (index == 0) {
            removedElement = this.head.getContent();
            this.head = this.head.getNext();
        } else {
            Node<T> predecessor = this.head;

            for (int i = 0; i < index - 1; i++)
                predecessor = predecessor.getNext();

            removedElement = predecessor.getNext().getContent();
            predecessor.setNext(predecessor.getNext().getNext());
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

        Node<T> aux = this.head;
        while (aux.getNext() != null) {
            sb.append(aux.getContent().toString());
            sb.append(", ");
            aux = aux.getNext();
        }
        sb.append(aux.getContent().toString());
        return sb.append(']').toString();
    }
}
