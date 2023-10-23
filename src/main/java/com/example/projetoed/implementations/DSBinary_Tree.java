package com.example.projetoed.implementations;

import java.util.ArrayList;

public interface DSBinary_Tree {
    public boolean isEmpty();
    public int size();
    public SBTNode<Integer> root();
    public ArrayList<String> preOrder_Traversal();
    public ArrayList<String> postOrder_Traversal();
    public ArrayList<String> inOrder_Traversal();
    public boolean search(int v);
    public void insert(int v);
    public int remove(int v);
    public void clear();
}
