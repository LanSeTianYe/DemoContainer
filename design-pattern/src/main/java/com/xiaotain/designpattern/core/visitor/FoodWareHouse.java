package com.xiaotain.designpattern.core.visitor;

public class FoodWareHouse {


    public static void main(String[] args) {

        Banana banana = new Banana();
        Rice rice = new Rice();

        NameVisitor nameVisitor = new NameVisitor();
        CountVisitor countVisitor = new CountVisitor();

        System.out.println(nameVisitor.visit(banana));
        System.out.println(nameVisitor.visit(rice));

        System.out.println(countVisitor.visit(banana));
        System.out.println(countVisitor.visit(rice));

    }
}
