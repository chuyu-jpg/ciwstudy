package drivers.protobuf;

import java.io.FileInputStream;

public class Main {

    public static void main(String[] args) throws Exception {
        Msg.Node.parseFrom(new FileInputStream(args[0]));

    }
}
