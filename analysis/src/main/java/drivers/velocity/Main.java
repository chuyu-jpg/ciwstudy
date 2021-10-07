package drivers.velocity;

import java.io.StringWriter;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;

public class Main
{
    public static void main( String[] args )
            throws Exception
    {
        VelocityEngine ve = new VelocityEngine();
        ve.init();
        Template t = ve.getTemplate( "foo.vm" );
        VelocityContext context = new VelocityContext();
        context.put("var", "value");
        StringWriter writer = new StringWriter();
        t.merge( context, writer );
    }
}