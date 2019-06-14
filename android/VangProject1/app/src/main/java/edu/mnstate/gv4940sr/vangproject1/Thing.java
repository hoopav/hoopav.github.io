package edu.mnstate.gv4940sr.vangproject1;

import java.lang.reflect.Field;

/**
 * Created by Hoopa on 10/14/2017.
 */

class Thing //a thing that has stats
{
    public String hp,mp,atk,def;
    Class thing=Thing.class;
    public Field[] fields=thing.getFields();



}
