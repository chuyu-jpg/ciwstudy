package acar.reporting.elements;

public class SimpleRecursionThis {
    // SimpleRecursiveCallThis(?fromMethod, ?container, ?component,  ?invocation) :-
    Method method;
    Class container;
    Class component;
    Invocation invocation;

    public static SimpleRecursionThis parse(String s) {
        String[] parts = s.split("\t", -1);
        if (parts.length != 4)
            throw new IllegalArgumentException("Error parsing SimpleRecursionThis: " + s );
        SimpleRecursionThis o = new SimpleRecursionThis();
        o.method = Method.parse(parts[0]);
        o.container = Class.parse(parts[1]);
        o.component = Class.parse(parts[2]);
        o.invocation = Invocation.parse(parts[3]);
        return o;

    }
    public Method getMethod() {
        return method;
    }
}
