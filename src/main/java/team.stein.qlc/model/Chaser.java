package team.stein.qlc.model;

import org.apache.log4j.Logger;
import team.stein.qlc.helper.Helper;

import java.util.ArrayList;
import java.util.List;

/**
 * A Chaser is a series of Scenes
 */
public class Chaser {
    private static final Logger log = Logger.getLogger(Chaser.class);
    private List<Scene> scenes;
    private Integer fadeIn;
    private Integer fadeOut;
    private Integer duration;
    private Integer id; //TODO did not check datatype
    private Boolean forwardDirection;
    private ChaserRunOrder runOrder;
    private String name;
    private String path;


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
     * e.g. exists only 1 step, adding 3 step, existing one is copied to exist 3 times and only then merged.
     *
     * @param moreScenes a list of scenes which should be added of NON OVERLAPPING lights, in order
     */
    public void merge(List<Scene> moreScenes) {
        log.warn("check for duplicate lights?"); //TODO

        log.debug("GCD of to be merged scenes is " + Helper.lcm(this.scenes.size(), moreScenes.size()) +
                " with existing " + this.scenes.size() + " add: " + moreScenes.size());
        if (this.scenes.size() != moreScenes.size()) {
            int lcm = Helper.lcm(this.scenes.size(), moreScenes.size());

            List<Scene> tempList = new ArrayList<>();
            while (tempList.size() < lcm) {
                tempList.addAll(this.scenes);
            }
            this.scenes = tempList;

            tempList = new ArrayList<>();
            while (tempList.size() < lcm) {
                tempList.addAll(moreScenes);
            }
            moreScenes = tempList;
        }

        for (int i = 0; i < this.scenes.size(); i++) {
            this.scenes.get(i).merge(moreScenes.get(i));
        }
    }

    @Override
    public String toString() {
        String result = "Chaser with name " + this.name +
                " with ID=" + id +
                " in Path=" + path +
                " WITH FadeIn/Out/Duration of " + fadeIn + ";" + fadeOut + ";" + duration +
                " AND RunOrder: " + (this.forwardDirection ? "Forward" : "Backward)") +
                scenes.toString();
        return result;
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
