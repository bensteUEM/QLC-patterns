package team.stein.qlc.model;

public interface Function {
    public Integer getID();

    public Integer setID(int newID);

    public Integer getFadeIn();

    public Integer getFadeOut();

    public Integer getDuration();

    /**
     * Watch Out for serialization issues ! e.g. < int  "&lt;"
     *
     * @return
     */
    public String getName();

    public String getPath();

    void addPathPrefix(String s);
}
