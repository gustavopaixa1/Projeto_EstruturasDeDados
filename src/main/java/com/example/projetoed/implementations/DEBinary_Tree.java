package com.example.projetoed.implementations;

public interface DEBinary_Tree<T> {
    public boolean isEmpty();
    public int size();
    public T root();
    public bool search(T v);
    public void push(T v);
    public T pop();
    public void clear();
}
