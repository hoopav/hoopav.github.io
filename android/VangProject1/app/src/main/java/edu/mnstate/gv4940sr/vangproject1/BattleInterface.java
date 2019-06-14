package edu.mnstate.gv4940sr.vangproject1;

/**
 * Created by Hoopa on 10/17/2017.
 */

public interface BattleInterface {
    public void attack(Thing m);
    public void defend();
    public void ability(Thing self, Thing target);
}
