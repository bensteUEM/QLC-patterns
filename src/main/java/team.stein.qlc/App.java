package team.stein.qlc;

import com.sun.org.apache.xerces.internal.dom.DeferredElementImpl;
import org.apache.log4j.Logger;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import team.stein.qlc.controller.Pattern;
import team.stein.qlc.controller.QXWread;
import team.stein.qlc.model.*;
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
class App {
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

        List<QLCFunction> export = accentDim1(allLights);
        export.addAll(accentDim2(allLights));
        exportToFile(export);
    }

    /**
     * Accent DIM 1 - ALL
     *
     * @param allLights
     * @return
     */
    public static List<QLCFunction> accentDim1(List allLights) {
        FixtureValue val1 = new FixtureValue(0, 0, 0, 0);
        val1.applyDim = true;
        val1.applyRed = false;
        val1.applyGreen = false;
        val1.applyBlue = false;
        FixtureValue val2 = new FixtureValue(255, 0, 0, 0);
        val2.applyDim = true;
        val2.applyRed = false;
        val2.applyGreen = false;
        val2.applyBlue = false;

        Pattern pat1 = new Pattern(allLights.subList(0, 8));
        pat1.movement = Movement.ALL;
        List<Scene> scenes = pat1.iteratePattern(val1, val2);

        Chaser chaser = new Chaser(-1, "all", scenes);

        log.debug("PRE-IDs : Chaser: " + chaser);
        List<Function> allFunctionsForExport = new ArrayList();
        allFunctionsForExport.add(chaser);
        allFunctionsForExport.addAll(chaser.scenes);

        return assignIDs(allFunctionsForExport);
    }

    /**
     * Accent DIM 2 -> ->
     *
     * @param allLights
     * @return
     */
    public static List<QLCFunction> accentDim2(List allLights) {
        FixtureValue val1 = new FixtureValue(0, 0, 0, 0);
        val1.applyDim = true;
        val1.applyRed = false;
        val1.applyGreen = false;
        val1.applyBlue = false;
        FixtureValue val2 = new FixtureValue(255, 0, 0, 0);
        val2.applyDim = true;
        val2.applyRed = false;
        val2.applyGreen = false;
        val2.applyBlue = false;

        Pattern pat1 = new Pattern(allLights.subList(0, 4));
        pat1.movement = Movement.LEFTtoRIGHT;
        List<Scene> scenesLeft = pat1.iteratePattern(val1, val2);
        Pattern pat2 = new Pattern(allLights.subList(4, 8));
        pat2.movement = Movement.LEFTtoRIGHT;
        List<Scene> scenesRight = pat2.iteratePattern(val1, val2);

        Chaser chaser = new Chaser(-1, "->->", scenesLeft);
        chaser.merge(scenesRight);

        log.debug("PRE-IDs : Chaser: " + chaser);
        List<Function> allFunctionsForExport = new ArrayList();
        allFunctionsForExport.add(chaser);
        allFunctionsForExport.addAll(chaser.scenes);

        log.debug("QLC Functions List: " + allFunctionsForExport);
        return assignIDs(allFunctionsForExport);
    }

    /**
     * Converts internal objects to QLCFunctions and assigns IDs
     *
     * @param allFunctionsForExport List of Function (scene or chaser)
     * @return list of exportable functions for QLC+
     */
    public static List<QLCFunction> assignIDs(List<Function> allFunctionsForExport) {
        ArrayList<QLCFunction> export = new ArrayList<>();
        for (Function function : allFunctionsForExport) {
            if (function instanceof Scene) {
                ((Scene) function).setID(highestFunctionID + 1);
                export.add(new QLCFunction((Scene) function));
            } else if (function instanceof Chaser) {
                ((Chaser) function).setID(highestFunctionID + 1);
                export.add(new QLCFunction((Chaser) function));
            }
            function.addPathPrefix("JAVATest");
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
