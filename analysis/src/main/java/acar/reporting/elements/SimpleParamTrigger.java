package acar.reporting.elements;

public class SimpleParamTrigger {

   // SimpleParamTrigger(?invocation, ?a)
    Invocation invocation;

    public static SimpleParamTrigger parse(String s) {
        String[] parts = s.split("\t", -1);
        if (parts.length != 2)
            throw new IllegalArgumentException("Error parsing SimpleParamTrigger: " + s );
        SimpleParamTrigger o = new SimpleParamTrigger();
        o.invocation = Invocation.parse(parts[0]);
        return o;
    }
    public String getInvocation() { return invocation.callsite+"\t"+invocation.getFromMethod().getDoopName(); }
    public Method getFromMethod() {
        return invocation.getFromMethod();
    }
}
