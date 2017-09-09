package team.stein.qlc;

import org.apache.log4j.Logger;
import team.stein.qlc.controller.Pattern;
import team.stein.qlc.model.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Hello world!
 */
class App {
    private static final Logger log = Logger.getLogger(App.class);

    public static void main(String[] args) {


        List<LEDLightDRGB> allLights = new ArrayList<>();
        // generate the TourLED group as used in regular EFG setup with 8 spots
        for (Integer i = 130; i <= 200; i += 10) {
            allLights.add(new LEDLightDRGB(i, -1));
        }

        //Generate Pattern and printout debug
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
        log.debug("Chaser: " + chaser);


        //Another example non in order

        pat1 = new Pattern(allLights.subList(3, 5));
        pat1.movement = Movement.ALL;
        List<Scene> scenes_inner = pat2.iteratePattern(val1, val2);
        List<LEDLightDRGB> lights2 = new ArrayList();
        lights2.addAll(allLights.subList(0, 3));
        lights2.addAll(allLights.subList(5, 8));
        pat2 = new Pattern(lights2);
        pat2.movement = Movement.LEFTtoRIGHT;
        List<Scene> scenes_outer = pat2.iteratePattern(val1, val2);

        chaser = new Chaser(-1, ">||(>)", scenes_outer);
        chaser.merge(scenes_inner);
        log.debug("Chaser: " + chaser);

    }
}
