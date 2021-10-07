package drivers.xstream;

import com.thoughtworks.xstream.XStream;

public class Main {
    public static void main(String[] args) {
        XStream xstream = new XStream();
        Object newJoe = (Object)xstream.fromXML(args[1]);
    }
}
