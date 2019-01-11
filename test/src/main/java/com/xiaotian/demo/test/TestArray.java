package com.xiaotian.demo.test;

public class TestArray {

    public static void main(String[] args) {
        int[][] two = new int[5][10];

        for (int i = 0; i < two.length; i++) {
            for (int j = 0; j < two[0].length; j++) {
                two[i][j] = i * j;
            }
        }

        for (int i = 0; i < two.length; i++) {
            String split = "";
            for (int j = 0; j < two[0].length; j++) {
                System.out.print(split);
                System.out.print(two[i][j]);
                split = "\t";
            }
            System.out.println();
        }

        int three[][][] = new int[2][4][8];
        for (int i = 0; i < three.length; i++) {
            for (int j = 0; j < three[0].length; j++) {
                for (int k = 0; k < three[0][0].length; k++) {
                    three[i][j][k] = i * j * k;
                }
            }
        }

        for (int i = 0; i < three.length; i++) {
            System.out.println();
            System.out.println();
            System.out.println();
            System.out.println();
            for (int j = 0; j < three[0].length; j++) {
                System.out.println(String.format("i: %s,j: %s", i, j));
                String split = "";
                for (int k = 0; k < three[0][0].length; k++) {
                    System.out.print(split);
                    System.out.print(three[i][j][k]);
                    split = "\t";
                }
                System.out.println();
            }
        }

        int four[][][][] = new int[2][4][8][16];
        for (int i = 0; i < four.length; i++) {
            for (int j = 0; j < four[0].length; j++) {
                for (int k = 0; k < four[0][0].length; k++) {
                    for (int l = 0; l < four[0][0][0].length; l++) {
                        four[i][j][k][l] = i * j * k * l;
                    }
                }
            }
        }

        for (int i = 0; i < four.length; i++) {
            for (int j = 0; j < four[0].length; j++) {
                for (int k = 0; k < four[0][0].length; k++) {
                    System.out.println(String.format("i: %s,j: %s, k: %s", i, j, k));
                    String split = "";
                    for (int l = 0; l < four[0][0][0].length; l++) {
                        System.out.print(split);
                        System.out.print(four[i][j][k][l]);
                        split = "\t";
                    }
                    System.out.println();
                }
            }
        }
    }

}
