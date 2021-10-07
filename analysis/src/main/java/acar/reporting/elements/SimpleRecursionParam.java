package acar.reporting.elements;

public class SimpleRecursionParam {

    //SimpleRecursiveCallParameter(?method, ?container, ?invocation) :-
    Method method;
    Class container;
    Invocation invocation;

    public static SimpleRecursionParam parse(String s) {
        String[] parts = s.split("\t", -1);
        if (parts.length != 3)
            throw new IllegalArgumentException("Error parsing SimpleRecursionParam: " + s );
        SimpleRecursionParam o = new SimpleRecursionParam();
        o.method = Method.parse(parts[0]);
        o.container = Class.parse(parts[1]);
        o.invocation = Invocation.parse(parts[2]);
        return o;

    }

    public Method getMethod() {
        return method;
    }


}
