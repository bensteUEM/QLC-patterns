package stein.team.qlc.model;

import org.apache.log4j.Logger;

import java.util.LinkedList;
import java.util.List;

/**
 * A scene is one representation of multiple lights at one given moment
 * it may contain additional arguments for default times and other metadata
 */
public class Scene {
    private static final Logger log = Logger.getLogger(Scene.class);

    public List<LEDLightDRGB> lights;
    public String name;
    Integer fadeIn, fadeOut, duration, id; //TODO did not check datatype
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

    /**
     * Utility used to merge the values and names of two scenes
     *
     * @param scene which should be merged into the current one
     * @return number of lights in merged scene
     */
    public int merge(Scene scene) {
        log.warn("Not implemented");

        LinkedList<LEDLightDRGB> lights1 = new LinkedList<>(), lights2 = new LinkedList<>();
        String name1, name2 = "";

        lights1.addAll(this.lights);
        name1 = this.name;
        lights2.addAll(scene.lights);
        name2 = scene.name;

        this.lights.clear();
        this.name = "";

        while (lights1.size() + lights2.size() > 0) {
            if (lights1.size() == 0) {
                this.name += name2;
                this.lights.addAll(lights2);
                log.debug("1st scene added, adding all of 2nd");
                break;
            } else if (lights2.size() == 0) {
                this.name += name1;
                this.lights.addAll(lights1);
                log.debug("2nd scene added, adding all of 1st");
                break;
            }

            if (lights1.peek().dmxAdress < lights2.peek().dmxAdress) {
                this.lights.add(lights1.remove());
                this.name += name1.charAt(0);
                name1 = name1.substring(1, name1.length());
                log.debug("Adding light of 1st list");
            } else {
                this.lights.add(lights2.remove());
                this.name += name2.charAt(0);
                name2 = name2.substring(1, name2.length());
                log.debug("Adding light of 2nd list");
            }
        }
        return this.lights.size();
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
