package drivers.jaxb;


import org.apache.batik.anim.dom.SVGOMElement;
import org.w3c.dom.Node;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.namespace.QName;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

public class Main {

    public static void main(String[] args) throws Exception {


        try {

            JAXBContext jaxbContext = JAXBContext.newInstance(Main.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            File XMLfile = new File("foo");
            Object f = new Object();
            jaxbMarshaller.marshal(f, XMLfile);
SVGOMElement g;

            jaxbMarshaller.marshal(f, System.out);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            jaxbUnmarshaller.unmarshal(XMLfile);

        } catch (JAXBException e) { }


    }
}
