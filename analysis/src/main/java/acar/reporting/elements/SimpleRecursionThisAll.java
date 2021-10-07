package acar.reporting.elements;

public class SimpleRecursionThisAll {

  //  SimpleRecursiveCallThisAll(?fromMethod,  ?invocation)
    Method method;
    Invocation invocation;

    public static SimpleRecursionThisAll parse(String s) {
        String[] parts = s.split("\t", -1);
        if (parts.length != 2)
            throw new IllegalArgumentException("Error parsing SimpleRecursionThisAll: " + s );
        SimpleRecursionThisAll o = new SimpleRecursionThisAll();
        o.method = Method.parse(parts[0]);
        o.invocation = Invocation.parse(parts[1]);
        return o;

    }

    public Method getMethod() {
        return method;
    }

    public Invocation getInvocation() {
        return invocation;
    }

}
