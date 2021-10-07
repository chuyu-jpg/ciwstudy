package drivers.stringtemplate;

import org.antlr.stringtemplate.*;
import org.antlr.stringtemplate.language.*;

public class Main {
    public static void main(String[] args) {
        StringTemplate template = new StringTemplate("$name$", DefaultTemplateLexer.class);
        template.setAttribute("name", "foo");
        Object o = template.getAttribute("name");
    }
}
