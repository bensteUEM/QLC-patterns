package team.stein.qlc.view;

import org.apache.log4j.Logger;
import team.stein.qlc.model.*;

import java.util.ArrayList;
import java.util.List;

/**
 * A QLC Function is an element for export which can contain e.g. Chaser or Scene CAN NOT HOLD Script
 */
public class QLCFunction {
    private static final Logger log = Logger.getLogger(QLCFunction.class);
    boolean isChaser;
    private Function internalFunction;

    public QLCFunction(Scene scene) {
        this.isChaser = false;
        this.internalFunction = scene;
    }

    public QLCFunction(Chaser chaser) {
        this.isChaser = true;
        this.internalFunction = chaser;
    }

    @Override
    public String toString() {
        String type = (this.isChaser) ? "Chaser" : "Scene";
        return type + " ID=" + this.internalFunction.getID();
    }

    public String toQXWString() {
        StringBuilder qxwText = new StringBuilder();
        qxwText.append("\n\t\t<Function ID=\"").append(this.internalFunction.getID()).append("\" Type=\"");

        qxwText.append((isChaser ? "Chaser" : "Scene")).append("\"");

        qxwText.append(" Name=\"").append(this.internalFunction.getName()).append("\"")
                .append(" Path=\"").append(this.internalFunction.getPath()).append("\"")
                .append(">");
        qxwText.append("\n\t\t\t<Speed FadeIn=\"").append(this.internalFunction.getFadeIn()).append("\"")
                .append(" FadeOut=\"").append(this.internalFunction.getFadeOut()).append("\"")
                .append(" Duration=\"").append(this.internalFunction.getDuration()).append("\"")
                .append("/>");

        if (this.internalFunction instanceof Chaser) {
            Chaser chaser = (Chaser) internalFunction;
            qxwText.append("\n\t\t\t<Direction>" + ((chaser.forwardDirection) ? "Forward" : "Backward") + "</Direction>");
            qxwText.append("\n\t\t\t<ChaserRunOrder>");

            switch (chaser.runOrder) {
                case LOOP:
                    qxwText.append("Loop");
                    break;
                case SINGLE_SHOT:
                    qxwText.append("Single Shot");
                    break;
                case PING_PONG:
                    qxwText.append("Ping Pong");
                    break;
                case RANDOM:
                    qxwText.append("Random");
                    break;
            }
            qxwText.append("</ChaserRunOrder>");

            qxwText.append("\n\t\t\t<SpeedModes FadeIn=\"Default\" FadeOut=\"Default\" Duration=\"Common\"/>");

            for (int i = 0; i < chaser.scenes.size(); i++) {
                qxwText.append("\n\t\t\t<Step Number=\"").append(i).append("\"")
                        .append(" FadeIn=\"").append(chaser.scenes.get(i).getFadeIn()).append("\"")
                        .append(" Hold=\"").append(0).append("\"")
                        .append(" FadeOut=\"").append(chaser.scenes.get(i).getFadeOut()).append("\">")
                        .append(chaser.scenes.get(i).getID())
                        .append("</Step>");
            }
        } else if (this.internalFunction instanceof Scene) {
            Scene scene = (Scene) internalFunction;
            /* <ChannelGroupsVal>12,255,10,255,11,255</ChannelGroupsVal>*/
            log.info("If this Scene also contains ChannelGroupsVal they are not exported");

            for (int i = 0; i < scene.lights.size(); i++) {
                LEDLightDRGB light = scene.lights.get(i);
                FixtureValue fixtureValue = light.fixtureValue;

                if (fixtureValue.anyApplies()) { //TODO for some reason this is always 0 #2
                    qxwText.append("\n\t\t\t<FixtureVal ID=\"").append(light.qlcID).append("\">");

                    List<String> values = new ArrayList<>();
                    if (fixtureValue.applyDim) {
                        values.add("0");
                        values.add(Integer.toString(fixtureValue.dim));
                    }
                    if (fixtureValue.applyRed) {
                        values.add("1");
                        values.add(Integer.toString(fixtureValue.red));
                    }
                    if (fixtureValue.applyGreen) {
                        values.add("2");
                        values.add(Integer.toString(fixtureValue.green));
                    }
                    if (fixtureValue.applyBlue) {
                        values.add("3");
                        values.add(Integer.toString(fixtureValue.blue));
                    }
                    qxwText.append(String.join(",", values));
                    qxwText.append("</FixtureVal>");
                }
            }
        }

        qxwText.append("\n\t\t</Function>");

        return qxwText.toString();
    }
}
