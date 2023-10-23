package com.example.projetoed.implementations;

public class SeqList<T> implements DSList<T> {
    private T[] data;
    private int numberOfElements;
    private int maxElements;

    public SeqList() {
        this.data = (T[]) new Object[5];
        this.numberOfElements = 0;
        this.maxElements = 5;
    }

    public SeqList(int size) {
        if (size > 0) {
            this.data = (T[]) new Object[size];
            this.numberOfElements = 0;
            this.maxElements = size;
        } else {
            System.out.printf("Tamanho inválido.\nCriando lista com tamanho 5.");
            this.data = (T[]) new Object[5];
            this.numberOfElements = 0;
            this.maxElements = 5;
        }
    }

    public boolean isEmpty() {
        return this.size() == 0;
    }

    public boolean isFull() {
        return this.size() == this.maxElements;
    }

    public int size() {
        return this.numberOfElements;
    }

    public T get(int index) {
        if (0 <= index && index < this.numberOfElements)
            return this.data[index];
        return null;
    }

    public int indexOf(T v) {
        if (this.isEmpty())
            return -1;

        for (int i = 0; i < this.numberOfElements; i++)
            if (this.data[i].equals(v))
                return i;
        return -1;
    }

    public int indexOf(T v, int offset) {
        if (this.isEmpty() || offset < 0 || offset >= this.numberOfElements)
            return -1;

        for (int i = offset; i < this.numberOfElements; i++)
            if (this.data[i].equals(v))
                return i;
        return -1;
    }

    public int indexOf(T v, int offset, int targetOccurrence) {
        if (this.isEmpty() || offset < 0 || offset >= this.numberOfElements
                || targetOccurrence > this.numberOfElements)
            return -1;

        int numberOfOccurrences = 0;
        for (int i = offset; i < this.numberOfElements; i++)
            if (this.data[i].equals(v)) {
                numberOfOccurrences++;
                if (numberOfOccurrences == targetOccurrence)
                    return i;
            }

        return -1;
    }

    public boolean insert(T v, int index) {
        if (index < 0 || index > this.numberOfElements || this.isFull())
            return false;

        for (int i = this.numberOfElements - 1; i >= index; i--)
            this.data[i + 1] = this.data[i];

        this.data[index] = v;
        this.numberOfElements++;
        return true;
    }

    public T remove(int index) {
        if (index < 0 || index >= this.numberOfElements)
            return null;

        T removedElement = this.data[index];

        for (int i = index; i < this.numberOfElements - 1; i++)
            this.data[i] = this.data[i + 1];

        this.numberOfElements--;
        return removedElement;
    }

    public void clear() {
        if (!this.isEmpty())
            for (int i = this.numberOfElements - 1; i >= 0; i--)
                this.remove(i);
    }

    public String toString() {
        if (this.isEmpty())
            return "[]";

        StringBuilder sb = new StringBuilder();
        sb.append('[');

        for (int i = 0; i < this.numberOfElements - 1; i++) {
            sb.append(this.data[i].toString());
            sb.append(", ");
        }
        sb.append(this.data[this.numberOfElements - 1].toString());
        return sb.append(']').toString();
    }
}
