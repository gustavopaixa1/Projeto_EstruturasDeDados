package com.example.projetoed.implementations;

public interface DEBinary_Tree<T> {
    public boolean isEmpty();
    public int size();
    public T root();
    public boolean search(T v);
    public void insert(T v);
    public T remove(T v);
    public void clear();
}
