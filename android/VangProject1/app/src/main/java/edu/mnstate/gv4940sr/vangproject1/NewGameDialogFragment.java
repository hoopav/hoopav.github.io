package edu.mnstate.gv4940sr.vangproject1;

import android.app.AlertDialog;
import android.app.Dialog;
import android.media.MediaPlayer;
import android.support.v4.app.DialogFragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.widget.Toast;

import static edu.mnstate.gv4940sr.vangproject1.MainActivity.m;

/**
 * Created by Hoopa on 10/16/2017.
 */

public class NewGameDialogFragment  extends DialogFragment {
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage(R.string.newGameTextPrompt);
        builder.setPositiveButton(R.string.yesText, new DialogInterface.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                m=MediaPlayer.create(getContext(),R.raw.dw1clickse);
                m.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    public void onCompletion(MediaPlayer mp) {
                        mp.release();

                    }});
                m.start();
                Intent menu=new Intent(getContext(),MenuActivity.class);
                startActivity(menu);
            }
        });
        builder.setNegativeButton(R.string.noText, new DialogInterface.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                m=MediaPlayer.create(getContext(),R.raw.dw1clickse);

                m.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    public void onCompletion(MediaPlayer mp) {
                        mp.release();

                    }});
                m.start();
                Toast.makeText(getContext(), R.string.dialogInterfaceDeclineText,Toast.LENGTH_LONG).show();
            }
        });

        return builder.create();
    }
}