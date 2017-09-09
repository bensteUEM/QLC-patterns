package stein.team.qlc.model;

import org.apache.log4j.Logger;
import stein.team.qlc.helper.Helper;

import java.util.ArrayList;
import java.util.List;

/**
 * A Chaser is a series of Scenes
 */
public class Chaser {
    private static final Logger log = Logger.getLogger(Chaser.class);
    public List<Scene> scenes;
    Integer fadeIn, fadeOut, duration, id; //TODO did not check datatype
    Boolean forwardDirection;
    ChaserRunOrder runOrder;
    String name, path;


    /**
     * Minimalistic Constructor for a function with default params
     *
     * @param id   which is unique to the project
     * @param name human readable name
     */
    public Chaser(Integer id, String name, List<Scene> scenes) {
        this.id = id;
        this.scenes = scenes;

        this.forwardDirection = true;
        this.runOrder = ChaserRunOrder.LOOP;

        this.name = name;
        this.path = "";

        this.fadeIn = 0;
        this.fadeOut = 0;
        this.duration = 0;
    }

    /**
     * This should merge multiple chaser steps into one pattern
     * if the existing one has a different number of steps the smallest possible repetition is created
     * e.g. exists only 1 step, adding 3 step, exsting one is copied to exist 3 times and only then merged.
     *
     * @param morescenes a list of scenes which should be added of NON OVERLAPPING lights, in order
     */
    public void merge(List<Scene> morescenes) {
        log.warn("check for duplicate lights?"); //TODO

        log.debug("GCD of to be merged scenes is " + Helper.lcm(this.scenes.size(), morescenes.size()) +
                " with existing " + this.scenes.size() + " add: " + morescenes.size());
        if (this.scenes.size() != morescenes.size()) {
            int lcm = Helper.lcm(this.scenes.size(), morescenes.size());

            List<Scene> templist = new ArrayList<>();
            while (templist.size() < lcm) {
                templist.addAll(this.scenes);
            }
            this.scenes = templist;

            templist = new ArrayList<>();
            while (templist.size() < lcm) {
                templist.addAll(morescenes);
            }
            morescenes = templist;
        }

        for (int i = 0; i < this.scenes.size(); i++) {
            this.scenes.get(i).merge(morescenes.get(i));
        }
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append("Chaser with name " + this.name)
                .append(" with ID=" + id)
                .append(" in Path=" + path)
                .append(" WITH FadeIn/Out/Duration of " + fadeIn + ";" + fadeOut + ";" + duration)
                .append(" AND RunOrder: " + (this.forwardDirection ? "Forward" : "Backward)"));
        result.append(scenes.toString());
        return result.toString();
    }

    /*
      <Function ID="229" Type="Chaser" Name="&gt;" Path="15 Uhr GoDi/DIM Move Accent Pattern">
   <Speed FadeIn="0" FadeOut="0" Duration="1000"/>
   <Direction>Forward</Direction>
   <ChaserRunOrder>Loop</ChaserRunOrder>
   <SpeedModes FadeIn="Default" FadeOut="Default" Duration="Common"/>
   <Step Number="0" FadeIn="0" Hold="0" FadeOut="0">225</Step>
   <Step Number="1" FadeIn="0" Hold="0" FadeOut="0">226</Step>
   <Step Number="2" FadeIn="0" Hold="0" FadeOut="0">230</Step>
   <Step Number="3" FadeIn="0" Hold="0" FadeOut="0">231</Step>
   <Step Number="4" FadeIn="0" Hold="0" FadeOut="0">232</Step>
   <Step Number="5" FadeIn="0" Hold="0" FadeOut="0">233</Step>
   <Step Number="6" FadeIn="0" Hold="0" FadeOut="0">234</Step>
   <Step Number="7" FadeIn="0" Hold="0" FadeOut="0">235</Step>
  </Function>
     */
}
