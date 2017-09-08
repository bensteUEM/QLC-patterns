package stein.team.qlc.controller;

import stein.team.qlc.model.LEDLightDRGB;
import stein.team.qlc.model.Mode;
import stein.team.qlc.model.Movement;

import java.util.ArrayList;
import java.util.List;

/**
 * Class which should be used to represent one pattern
 */
public class Pattern {
    List<LEDLightDRGB> list;
    Mode mode;
    Movement movement;

    Pattern (){
        list = new ArrayList<>();
        mode = Mode.HIGHLIGHT;
        movement = Movement.LEFT;
    }
}
