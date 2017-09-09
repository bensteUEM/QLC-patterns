package stein.team.qlc.view;

import org.apache.log4j.Logger;
import stein.team.qlc.model.Chaser;
import stein.team.qlc.model.Scene;

/**
 * A QLC Function is an element for export which can contain e.g. Chaser or Scene
 */
class QLCFunction {
    private static final Logger log = Logger.getLogger(QLCFunction.class);

    /**
     * DIM VALUE
     * <Function ID="235" Type="Scene" Name="--- -- --X" Path="15Uhr Godi/DIM Move Accent Pattern">
     * <Speed FadeIn="0" FadeOut="0" Duration="0"/>
     * <ChannelGroupsVal>9,0</ChannelGroupsVal>
     * <FixtureVal ID="29">0,0</FixtureVal>
     * <FixtureVal ID="30">0,0</FixtureVal>
     * <FixtureVal ID="31">0,0</FixtureVal>
     * <FixtureVal ID="32">0,0</FixtureVal>
     * <FixtureVal ID="33">0,0</FixtureVal>
     * <FixtureVal ID="34">0,0</FixtureVal>
     * <FixtureVal ID="35">0,0</FixtureVal>
     * <FixtureVal ID="36">0,255</FixtureVal>
     * </Function>
     * <p>
     * <p>
     * <p>
     * COLOR VALUE
     * <Function ID="239" Type="Scene" Name="XXXX XXXX white" Path="15Uhr Godi/Color Static">
     * <Speed FadeIn="0" FadeOut="0" Duration="0"/>
     * <ChannelGroupsVal>12,255,10,255,11,255</ChannelGroupsVal>
     * <FixtureVal ID="29">1,255,2,255,3,255</FixtureVal>
     * <FixtureVal ID="30">1,255,2,255,3,255</FixtureVal>
     * <FixtureVal ID="31">1,255,2,255,3,255</FixtureVal>
     * <FixtureVal ID="32">1,255,2,255,3,255</FixtureVal>
     * <FixtureVal ID="33">1,255,2,255,3,255</FixtureVal>
     * <FixtureVal ID="34">1,255,2,255,3,255</FixtureVal>
     * <FixtureVal ID="35">1,255,2,255,3,255</FixtureVal>
     * <FixtureVal ID="36">1,255,2,255,3,255</FixtureVal>
     * </Function>
     */
    public QLCFunction(Scene scene) {
        log.warn("not yet implemented export");
    }


    /**
     * CHASER WITH LINK TO SCENES
     * <Function ID="229" Type="Chaser" Name="&gt;" Path="15 Uhr GoDi/DIM Move Accent Pattern">
     * <Speed FadeIn="0" FadeOut="0" Duration="1000"/>
     * <Direction>Forward</Direction>
     * <ChaserRunOrder>Loop</ChaserRunOrder>
     * <SpeedModes FadeIn="Default" FadeOut="Default" Duration="Common"/>
     * <Step Number="0" FadeIn="0" Hold="0" FadeOut="0">225</Step>
     * <Step Number="1" FadeIn="0" Hold="0" FadeOut="0">226</Step>
     * <Step Number="2" FadeIn="0" Hold="0" FadeOut="0">230</Step>
     * <Step Number="3" FadeIn="0" Hold="0" FadeOut="0">231</Step>
     * <Step Number="4" FadeIn="0" Hold="0" FadeOut="0">232</Step>
     * <Step Number="5" FadeIn="0" Hold="0" FadeOut="0">233</Step>
     * <Step Number="6" FadeIn="0" Hold="0" FadeOut="0">234</Step>
     * <Step Number="7" FadeIn="0" Hold="0" FadeOut="0">235</Step>
     * </Function>
     */
    public QLCFunction(Chaser chaser) {
        log.warn("not yet implemented chaser export");
    }


}
