package drivers.snakeyaml;

import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.SafeConstructor;

public class Main {

    public static void main(String[] args) {
        Yaml yaml = new Yaml(new SafeConstructor());
        Object obj = new Object();
        String output = yaml.dump(obj);
        Object y = yaml.load(output);

    }
}
