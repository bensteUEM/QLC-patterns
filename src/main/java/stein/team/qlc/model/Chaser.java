package stein.team.qlc.model;

import java.util.List;

/**
 * A Chaser is a series of Scenes
 */
public class Chaser {
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

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append("Function with name " + this.name)
                .append(" with ID=" + id)
                .append(" in Path=" + path)
                .append(" WITH FadeIn/Out/Duration of " + fadeIn + ";" + fadeOut + ";" + duration)
                .append(" AND RunOrder: " + (this.forwardDirection ? "Forward" : "Backward)"));
        result.append("\n\t" + scenes.toString());
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
