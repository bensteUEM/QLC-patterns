package team.stein.qlc.controller;

import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

public class QXWread {

    private static final Logger log = Logger.getLogger(QXWread.class);
    Document document;
    private HashMap<Integer, Integer> dmxToQlc;

    /**
     * Constructor
     *
     * @param file saved from QLight+
     */
    public QXWread(File file) {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = null;
        try {
            db = dbf.newDocumentBuilder();
            this.document = db.parse(file);

        } catch (ParserConfigurationException | IOException | SAXException e) {
            log.error(e.getMessage());
        }
    }


    /**
     * This function should read NodeList of Fixtures extracted from a QXW safe File and then enable calling a QLCId by DMX address
     * Following first analysis the dmxaddress seems to be zero based
     *
     * @return Hashmap with DMXAddress -> QLC-ID
     */
    public HashMap<Integer, Integer> parseDMXtoQLCId(NodeList listOfFixtures) {
        HashMap result = new HashMap();

        for (int x = 0, size = listOfFixtures.getLength(); x < size; x++) {
            Node nNode = listOfFixtures.item(x);
            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                Element eElement = (Element) nNode;
                String id = eElement.getElementsByTagName("ID").item(0).getTextContent();
                String dmxAdresses = eElement.getElementsByTagName("Address").item(0).getTextContent();

                result.put(Integer.parseInt(dmxAdresses) + 1, Integer.parseInt(id));
            }
        }

        log.info("Created Hashmap for " + result.size() + " fixtures: " + result.toString());
        this.dmxToQlc = result;
        return result;
    }

    /**
     * This function checks a list of nodes from a qxw file for the highest function ID
     *
     * @return an ID number which can be used for a new function
     */
    public int generateNewFunctionId(NodeList listOfFunction) {
        int highest = -1;
        for (int x = 0, size = listOfFunction.getLength(); x < size; x++) {
            Node nNode = listOfFunction.item(x);
            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                String text = ((Element) nNode).getAttribute("ID");
                int id = Integer.parseInt(text);
                highest = (id > highest) ? id : highest;
            }
        }
        return highest + 1;
    }
}
