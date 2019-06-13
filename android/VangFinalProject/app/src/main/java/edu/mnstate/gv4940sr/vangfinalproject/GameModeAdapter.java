package edu.mnstate.gv4940sr.vangfinalproject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Hoopa on 12/11/2017.
 */

public class GameModeAdapter extends RecyclerView.Adapter<GameModeAdapter.GameModeViewHolder>{


    private Activity parent;

    public GameModeAdapter(List<GameMode> gameModeRoster, Activity parent)
    {
        this.parent=parent;

    }

    @Override
    public GameModeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflatedVw= LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_view_row_game_modes,parent,false);
        return new GameModeViewHolder(inflatedVw);
    }

    @Override
    public void onBindViewHolder(GameModeViewHolder holder, int position) {
        GameMode gm=HomeScreen.gameModeRoster.get(position);
        String name=gm.getName();
        int imageID=gm.getImageID();

        holder.gameModeImage.setImageResource(imageID);
        holder.gameModeImage.setTag(imageID);
        holder.gameModeName.setText(name);
    }

    @Override
    public int getItemCount() {
        return HomeScreen.gameModeRoster.size();
    }

    public class GameModeViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView gameModeName;
        ImageView gameModeImage;
        public GameModeViewHolder(View itemView) {
            super(itemView);
            gameModeName=itemView.findViewById(R.id.game_mode_name);
            gameModeImage=itemView.findViewById(R.id.game_mode_picture);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Bundle b=new Bundle();
            b.putString("GAMEMODENAME",gameModeName.getText().toString());
            b.putInt("GAMEMODEIMAGE",(Integer)gameModeImage.getTag());
            Intent intent=new Intent(parent,GameModeActivity.class);
            intent.putExtras(b);
            parent.startActivity(intent);
            Log.d("debug","CLICKED!");
        }
    }
}
