package com.xiaotian.demo.algorithm.genetic;

public class TreeAndScore {
    private Node tree;
    private int score;

    public TreeAndScore(Node tree, int score) {
        this.tree = tree;
        this.score = score;
    }

    public Node getTree() {
        return tree;
    }

    public void setTree(Node tree) {
        this.tree = tree;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
