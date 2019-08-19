package com.xiaotian.demo.algorithm.genetic;

import java.util.Arrays;
import java.util.Random;

public class Genetic {

    private static final Random random = new Random();


    public static void main(String[] args) {

        IntNodeFactory intNodeFactory = new IntNodeFactory();
        MutationAlgorithm mutationAlgorithm = new MutationAlgorithm(intNodeFactory);

        Node[] trees = intNodeFactory.getTrees(1000, 2, 5, 0, 0.5F, 0.6F);
        TreeAndScore[] treeAndScores = Arrays.stream(trees).map(t -> new TreeAndScore(t, Integer.MIN_VALUE)).toArray((TreeAndScore[]::new));
        int[][] params = new int[200][2];
        for (int i = 0; i < params.length; i++) {
            for (int j = 0; j < params[i].length; j++) {
                params[i][j] = generateNextNumber();
            }
        }
        Node result = null;
        boolean hasFinished = false;
        while (true) {
            for (TreeAndScore treeAndScore : treeAndScores) {
                if (calculate(params, treeAndScore) == 0) {
                    result = treeAndScore.getTree();
                    hasFinished = true;
                    break;
                }
            }
            if (hasFinished) {
                break;
            }
            Arrays.sort(treeAndScores, (a, b) -> a.getScore() - b.getScore());
            for (int i = 0; i < treeAndScores.length; i++) {
                treeAndScores[i].setTree(mutationAlgorithm.mutation(TreeCopyUtil.copyFrom(treeAndScores[0].getTree()), 0.01F, 2, 4, 0));
            }
            System.out.println(treeAndScores[0].getScore());
        }

        System.out.println(result);
    }

    private static long calculate(int[][] params, TreeAndScore treeAndScores) {
        long diff = 0;
        for (int[] param : params) {
            int distance = Math.abs(treeAndScores.getTree().execute(param) - realValue(param[0], param[1]));
            diff = diff + distance;
            treeAndScores.setScore(distance);
        }
        return diff;
    }


    private static int generateNextNumber() {
        return random.nextInt(1000);
    }

    private static int realValue(int x, int y) {
        return (x ^ 2) + (y ^ 2) + (8 * y) + (13 * x) + 15;
    }
}
