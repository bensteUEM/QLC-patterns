package stein.team.qlc.model;

public class FixtureValue {
    Integer fixtureID;

    int dim, red, green, blue; //Using in instead of byte for convinience
    Boolean applyDim, applyRed, applyGreen, applyBlue;

    /**
     * Create new empty fixture values
     */
    public FixtureValue() {
        applyDim = false;
        applyRed = false;
        applyGreen = false;
        applyBlue = false;
        dim = 0;
        red = 0;
        green = 0;
        blue = 0;
    }

    /**
     * Create new fixture values with applicable defined values
     *
     * @param dim   value from 0 to 255
     * @param red   value from 0 to 255
     * @param green value from 0 to 255
     * @param blue  value from 0 to 255
     */
    public FixtureValue(int dim, int red, int green, int blue) {
        applyDim = true;
        applyRed = true;
        applyGreen = true;
        applyBlue = true;

        this.dim = dim;
        this.red = red;
        this.green = green;
        this.blue = blue;
    }

    @Override
    public String toString() {
        return "{" + dim + "|" + red + "|" + green + "|" + blue + "}";
    }
}
