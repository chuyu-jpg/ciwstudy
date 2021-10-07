package acar.tests;

import acar.analysis.Analysis;
import org.apache.log4j.BasicConfigurator;
import org.junit.*;

import java.io.File;
import java.util.ArrayList;

import static java.util.stream.Collectors.groupingBy;
import static org.junit.Assert.*;


public class TestsFieldSignatures {

    static Analysis analysis;

    @BeforeClass
    public static void runTestAnalysis() throws Exception {

        File inputFile = new File("data/rt.jar");
        File outputFolder = new File("out");
        new File("out").mkdirs();

        BasicConfigurator.configure();
        analysis = new Analysis();
        analysis.runAnalysis(inputFile, outputFolder);
        ArrayList a = new ArrayList();
        a.hashCode();


    }

    @Ignore
    @Test
    public void test() {
     assertEquals(true, true);
    }




}
