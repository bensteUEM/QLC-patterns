package team.stein.qlc.view;

import org.apache.log4j.Logger;
import team.stein.qlc.App;
import team.stein.qlc.controller.Pattern;
import team.stein.qlc.model.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class AccentDim {
    private static final Logger log = Logger.getLogger(AccentDim.class);
    private static HashMap<String, FixtureValue> colors;
    List<QLCFunction> export;
    private List alllights;

    public AccentDim(List<LEDLightDRGB> alllights) {
        this.alllights = alllights;

        //Colors
        colors = new HashMap<>();
        FixtureValue val1 = new FixtureValue(0, 0, 0, 0);
        val1.applyDim = true;
        val1.applyRed = false;
        val1.applyGreen = false;
        val1.applyBlue = false;
        colors.put("ON", val1);

        FixtureValue val2 = new FixtureValue(255, 0, 0, 0);
        val2.applyDim = true;
        val2.applyRed = false;
        val2.applyGreen = false;
        val2.applyBlue = false;
        colors.put("OFF", val2);
    }

    /**
     * Accent DIM 1 - ALL
     *
     * @param allLights
     * @return
     */
    public static List<QLCFunction> accentDim1(List allLights) {
        Pattern pat1 = new Pattern(allLights.subList(0, 8));
        pat1.movement = Movement.ALL;
        List<Scene> scenes = pat1.iteratePattern(colors.get("ON"), colors.get("OFF"));

        Chaser chaser = new Chaser(-1, "all", scenes);

        log.debug("PRE-IDs : Chaser: " + chaser);
        List<Function> allFunctionsForExport = new ArrayList();
        allFunctionsForExport.add(chaser);
        allFunctionsForExport.addAll(chaser.scenes);

        return App.assignIDs(allFunctionsForExport);
    }

    /**
     * Accent DIM 2 -> ->
     *
     * @param allLights
     * @return
     */
    public static List<QLCFunction> accentDim2(List allLights) {
        Pattern pat1 = new Pattern(allLights.subList(0, 4));
        pat1.movement = Movement.LEFTtoRIGHT;
        List<Scene> scenesLeft = pat1.iteratePattern(colors.get("ON"), colors.get("OFF"));
        Pattern pat2 = new Pattern(allLights.subList(4, 8));
        pat2.movement = Movement.LEFTtoRIGHT;
        List<Scene> scenesRight = pat2.iteratePattern(colors.get("ON"), colors.get("OFF"));

        Chaser chaser = new Chaser(-1, "->->", scenesLeft);
        chaser.merge(scenesRight);

        log.debug("PRE-IDs : Chaser: " + chaser);
        List<Function> allFunctionsForExport = new ArrayList();
        allFunctionsForExport.add(chaser);
        allFunctionsForExport.addAll(chaser.scenes);

        log.debug("QLC Functions List: " + allFunctionsForExport);
        return App.assignIDs(allFunctionsForExport);
    }

    /**
     * Accent DIM 3 <- <- //TODO #4
     *
     * @param allLights
     * @return
     */
    public static List<QLCFunction> accentDim3(List allLights) {
        Pattern pat1 = new Pattern(allLights.subList(0, 4));
        pat1.movement = Movement.RIGHTtoLEFT;
        List<Scene> scenesLeft = pat1.iteratePattern(colors.get("ON"), colors.get("OFF"));
        Pattern pat2 = new Pattern(allLights.subList(4, 8));
        pat2.movement = Movement.RIGHTtoLEFT;
        List<Scene> scenesRight = pat2.iteratePattern(colors.get("ON"), colors.get("OFF"));

        Chaser chaser = new Chaser(-1, "<-<-", scenesLeft);
        chaser.merge(scenesRight);

        log.debug("PRE-IDs : Chaser: " + chaser);
        List<Function> allFunctionsForExport = new ArrayList();
        allFunctionsForExport.add(chaser);
        allFunctionsForExport.addAll(chaser.scenes);

        log.debug("QLC Functions List: " + allFunctionsForExport);
        return App.assignIDs(allFunctionsForExport);
    }

    /**
     * Accent DIM 4 -> <-
     *
     * @param allLights
     * @return
     */
    public static List<QLCFunction> accentDim4(List allLights) {
        Pattern pat1 = new Pattern(allLights.subList(0, 4));
        pat1.movement = Movement.LEFTtoRIGHT;
        List<Scene> scenesLeft = pat1.iteratePattern(colors.get("ON"), colors.get("OFF"));
        Pattern pat2 = new Pattern(allLights.subList(4, 8));
        pat2.movement = Movement.RIGHTtoLEFT;
        List<Scene> scenesRight = pat2.iteratePattern(colors.get("ON"), colors.get("OFF"));

        Chaser chaser = new Chaser(-1, "-><-", scenesLeft);
        chaser.merge(scenesRight);

        log.debug("PRE-IDs : Chaser: " + chaser);
        List<Function> allFunctionsForExport = new ArrayList();
        allFunctionsForExport.add(chaser);
        allFunctionsForExport.addAll(chaser.scenes);

        log.debug("QLC Functions List: " + allFunctionsForExport);
        return App.assignIDs(allFunctionsForExport);
    }

    /**
     * Accent DIM 5 <- -> //TODO #4
     *
     * @param allLights
     * @return
     */
    public static List<QLCFunction> accentDim5(List allLights) {
        Pattern pat1 = new Pattern(allLights.subList(0, 4));
        pat1.movement = Movement.RIGHTtoLEFT;
        List<Scene> scenesLeft = pat1.iteratePattern(colors.get("ON"), colors.get("OFF"));
        Pattern pat2 = new Pattern(allLights.subList(4, 8));
        pat2.movement = Movement.LEFTtoRIGHT;
        List<Scene> scenesRight = pat2.iteratePattern(colors.get("ON"), colors.get("OFF"));

        Chaser chaser = new Chaser(-1, "<-->", scenesLeft);
        chaser.merge(scenesRight);

        log.debug("PRE-IDs : Chaser: " + chaser);
        List<Function> allFunctionsForExport = new ArrayList();
        allFunctionsForExport.add(chaser);
        allFunctionsForExport.addAll(chaser.scenes);

        log.debug("QLC Functions List: " + allFunctionsForExport);
        return App.assignIDs(allFunctionsForExport);
    }

    /**
     * 6 = ->||-> ACCENT DIM
     */
    public static List<QLCFunction> accentDim6(List allLights) {
        Pattern pat1 = new Pattern(allLights.subList(0, 3));
        pat1.movement = Movement.LEFTtoRIGHT;
        List<Scene> scenesLeft = pat1.iteratePattern(colors.get("ON"), colors.get("OFF"));
        Pattern pat2 = new Pattern(allLights.subList(5, 8));
        pat2.movement = Movement.LEFTtoRIGHT;
        List<Scene> scenesRight = pat2.iteratePattern(colors.get("ON"), colors.get("OFF"));

        Chaser chaser = new Chaser(-1, "->||->", scenesLeft);
        chaser.merge(scenesRight);

        log.debug("PRE-IDs : Chaser: " + chaser);
        List<Function> allFunctionsForExport = new ArrayList();
        allFunctionsForExport.add(chaser);
        allFunctionsForExport.addAll(chaser.scenes);

        log.debug("QLC Functions List: " + allFunctionsForExport);
        return App.assignIDs(allFunctionsForExport);
    }

    /**
     * 7 = <-||<- ACCENT DIM
     *///TODO #7
    public static List<QLCFunction> accentDim7(List allLights) {
        Pattern pat1 = new Pattern(allLights.subList(0, 3));
        pat1.movement = Movement.RIGHTtoLEFT;
        List<Scene> scenesLeft = pat1.iteratePattern(colors.get("ON"), colors.get("OFF"));
        Pattern pat2 = new Pattern(allLights.subList(5, 8));
        pat2.movement = Movement.RIGHTtoLEFT;
        List<Scene> scenesRight = pat2.iteratePattern(colors.get("ON"), colors.get("OFF"));

        Chaser chaser = new Chaser(-1, "<-||<-", scenesLeft);
        chaser.merge(scenesRight);

        log.debug("PRE-IDs : Chaser: " + chaser);
        List<Function> allFunctionsForExport = new ArrayList();
        allFunctionsForExport.add(chaser);
        allFunctionsForExport.addAll(chaser.scenes);

        log.debug("QLC Functions List: " + allFunctionsForExport);
        return App.assignIDs(allFunctionsForExport);
    }

    /**
     * 8 = ->||<-ACCENT DIM
     */
    public static List<QLCFunction> accentDim8(List allLights) {
        Pattern pat1 = new Pattern(allLights.subList(0, 3));
        pat1.movement = Movement.LEFTtoRIGHT;
        List<Scene> scenesLeft = pat1.iteratePattern(colors.get("ON"), colors.get("OFF"));
        Pattern pat2 = new Pattern(allLights.subList(5, 8));
        pat2.movement = Movement.RIGHTtoLEFT;
        List<Scene> scenesRight = pat2.iteratePattern(colors.get("ON"), colors.get("OFF"));

        Chaser chaser = new Chaser(-1, "->||<-", scenesLeft);
        chaser.merge(scenesRight);

        log.debug("PRE-IDs : Chaser: " + chaser);
        List<Function> allFunctionsForExport = new ArrayList();
        allFunctionsForExport.add(chaser);
        allFunctionsForExport.addAll(chaser.scenes);

        log.debug("QLC Functions List: " + allFunctionsForExport);
        return App.assignIDs(allFunctionsForExport);
    }

    /**
     * 9 = <-||-> ACCENT DIM
     *///TODO #4
    public static List<QLCFunction> accentDim9(List allLights) {
        Pattern pat1 = new Pattern(allLights.subList(0, 3));
        pat1.movement = Movement.RIGHTtoLEFT;
        List<Scene> scenesLeft = pat1.iteratePattern(colors.get("ON"), colors.get("OFF"));
        Pattern pat2 = new Pattern(allLights.subList(5, 8));
        pat2.movement = Movement.LEFTtoRIGHT;
        List<Scene> scenesRight = pat2.iteratePattern(colors.get("ON"), colors.get("OFF"));

        Chaser chaser = new Chaser(-1, "<-||->", scenesLeft);
        chaser.merge(scenesRight);

        log.debug("PRE-IDs : Chaser: " + chaser);
        List<Function> allFunctionsForExport = new ArrayList();
        allFunctionsForExport.add(chaser);
        allFunctionsForExport.addAll(chaser.scenes);

        log.debug("QLC Functions List: " + allFunctionsForExport);
        return App.assignIDs(allFunctionsForExport);
    }

    /**
     * 10 = <> PingPong ACCENT DIM
     */
    public static List<QLCFunction> accentDim10(List allLights) {
        Pattern pat1 = new Pattern(allLights.subList(0, 8));
        pat1.movement = Movement.PINGPONG;
        List<Scene> scenesLeft = pat1.iteratePattern(colors.get("ON"), colors.get("OFF"));

        Chaser chaser = new Chaser(-1, "<>", scenesLeft);

        log.debug("PRE-IDs : Chaser: " + chaser);
        List<Function> allFunctionsForExport = new ArrayList();
        allFunctionsForExport.add(chaser);
        allFunctionsForExport.addAll(chaser.scenes);

        log.debug("QLC Functions List: " + allFunctionsForExport);
        return App.assignIDs(allFunctionsForExport);
    }

    /**
     * 11 = <||> ACCENT DIM
     */
    public static List<QLCFunction> accentDim11(List allLights) {
        List<LEDLightDRGB> outerlights = new ArrayList<>();
        outerlights.addAll(allLights.subList(0, 3));
        outerlights.addAll(allLights.subList(5, 8));
        Pattern pat1 = new Pattern(outerlights);
        pat1.movement = Movement.PINGPONG;
        List<Scene> scenesLeft = pat1.iteratePattern(colors.get("ON"), colors.get("OFF"));

        Chaser chaser = new Chaser(-1, "<||>", scenesLeft);

        log.debug("PRE-IDs : Chaser: " + chaser);
        List<Function> allFunctionsForExport = new ArrayList();
        allFunctionsForExport.add(chaser);
        allFunctionsForExport.addAll(chaser.scenes);

        log.debug("QLC Functions List: " + allFunctionsForExport);
        return App.assignIDs(allFunctionsForExport);
    }

    /**
     * 12 = <>||<>ACCENT DIM
     */
    public static List<QLCFunction> accentDim12(List allLights) {
        Pattern pat1 = new Pattern(allLights.subList(0, 3));
        pat1.movement = Movement.PINGPONG;
        List<Scene> scenesLeft = pat1.iteratePattern(colors.get("ON"), colors.get("OFF"));
        Pattern pat2 = new Pattern(allLights.subList(5, 8));
        pat2.movement = Movement.PINGPONG;
        List<Scene> scenesRight = pat2.iteratePattern(colors.get("ON"), colors.get("OFF"));

        Chaser chaser = new Chaser(-1, "<>||<>", scenesLeft);
        chaser.merge(scenesRight);

        log.debug("PRE-IDs : Chaser: " + chaser);
        List<Function> allFunctionsForExport = new ArrayList();
        allFunctionsForExport.add(chaser);
        allFunctionsForExport.addAll(chaser.scenes);

        log.debug("QLC Functions List: " + allFunctionsForExport);
        return App.assignIDs(allFunctionsForExport);
    }

    /**
     * 13 = >ACCENT DIM
     */
    public static List<QLCFunction> accentDim13(List allLights) {
        Pattern pat1 = new Pattern(allLights);
        pat1.movement = Movement.LEFTtoRIGHT;
        List<Scene> scenesLeft = pat1.iteratePattern(colors.get("ON"), colors.get("OFF"));

        Chaser chaser = new Chaser(-1, ">", scenesLeft);

        log.debug("PRE-IDs : Chaser: " + chaser);
        List<Function> allFunctionsForExport = new ArrayList();
        allFunctionsForExport.add(chaser);
        allFunctionsForExport.addAll(chaser.scenes);

        log.debug("QLC Functions List: " + allFunctionsForExport);
        return App.assignIDs(allFunctionsForExport);
    }

    /**
     * 14 = <ACCENT DIM
     */ //TODO #4
    public static List<QLCFunction> accentDim14(List allLights) {
        Pattern pat1 = new Pattern(allLights);
        pat1.movement = Movement.RIGHTtoLEFT;
        List<Scene> scenesLeft = pat1.iteratePattern(colors.get("ON"), colors.get("OFF"));

        Chaser chaser = new Chaser(-1, "<", scenesLeft);

        log.debug("PRE-IDs : Chaser: " + chaser);
        List<Function> allFunctionsForExport = new ArrayList();
        allFunctionsForExport.add(chaser);
        allFunctionsForExport.addAll(chaser.scenes);

        log.debug("QLC Functions List: " + allFunctionsForExport);
        return App.assignIDs(allFunctionsForExport);
    }

    /**
     * 15 = > || (>)ACCENT DIM
     */
    public static List<QLCFunction> accentDim15(List allLights) {
        List<LEDLightDRGB> outerlights = new ArrayList<>();
        outerlights.addAll(allLights.subList(0, 3));
        outerlights.addAll(allLights.subList(5, 8));
        Pattern pat1 = new Pattern(outerlights);
        pat1.movement = Movement.LEFTtoRIGHT;
        List<Scene> scenesLeft = pat1.iteratePattern(colors.get("ON"), colors.get("OFF"));

        Chaser chaser = new Chaser(-1, "> || (>)", scenesLeft);

        log.debug("PRE-IDs : Chaser: " + chaser);
        List<Function> allFunctionsForExport = new ArrayList();
        allFunctionsForExport.add(chaser);
        allFunctionsForExport.addAll(chaser.scenes);

        log.debug("QLC Functions List: " + allFunctionsForExport);
        return App.assignIDs(allFunctionsForExport);
    }

    /**
     * 16 = < || (<)ACCENT DIM
     */ //TODO #4
    public static List<QLCFunction> accentDim16(List allLights) {
        List<LEDLightDRGB> outerlights = new ArrayList<>();
        outerlights.addAll(allLights.subList(0, 3));
        outerlights.addAll(allLights.subList(5, 8));
        Pattern pat1 = new Pattern(outerlights);
        pat1.movement = Movement.RIGHTtoLEFT;
        List<Scene> scenesLeft = pat1.iteratePattern(colors.get("ON"), colors.get("OFF"));

        Chaser chaser = new Chaser(-1, "< || (<)", scenesLeft);

        log.debug("PRE-IDs : Chaser: " + chaser);
        List<Function> allFunctionsForExport = new ArrayList();
        allFunctionsForExport.add(chaser);
        allFunctionsForExport.addAll(chaser.scenes);

        log.debug("QLC Functions List: " + allFunctionsForExport);
        return App.assignIDs(allFunctionsForExport);
    }

    public List<QLCFunction> generateAll() {
        List<QLCFunction> result = new ArrayList();
        result.addAll(accentDim1(alllights));
        result.addAll(accentDim2(alllights));
        result.addAll(accentDim3(alllights));
        result.addAll(accentDim4(alllights));
        result.addAll(accentDim5(alllights));
        result.addAll(accentDim6(alllights));
        result.addAll(accentDim7(alllights));
        result.addAll(accentDim8(alllights));

        result.addAll(accentDim9(alllights));
        result.addAll(accentDim10(alllights));
        result.addAll(accentDim11(alllights));
        result.addAll(accentDim12(alllights));
        result.addAll(accentDim13(alllights));
        result.addAll(accentDim14(alllights));
        result.addAll(accentDim15(alllights));
        result.addAll(accentDim16(alllights));

        for (QLCFunction function : result) {
            function.updatePathPrefix("_AUTOGENERATED/AccentDIM");
        }

        return result;
    }

}
