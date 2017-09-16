package team.stein.qlc;

import com.sun.org.apache.xerces.internal.dom.DeferredElementImpl;
import org.apache.commons.lang.StringEscapeUtils;
import org.apache.log4j.Logger;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import team.stein.qlc.controller.QXWread;
import team.stein.qlc.model.Chaser;
import team.stein.qlc.model.Function;
import team.stein.qlc.model.LEDLightDRGB;
import team.stein.qlc.model.Scene;
import team.stein.qlc.view.AccentDim;
import team.stein.qlc.view.QLCFunction;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Application which should generate valid Chasers and Scenes which can be used with an existing QXW file as used by QLC+
 */
public class App {
    private static final Logger log = Logger.getLogger(App.class);
    private static QXWread qxwRead;
    private static int highestFunctionID;
    private static HashMap<Integer, Integer> parseDMXtoQLCId;

    public static void main(String[] args) {
        // read the base file
        qxwRead = new QXWread(new File(QXWread.class.getResource("sample.qxw").getFile()));
        Element rootNode = qxwRead.document.getDocumentElement();
        NodeList engineNodes = rootNode.getElementsByTagName("Engine").item(0).getChildNodes();
        NodeList functionNodes = ((DeferredElementImpl) engineNodes).getElementsByTagName("Function");
        highestFunctionID = qxwRead.getHighestFunctionID(functionNodes);

        NodeList fixtureNodes = ((DeferredElementImpl) engineNodes).getElementsByTagName("Fixture");
        parseDMXtoQLCId = qxwRead.parseDMXtoQLCId(fixtureNodes);

        List<LEDLightDRGB> allLights = new ArrayList<>();
        // generate the TourLED group as used in regular EFG setup with 8 spots

        for (Integer i = 130; i <= 200; i += 10) {
            allLights.add(new LEDLightDRGB(i, parseDMXtoQLCId.get(i)));
        }

        //application
        AccentDim accentDim = new AccentDim(allLights);
        List<QLCFunction> export = accentDim.generateAll();
        exportToFile(export);
    }

    /**
     * Converts internal objects to QLCFunctions and assigns IDs
     *
     * @param allFunctionsForExport List of Function (scene or chaser)
     * @return list of exportable functions for QLC+
     */
    public static List<QLCFunction> assignIDs(List<Function> allFunctionsForExport) {
        ArrayList<QLCFunction> export = new ArrayList<>();
        Chaser lastChaser = null;
        for (Function function : allFunctionsForExport) {
            if (function instanceof Scene) {
                Scene scene = (Scene) function;
                scene.setID(highestFunctionID + 1);
                scene.addPathPrefix(StringEscapeUtils.unescapeXml(lastChaser.getName()));
                export.add(new QLCFunction((Scene) function));
            } else if (function instanceof Chaser) {
                lastChaser = (Chaser) function;
                lastChaser.setID(highestFunctionID + 1);
                export.add(new QLCFunction(lastChaser));
            }
            highestFunctionID++;
        }
        return export;
    }

    /**
     * Writes to file
     *
     * @param export
     */
    public static void exportToFile(List<QLCFunction> export) {
        BufferedWriter bw = null;
        FileWriter fw = null;

        try {
            fw = new FileWriter("export.qxw");
            bw = new BufferedWriter(fw);

            for (QLCFunction function : export) {
                bw.write(function.toQXWString());
                log.debug(function.toQXWString());

            }

            System.out.println("Done");

        } catch (IOException e) {
            log.error(e.getMessage());
        } finally {
            try {
                if (bw != null)
                    bw.close();
                if (fw != null)
                    fw.close();
            } catch (IOException ex) {
                log.error(ex.getMessage());
            }
        }
    }
}
