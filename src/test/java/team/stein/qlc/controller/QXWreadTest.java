package team.stein.qlc.controller;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import org.apache.log4j.Logger;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.io.File;

/**
 * Unit test for simple App.
 */
public class QXWreadTest extends TestCase {

    private static final Logger log = Logger.getLogger(QXWreadTest.class);

    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public QXWreadTest(String testName) {
        super(testName);
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite() {
        return new TestSuite(QXWreadTest.class);
    }

    /**
     * Rigourous Test :-)
     */
    public void testApp() throws Exception {

        QXWread qxWread = new QXWread(new File(QXWread.class.getResource("sample.qxw").getFile()));

        Element rootNode = qxWread.document.getDocumentElement();
        log.warn("Rootnode= " + rootNode.getTagName());

        NodeList nodeList = rootNode.getElementsByTagName("Fixture");
        log.info("nodelist with  " + nodeList.getLength());

        qxWread.parseDMXtoQLCId(nodeList);
        assertTrue(true);
    }
}
