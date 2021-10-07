package acar.reporting.elements;

public class SimpleFlowParam {
    // SimpleParamWithFlow(?container:Type, ?invocation:MethodInvocation, ?a:Var)

    Invocation inv;

    public static SimpleFlowParam parse(String s) {
        String[] parts = s.split("\t", -1);
        if (parts.length != 3)
            throw new IllegalArgumentException("Error parsing SimpleFlowThis: " + s );
        SimpleFlowParam o = new SimpleFlowParam();
        o.inv = Invocation.parse(parts[1]);
        return o;
    }

    public Method getMethod() {
        return inv.getFromMethod();
    }

}
