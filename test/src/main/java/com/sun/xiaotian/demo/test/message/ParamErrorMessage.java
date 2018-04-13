package com.sun.xiaotian.demo.test.message;

import java.util.ArrayList;
import java.util.List;

/**
 * 参数错误信息
 */
public class ParamErrorMessage {

    private List<NameAndValue> nameAndValueList = new ArrayList<>();

    public ParamErrorMessage add(String name, String message) {
        nameAndValueList.add(new NameAndValue(name, message));
        return this;
    }

    @Override
    public String toString() {
        return nameAndValueList.toString();
    }

    class NameAndValue {

        private final String paramName;

        private final String errorMessage;

        NameAndValue(String name, String message) {
            this.paramName = name;
            this.errorMessage = message;
        }

        @Override
        public String toString() {
            return "{" +
                    "paramName='" + paramName + '\'' +
                    ", errorMessage='" + errorMessage + '\'' +
                    '}';
        }
    }

    public static void main(String[] args) {
        ParamErrorMessage paramErrorMessage = new ParamErrorMessage();
        paramErrorMessage
                .add("name", "不能为空")
                .add("age", "不能大于200")
                .add("startDate和endDate", "开始日期不能大于结束日期")
        ;
        System.out.println(paramErrorMessage);
    }
}
