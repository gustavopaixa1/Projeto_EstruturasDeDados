package com.example.projetoed.implementations;

public class SBTNode<T> {
        private T content;
        private SBTNode<T> left;
        private SBTNode<T> right;

        public SBTNode() {
            this.content = null;
            this.left = null;
            this.right = null;
        }

        public T getContent() {
            return this.content;
        }

        public SBTNode<T> getLeft() {
            return this.left;
        }

        public SBTNode<T> getRight() {
            return this.right;
        }

        public void setContent(T content) {
            this.content = content;
        }

        public void setLeft(SBTNode<T> left) {
            this.left = left;
        }

        public void setRight(SBTNode<T> right) {
            this.right = right;
        }
}
