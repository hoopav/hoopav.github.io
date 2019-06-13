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

public class UnitAdapter extends RecyclerView.Adapter<UnitAdapter.UnitViewHolder>
{
    private List<UnitDisplay> unitRoster;
    private Activity parent;


    public UnitAdapter(List<UnitDisplay> unitRoster,Activity parent)
    {
        this.unitRoster=unitRoster;
        this.parent=parent;

    }

    @Override
    public UnitViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflatedVw = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_view_row_unit,parent,false);

        return new UnitViewHolder(inflatedVw);
    }

    @Override
    public void onBindViewHolder(UnitViewHolder holder, int position) {
        UnitDisplay unit=unitRoster.get(position);
        String name=unit.getName();
        int imageID=unit.getImageID();

        holder.unitImage.setImageResource(imageID);
        holder.unitImage.setTag(imageID);
        holder.unitName.setText(name);
        Log.d("debug",holder.unitName.getText().toString());
    }

    @Override
    public int getItemCount() {
        return unitRoster.size();
    }


    public class UnitViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView unitName;
        ImageView unitImage;
        public UnitViewHolder(View itemView) {
            super(itemView);
            unitImage=itemView.findViewById(R.id.unit_picture);
            unitName=itemView.findViewById(R.id.unit_name);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            Bundle b=new Bundle();
            b.putString("NAME",unitName.getText().toString());
            b.putInt("IMAGE",(Integer)unitImage.getTag());
            Intent intent=new Intent(parent,UnitStatusActivity.class);
            intent.putExtras(b);
            parent.startActivity(intent);
            Log.d("debug","CLICKED!");
        }
    }
}
