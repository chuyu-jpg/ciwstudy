package acar.reporting.elements;

public class Signature {
    String descriptor;
    public static Signature parse(String s) {
        Signature signature = new Signature();
        signature.descriptor = s;
        return signature;
    }
}