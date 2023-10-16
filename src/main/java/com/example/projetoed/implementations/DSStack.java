package com.example.projetoed.implementations;

public interface DSStack<T> {
    public boolean isEmpty();

    public int size();

    public T top();

    public T push(T v);

    public T pop();

    public void clear();
}
