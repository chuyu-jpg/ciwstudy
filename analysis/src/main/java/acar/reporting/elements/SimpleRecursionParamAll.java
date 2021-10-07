package acar.reporting.elements;

public class SimpleRecursionParamAll {
  //  SimpleRecursiveCallParameterAll(?method,  ?invocation)
    Method method;
    Invocation invocation;

    public static SimpleRecursionParamAll parse(String s) {
        String[] parts = s.split("\t", -1);
        if (parts.length != 2)
            throw new IllegalArgumentException("Error parsing SimpleRecursionParamAll: " + s );
        SimpleRecursionParamAll o = new SimpleRecursionParamAll();
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
