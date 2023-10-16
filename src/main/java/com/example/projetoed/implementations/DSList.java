package com.example.projetoed.implementations;

public interface DSList<T> {
    public boolean isEmpty();

    public int size();

    public T get(int index);

    public int indexOf(T v);

    public int indexOf(T v, int offset);

    public int indexOf(T v, int offset, int targetOccurrence);

    public boolean insert(T v, int index);

    public T remove(int index);

    public void clear();

    public String toString();
}
