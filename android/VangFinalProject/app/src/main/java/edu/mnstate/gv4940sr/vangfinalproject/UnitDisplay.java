package edu.mnstate.gv4940sr.vangfinalproject;

/**
 * Created by Hoopa on 12/10/2017.
 */

public class UnitDisplay
{
    public UnitDisplay(String name, int imageID) {
        this.name = name;
        this.imageID = imageID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String name;

    public int getImageID() {
        return imageID;
    }

    public void setImageID(int imageID) {
        this.imageID = imageID;
    }

    private int imageID;

}
