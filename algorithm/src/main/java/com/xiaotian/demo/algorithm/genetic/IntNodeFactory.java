package com.xiaotian.demo.algorithm.genetic;

import java.util.Random;

public class IntNodeFactory {

    private final Random random = new Random(System.currentTimeMillis());

    private IntFunctionWrapper[] functionWrappers;

    {

        IntFunctionWrapper addFunction = new IntFunctionWrapper(params -> params[0] + params[1], "add", 2);
        IntFunctionWrapper subFunction = new IntFunctionWrapper(params -> params[0] - params[1], "sub", 2);
        IntFunctionWrapper multiFunction = new IntFunctionWrapper(params -> params[0] * params[1], "multi", 2);
        IntFunctionWrapper isGreaterFunction = new IntFunctionWrapper(params -> params[0] > params[1] ? 1 : 0, "isGreater", 2);

        IntFunctionWrapper ifFunction = new IntFunctionWrapper(params -> {
            if (params[0] > 0) {
                return params[1];
            } else {
                return params[2];
            }
        }, "if", 3);

        functionWrappers = new IntFunctionWrapper[]{ifFunction, addFunction, subFunction, multiFunction, isGreaterFunction};
    }


    public Node[] getTrees(int number, int paramCount, int maxDepth, int depth, float expressionNodeProb, float paramNodeCountProb) {
        if (number <= 0) {
            return new Node[]{};
        } else {
            Node[] nodes = new Node[number];
            for (int i = 0; i < number; i++) {
                nodes[i] = getTree(paramCount, maxDepth, depth, expressionNodeProb, paramNodeCountProb);
            }
            return nodes;
        }
    }

    public IntFunctionWrapper randomFunctionWrapper() {
        return functionWrappers[random.nextInt(functionWrappers.length)];
    }

    public Node getParamNode(int paramCount) {
        return new ParamNode(random.nextInt(paramCount));
    }

    public Node getConstantNode(int paramCount) {
        return new ConstantNode(random.nextInt(10));
    }

    public Node getTree(int paramCount, int level) {
        return getTree(paramCount, level, 0, 0.5F, 0.6F);
    }

    private Node getTree(int paramCount, int maxDepth, int depth, float expressionNodeProb, float paramNodeCountProb) {
        float randomFloat = random.nextFloat();
        if ((depth < maxDepth) && randomFloat < expressionNodeProb) {
            IntFunctionWrapper functionWrapper = randomFunctionWrapper();
            Node[] nodes = new Node[functionWrapper.getChildCount()];
            for (int i = 0; i < functionWrapper.getChildCount(); i++) {
                nodes[i] = getTree(paramCount, maxDepth, depth++, expressionNodeProb, paramNodeCountProb);
            }
            return new ExpressionNode(functionWrapper, nodes);
        } else if (randomFloat < paramNodeCountProb) {
            return getParamNode(paramCount);
        } else {
            return getConstantNode(paramCount);
        }
    }
}

