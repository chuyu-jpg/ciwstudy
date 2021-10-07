package acar.reporting.elements;

public class Composite {

    Class container;
    Class component;
    Signature signature;


    public static Composite parse(String s) {
        String[] parts = s.split("\t", -1);
        if (parts.length != 3)
            throw new IllegalArgumentException("Error parsing component: " + s );
        Composite c = new Composite();
        c.container = Class.parse(parts[0]);
        c.component = Class.parse(parts[1]);
        c.signature = Signature.parse(parts[2]);
        return c;
    }

    public Class getContainer() {
        return container;
    }
}
