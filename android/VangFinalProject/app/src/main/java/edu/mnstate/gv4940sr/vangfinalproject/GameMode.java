package edu.mnstate.gv4940sr.vangfinalproject;

/**
 * Created by Hoopa on 12/11/2017.
 */

public class GameMode
{


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getImageID() {
        return ImageID;
    }

    public void setImageID(int imageID) {
        ImageID = imageID;
    }

    private String name;

    public GameMode(String name, int imageID) {
        this.name = name;
        ImageID = imageID;
    }

    private int ImageID;
}
