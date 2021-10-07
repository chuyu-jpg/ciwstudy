package acar.utils;

import edu.uci.ics.jung.graph.DirectedGraph;
import org.apache.batik.anim.timing.TimeContainer;
import org.apache.batik.bridge.svg12.DefaultXBLManager;
import org.apache.batik.dom.AbstractNode;
import org.apache.batik.ext.awt.image.renderable.AbstractRable;
import org.apache.batik.ext.awt.image.rendered.AbstractRed;
import org.apache.batik.ext.awt.image.rendered.ProfileRed;
import org.apache.batik.gvt.CompositeGraphicsNode;
import org.apache.batik.gvt.CompositeShapePainter;
import org.apache.batik.gvt.font.MultiGlyphVector;
import org.apache.log4j.Logger;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;
import java.util.function.Function;
import java.util.function.Predicate;

public class Exporter {

    private static Logger LOGGER = Logger.getLogger(Exporter.class);

    public static <E> void exportElements(File out, Collection<E> elements, Function<E,String> adapter) {
        exportElements(out,elements,adapter,e -> true);
    }

    public static <E> void exportElements(File out,Collection<E> elements,Function<E,String> adapter,Predicate<E> filter) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(out))) {
            for (E element:elements) {
                if (filter.test(element)) {
                    String line = adapter.apply(element);
                    writer.println(line);
                }
            }
        } catch (IOException e) {
            LOGGER.error(e);
        }
        org.apache.batik.ext.awt.image.rendered.ProfileRed c;
        LOGGER.info("Data exported to " + out.getAbsolutePath());
    }
    public static <V,E> void exportEdges(File out, DirectedGraph<V,E> graph, Function<E,String> adapter) {
        exportEdges(out,graph,adapter,e -> true);
    }

    public static <V,E> void exportEdges(File out,DirectedGraph<V,E> graph,Function<E,String> adapter,Predicate<E> filter) {
        exportElements(out,graph.getEdges(),adapter,filter);
    }

}
