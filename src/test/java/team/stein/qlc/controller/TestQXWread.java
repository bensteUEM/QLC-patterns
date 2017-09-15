package team.stein.qlc.controller;

import com.sun.org.apache.xerces.internal.dom.DeferredElementImpl;
import org.apache.log4j.Logger;
import org.junit.BeforeClass;
import org.junit.Test;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.io.File;

import static org.junit.Assert.assertEquals;

/**
 * Unit test for simple App.
 */
public class TestQXWread {

    private static final Logger log = Logger.getLogger(TestQXWread.class);
    public static QXWread qxWread;

    @BeforeClass
    public static void initData() throws Exception {
        qxWread = new QXWread(new File(QXWread.class.getResource("sample.qxw").getFile()));
    }

    @Test
    public void testFixtureReading() throws Exception {
        Element rootNode = qxWread.document.getDocumentElement();
        log.warn("Rootnode= " + rootNode.getTagName());

        NodeList nodeList = rootNode.getElementsByTagName("Fixture");
        log.info("nodelist with  " + nodeList.getLength());

        qxWread.parseDMXtoQLCId(nodeList);
        assert (true);
    }

    /**
     * test if the highest function ID equals the one known from the test file
     * the current sample.qxw includes 239 Functions within Engine
     */
    @Test
    public void testHighestFunctionID() {
        Element rootNode = qxWread.document.getDocumentElement();
        NodeList engineNodes = rootNode.getElementsByTagName("Engine").item(0).getChildNodes();
        NodeList nodeList = ((DeferredElementImpl) engineNodes).getElementsByTagName("Function");
        assertEquals(239, qxWread.getHighestFunctionID(nodeList) );
    }
}
