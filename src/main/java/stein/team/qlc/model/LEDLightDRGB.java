package stein.team.qlc.model;

/**
 * Basic Colorful light definiion with
 * Dimmer, Red, Green, Blue
 */
public class LEDLightDRGB{
    Byte dmxAdress;

    LEDLightDRGB(Byte dmxAdress){
        this.dmxAdress = dmxAdress;
    }
}
