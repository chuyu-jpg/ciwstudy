package drivers.xmlbean;

import java.io.*;
import org.apache.xmlbeans.*;
import org.openuri.nameworld.*;

public class Main
{
    public static void main(String[] args) throws Exception
    {
        // Bind the incoming XML to an XMLBeans type.
        String xmlStr = "foo";
        NameworldDocument doc = null;
        try
        {
            doc = NameworldDocument.Factory.parse(xmlStr);
        } catch (Exception e)
        {
            e.printStackTrace();
        }

    }
}
