package team.stein.qlc.model;

public interface Function {
    public Integer getID();

    public Integer setID(int newID);

    public Integer getFadeIn();

    public Integer getFadeOut();

    public Integer getDuration();

    public String getName();

    public String getPath();

    void addPathPrefix(String s);
}
