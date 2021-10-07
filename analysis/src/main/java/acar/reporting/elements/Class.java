package acar.reporting.elements;

public class Class {

    String name;

    public static Class parse(String s) {
        Class c = new Class();
        c.name = s;
        return c;
    }

    public String getName() {
        return name;
    }

}