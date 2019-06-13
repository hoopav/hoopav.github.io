package edu.mnstate.gv4940sr.vangfinalproject;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by Hoopa on 11/13/2017.
 */

public class Message {
    public static void message(Context context, String msg)
    {
        Toast.makeText(context,msg, Toast.LENGTH_LONG).show();
    }
}
