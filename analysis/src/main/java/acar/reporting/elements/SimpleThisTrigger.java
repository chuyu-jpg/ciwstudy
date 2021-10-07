package acar.reporting.elements;

public class SimpleThisTrigger {

   // SimpleThisTrigger(?container, ?invocation, ?a)

    Invocation invocation;

    public static SimpleThisTrigger parse(String s) {
        String[] parts = s.split("\t", -1);
        if (parts.length != 3)
            throw new IllegalArgumentException("Error parsing SimpleThisTrigger: " + s );
        SimpleThisTrigger o = new SimpleThisTrigger();
        o.invocation = Invocation.parse(parts[1]);
        return o;

    }

    public Method getFromMethod() {
        return invocation.getFromMethod();
    }

}
