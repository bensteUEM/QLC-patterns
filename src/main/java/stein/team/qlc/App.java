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
        pat1.name = "TEST Pattern";
        log.debug("Generated: " + pat1);

        FixtureValue val1 = new FixtureValue();
        FixtureValue val2 = new FixtureValue(0, 255, 255, 255);

        List<Scene> scenes = pat1.iteratePattern(val1, val2);

        Chaser chaser = new Chaser(-1, "White TESTING JAVA", scenes);
        log.debug("Chaser: " + chaser);
    }
}
