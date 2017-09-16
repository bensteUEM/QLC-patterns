package team.stein.qlc;

import com.sun.org.apache.xerces.internal.dom.DeferredElementImpl;
import org.apache.log4j.Logger;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import team.stein.qlc.controller.Pattern;
import team.stein.qlc.controller.QXWread;
import team.stein.qlc.model.*;
import team.stein.qlc.view.QLCFunction;

import java.io.File;
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

        firstCase(allLights);
        //anotherCase(allLights);
    }

    public static void firstCase(List allLights) {
        Pattern pat1 = new Pattern(allLights.subList(0, 3));
        pat1.movement = Movement.LEFTtoRIGHT;
        log.debug("Generated: " + pat1);

        FixtureValue val1 = new FixtureValue();
        FixtureValue val2 = new FixtureValue(0, 255, 255, 255);

        List<Scene> scenesLeft = pat1.iteratePattern(val1, val2);

        Pattern pat2 = new Pattern(allLights.subList(3, 5));
        pat2.movement = Movement.ALL;
        List<Scene> scenesMiddle = pat2.iteratePattern(val1, val2);
        Pattern pat3 = new Pattern(allLights.subList(5, 8));
        pat3.movement = Movement.LEFTtoRIGHT;
        List<Scene> scenesRight = pat3.iteratePattern(val1, val2);

        Chaser chaser = new Chaser(-1, ">||>", scenesLeft);
        chaser.merge(scenesMiddle);
        chaser.merge(scenesRight);

        log.debug("PRE-IDs : Chaser: " + chaser);


        List<Function> allFunctionsForExport = new ArrayList();
        allFunctionsForExport.add(chaser);
        allFunctionsForExport.addAll(chaser.scenes);

        /**
         * Assign function IDs and add to export
         */
        List<QLCFunction> export = new ArrayList<>();
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
        log.debug("POST-IDs : Chaser: " + chaser);


        log.debug("QLC Functions List: " + export);

        for (QLCFunction function : export) {
            log.debug(function.toQXWString());
        }
        //TODO continue here to prepare IDs for export? #1
    }

    public static void anotherCase(List allLights) {
        FixtureValue val1 = new FixtureValue();
        FixtureValue val2 = new FixtureValue(0, 255, 255, 255);

        //Another example non in order
        Pattern pat1 = new Pattern(allLights.subList(3, 5));
        pat1.movement = Movement.ALL;
        List<Scene> scenes_inner = pat1.iteratePattern(val1, val2);

        List<LEDLightDRGB> lights2 = new ArrayList();
        lights2.addAll(allLights.subList(0, 3));
        lights2.addAll(allLights.subList(5, 8));
        Pattern pat2 = new Pattern(lights2);
        pat2.movement = Movement.LEFTtoRIGHT;
        List<Scene> scenes_outer = pat2.iteratePattern(val1, val2);

        Chaser chaser = new Chaser(-1, ">||(>)", scenes_outer);
        chaser.merge(scenes_inner);
        log.debug("Chaser: " + chaser);
    }
}
