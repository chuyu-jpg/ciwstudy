package drivers.ognl;

import ognl.Ognl;
import ognl.OgnlContext;

public class Main {

    public static void main(String[] args) throws Exception {
        String exprStr = "";
        Object expr = Ognl.parseExpression(exprStr);
        Object value = Ognl.getValue(expr, "foo");

    }
}
