package com.example.projetoed.implementations;

public interface DSQueue<T> {
    public boolean isEmpty();

    public int size();

    public T front();

    public T end();

    public void push(T v);

    public T pop();

    public void clear();
}
