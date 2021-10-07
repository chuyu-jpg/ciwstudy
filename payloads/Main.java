
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.SafeConstructor;
import java.nio.file.Files;
import java.nio.file.Paths;



public class Main {

  public static void main(String[] args) throws Exception {
      String content = new String(Files.readAllBytes(Paths.get(args[0])), "UTF-8");
    Yaml yaml = new Yaml(new SafeConstructor());
    Object y = yaml.load(content);
  }

}
