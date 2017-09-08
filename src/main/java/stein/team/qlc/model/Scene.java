package stein.team.qlc.model;

import java.util.List;

/**
 * A scene is one representation of multiple lights at one given moment
 * it may contain additional arguments for default times and other metadata
 */
public class Scene {
    public List<LEDLightDRGB> lights;
    Integer fadeIn, fadeOut, duration, id; //TODO did not check datatype
    public String name;
    String path;

    /**
     * Minimalistic Constructor for a function with default params
     *
     * @param id   which is unique to the project
     * @param name human readable name
     */
    public Scene(Integer id, String name, List<LEDLightDRGB> lights) {
        this.id = id;
        this.name = name;
        this.lights = lights;

        this.path = "";
        this.fadeIn = 0;
        this.fadeOut = 0;
        this.duration = 0;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append("\n\tScene with name " + this.name)
                .append(" with ID=" + id)
                .append(" in Path=" + path);
        result.append(" With FadeIn/Out/Duration of " + fadeIn + ";" + fadeOut + ";" + duration);
        result.append("\n\t\t" + lights.toString());
        return result.toString();
    }


    /*
    <Function ID="204" Type="Chaser" Name="Decke Blau/Gelb" Path="15 Uhr GoDi/OLD">
   <Speed FadeIn="100" FadeOut="200" Duration="1107968"/>
   <Direction>Forward</Direction>
   <ChaserRunOrder>Loop</ChaserRunOrder>
   <SpeedModes FadeIn="Common" FadeOut="Common" Duration="Common"/>
   <Step Number="0" FadeIn="200" Hold="0" FadeOut="200">121</Step>
   <Step Number="1" FadeIn="0" Hold="0" FadeOut="0">162</Step>
   <Step Number="2" FadeIn="0" Hold="0" FadeOut="0">161</Step>
   <Step Number="3" FadeIn="0" Hold="0" FadeOut="0">116</Step>
  </Function>
     */
}
