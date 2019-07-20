package com.xiaotian.demo.algorithm.genetic;

import java.util.Random;

/**
 * 突变算法
 */
public class MutationAlgorithm {

    private final IntNodeFactory intNodeFactory;

    private static final Random random = new Random();

    public MutationAlgorithm(IntNodeFactory intNodeFactory) {
        this.intNodeFactory = intNodeFactory;
    }

    public Node mutation(Node tree, float mutationProb, int paramCount, int maxLevel, int level) {
        if (random.nextFloat() < mutationProb) {
            if (tree instanceof ExpressionNode) {
                return intNodeFactory.getTree(paramCount, maxLevel - level);
            } else if (tree instanceof ParamNode) {
                return intNodeFactory.getParamNode(paramCount);
            } else {
                return intNodeFactory.getConstantNode(paramCount);
            }
        } else if (tree instanceof ExpressionNode) {
            ExpressionNode expressionNode = (ExpressionNode) tree;
            for (int i = 0; i < expressionNode.getChildren().length; i++) {
                expressionNode.getChildren()[i] = mutation(expressionNode.getChildren()[i], mutationProb, paramCount, maxLevel, level + 1);
            }
        }
        return tree;
    }
}
