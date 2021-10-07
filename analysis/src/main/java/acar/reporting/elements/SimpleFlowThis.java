package acar.reporting.elements;

public class SimpleFlowThis {
// SimpleThis(?container, ?invocation, ?actual)

    Invocation inv;

    public static SimpleFlowThis parse(String s) {
        String[] parts = s.split("\t", -1);
        if (parts.length != 3)
            throw new IllegalArgumentException("Error parsing SimpleFlowThis: " + s );
        SimpleFlowThis o = new SimpleFlowThis();
        o.inv = Invocation.parse(parts[1]);
        return o;
    }

    public Method getMethod() {
        return inv.getFromMethod();
    }


}
