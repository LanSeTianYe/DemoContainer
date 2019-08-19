package com.xiaotian.demo.algorithm.genetic;

/**
 * 树拷贝工具
 */

public class TreeCopyUtil {

    public static Node copyFrom(Node oldTree) {
        if (oldTree == null) {
            return null;
        }
        if (oldTree instanceof ExpressionNode) {
            ExpressionNode expressionNode = (ExpressionNode) oldTree;
            Node[] copyNode = new Node[expressionNode.getChildren().length];

            for (int i = 0; i < expressionNode.getChildren().length; i++) {
                copyNode[i] = copyFrom(expressionNode.getChildren()[i]);
            }
            IntFunctionWrapper intFunctionWrapper = new IntFunctionWrapper(expressionNode.getFunction(), expressionNode.getName(), expressionNode.getChildren().length);
            return new ExpressionNode(intFunctionWrapper, copyNode);
        } else if (oldTree instanceof ParamNode) {
            ParamNode paramNode = (ParamNode) oldTree;
            return new ParamNode(paramNode.getIndex());
        } else {
            ConstantNode constantNode = (ConstantNode) oldTree;
            return new ConstantNode(constantNode.getValue());
        }
    }
}
