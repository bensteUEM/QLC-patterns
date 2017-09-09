package team.stein.qlc.controller;

import org.apache.log4j.Logger;
import team.stein.qlc.model.*;

import java.util.ArrayList;
import java.util.List;

import static team.stein.qlc.model.Movement.LEFTtoRIGHT;

/**
 * Class which should be used to represent one pattern
 */
@SuppressWarnings("CanBeFinal")
public class Pattern {
    private static final Logger log = Logger.getLogger(Pattern.class);
    public Movement movement;
    private String name;
    private Mode mode;
    private List<LEDLightDRGB> lights;

    /**
     * Minimalistic Pattern Constructor
     */
    private Pattern() {
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
    private Pattern(List<LEDLightDRGB> list, Mode mode, Movement movement) {
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
    private List<Scene> iteratePattern(FixtureValue val1, FixtureValue val2, Mode mode) {
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
     * @param val1 old value
     * @param val2 new value
     * @return list of scens for this action
     */
    private List<Scene> iteratePatternReplace(FixtureValue val1, FixtureValue val2) {
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
    private List<Scene> iteratePatternHighlight(FixtureValue val1, FixtureValue val2) {
        ArrayList<Scene> result = new ArrayList();
        ArrayList<LEDLightDRGB> lightsStep; //to be initialized in every step

        switch (this.movement) {
            case ALL:
                name = "";
                lightsStep = new ArrayList<>();
                for (LEDLightDRGB light : this.lights) {
                    name += "|";
                    lightsStep.add(new LEDLightDRGB(light, val2));
                }
                result.add(new Scene(-1, name, lightsStep));
                break;
            case LEFTtoRIGHT:
                for (LEDLightDRGB step : this.lights) {
                    name = "";
                    lightsStep = new ArrayList<>();
                    for (LEDLightDRGB light : this.lights) {
                        if (step.equals(light)) {
                            name += "X";
                            lightsStep.add(new LEDLightDRGB(light, val2));
                        } else {
                            name += "-";
                            lightsStep.add(new LEDLightDRGB(light, val1));
                        }
                    }
                    result.add(new Scene(-1, name, lightsStep));
                }
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
        String text = "Pattern with name: " +
                this.name + " contains: " +
                this.lights.toString() +
                " is set to move " +
                this.movement +
                " with mode " + this.mode;

        return text;
    }
}
