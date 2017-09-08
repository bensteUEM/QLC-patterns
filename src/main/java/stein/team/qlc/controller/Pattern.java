package stein.team.qlc.controller;

import org.apache.log4j.Logger;
import stein.team.qlc.model.*;

import java.util.ArrayList;
import java.util.List;

import static stein.team.qlc.model.Movement.LEFTtoRIGHT;

/**
 * Class which should be used to represent one pattern
 */
public class Pattern {
    private static final Logger log = Logger.getLogger(Pattern.class);

    public String name;
    public Mode mode;
    public Movement movement;
    List<LEDLightDRGB> lights;

    /**
     * Minimalistic Pattern Constructor
     */
    public Pattern() {
        this.lights = new ArrayList<>();
        this.mode = Mode.HIGHLIGHT;
        this.movement = LEFTtoRIGHT;
    }

    /**
     * Pattern Constructor based on defined lights
     *
     * @param list of lights
     */
    public Pattern(List<LEDLightDRGB> list) {
        this.lights = list;
        this.mode = Mode.HIGHLIGHT;
        this.movement = LEFTtoRIGHT;
    }

    /**
     * Pattern Constructor
     *
     * @param list     of lights
     * @param mode     of replacement
     * @param movement in which direction
     */
    public Pattern(List<LEDLightDRGB> list, Mode mode, Movement movement) {
        this.lights = list;
        this.mode = mode;
        this.movement = movement;
    }

    /**
     * Iterate Pattern Mode Selector
     *
     * @param val1 first value
     * @param val2 second value
     * @param mode selected
     * @return List of FixtureValues with the Values applied
     */
    public List<Scene> iteratePattern(FixtureValue val1, FixtureValue val2, Mode mode) {
        if (mode == Mode.HIGHLIGHT) {
            return iteratePatternHighlight(val1, val2);
        } else if (mode == Mode.REPLACE) {
            return iteratePatternReplace(val1, val2);
        }
        return null;
    }

    /**
     * Iterate Pattern Mode Selector - with default mode
     */
    public List<Scene> iteratePattern(FixtureValue val1, FixtureValue val2) {
        return iteratePattern(val1, val2, Mode.HIGHLIGHT);
    }

    /**
     * Placeholder for iteration with Replace mode
     *
     * @param val1
     * @param val2
     * @return
     */
    public List<Scene> iteratePatternReplace(FixtureValue val1, FixtureValue val2) {
        log.warn("REPLACE not yet implemented"); //TODO implement
        return null;
    }

    /**
     * iterate pattern with Highlight mode
     *
     * @param val1 default status
     * @param val2 highlight status
     * @return list of functions with fixture definitions
     */
    public List<Scene> iteratePatternHighlight(FixtureValue val1, FixtureValue val2) {
        ArrayList<Scene> result = new ArrayList();
        ArrayList<LEDLightDRGB> lights = new ArrayList<>();
        name = "";

        Scene fun1 = new Scene(-1, "TESTING JAVA", lights);

        switch (this.movement) {
            case ALL:
                for (LEDLightDRGB light : this.lights) {
                    name += "x";
                    lights.add(new LEDLightDRGB(light, val2));
                }
                result.add(new Scene(-1, name, lights));
                break;
            case LEFTtoRIGHT:
                log.error(this.movement + " not yet implemented");
                break;
            case RIGHTtoLEFT:
                log.error(this.movement + " not yet implemented");
                break;
            case RANDOM:
                log.error(this.movement + " not yet implemented");
                break;
            case PINGPONG:
                log.error(this.movement + " not yet implemented");
                break;
        }
        return result;
    }

    @Override
    public String toString() {
        StringBuilder text = new StringBuilder();
        text.append("Pattern with name: " + this.name);
        text.append(" contains: " + this.lights.toString());
        text.append(" is set to move " + this.movement);
        text.append(" with mode " + this.mode);

        return text.toString();
    }
}