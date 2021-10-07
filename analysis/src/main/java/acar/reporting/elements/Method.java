package acar.reporting.elements;

public class Method {

    Class owner;
    String doopName;

    public static Method parse(String s) {
        Method m = new Method();
        m.doopName = s;
        String p = s.split(":")[0];
        m.owner = Class.parse(p.substring(1, p.length()));
        return m;
    }

    public String getDoopName() {
        return doopName;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Method method = (Method) o;

        return doopName != null ? doopName.equals(method.doopName) : method.doopName == null;
    }

    @Override
    public int hashCode() {
        return doopName != null ? doopName.hashCode() : 0;
    }

    public Class getOwner() {
        return owner;
    }

}