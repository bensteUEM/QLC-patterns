package stein.team.qlc;

import org.apache.log4j.Logger;
import stein.team.qlc.controller.Pattern;
import stein.team.qlc.model.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Hello world!
 */
public class App {
    private static final Logger log = Logger.getLogger(App.class);

    public static void main(String[] args) {


        List<LEDLightDRGB> alllights = new ArrayList<>();
        // genrate the TourLED group as used in regular EFG setup with 8 spots
        for (Integer i = 130; i <= 200; i += 10) {
            alllights.add(new LEDLightDRGB(i, -1));
        }

        //Generate Pattern and printout debug
        Pattern pat1 = new Pattern(alllights.subList(0, 3));
        pat1.movement = Movement.LEFTtoRIGHT;
        log.debug("Generated: " + pat1);

        FixtureValue val1 = new FixtureValue();
        FixtureValue val2 = new FixtureValue(0, 255, 255, 255);

        List<Scene> scenesLeft = pat1.iteratePattern(val1, val2);

        Pattern pat2 = new Pattern(alllights.subList(3, 5));
        pat2.movement = Movement.ALL;
        List<Scene> scenesMiddle = pat2.iteratePattern(val1, val2);
        Pattern pat3 = new Pattern(alllights.subList(5, 8));
        pat3.movement = Movement.LEFTtoRIGHT;
        List<Scene> scenesRight = pat3.iteratePattern(val1, val2);

        Chaser chaser = new Chaser(-1, ">||>", scenesLeft);
        chaser.merge(scenesMiddle);
        chaser.merge(scenesRight);
        log.debug("Chaser: " + chaser);
    }
}
