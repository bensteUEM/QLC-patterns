package team.stein.qlc.model;

/**
 * Basic Colorful light definition with
 * Dimmer, Red, Green, Blue
 */
public class LEDLightDRGB {
    @SuppressWarnings("CanBeFinal")
    public Integer qlcID;
    public FixtureValue fixtureValue;
    @SuppressWarnings("CanBeFinal")
    Integer dmxAdress;

    /**
     * Basic light definition
     *
     * @param dmxAdress physical output for reference only
     * @param qlcID     internal ID
     */
    public LEDLightDRGB(Integer dmxAdress, Integer qlcID) {
        this.dmxAdress = dmxAdress;
        this.qlcID = qlcID;
        this.fixtureValue = new FixtureValue();
    }

    /**
     * Creates a new light based on current light and
     *
     * @param fixtureValue to be applied on the new light
     */
    public LEDLightDRGB(LEDLightDRGB light, FixtureValue fixtureValue) {
        this.dmxAdress = light.dmxAdress;
        this.qlcID = light.qlcID;
        this.fixtureValue = new FixtureValue();
        this.applyValues(fixtureValue);
    }

    /**
     * Merges the Values of another Value combination into the current one
     *
     * @param values that should be set if apply is true
     */
    private void applyValues(FixtureValue values) {
        if (values.applyDim) {
            this.fixtureValue.applyDim = true;
            this.fixtureValue.dim = values.dim;
        }
        if (values.applyRed) {
            this.fixtureValue.applyRed = true;
            this.fixtureValue.red = values.red;
        }
        if (values.applyGreen) {
            this.fixtureValue.applyGreen = true;
            this.fixtureValue.green = values.green;
        }
        if (values.applyBlue) {
            this.fixtureValue.applyBlue = true;
            this.fixtureValue.blue = values.blue;
        }
    }

    @Override
    public String toString() {
        return "DMX:" + dmxAdress.toString() + " " + this.fixtureValue.toString();
    }
}
