package acar.reporting.elements;

public class Invocation {
    Method fromMethod;
    Method to;
    String callsite;

    public static Invocation parse(String s) {
        String[] parts = s.split("/");
        Invocation i = new Invocation();
        i.fromMethod = Method.parse(parts[0]);
        i.callsite = s;
        return i;
    }

    public Method getFromMethod() {
        return fromMethod;
    }

}