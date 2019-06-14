package edu.mnstate.gv4940sr.vangproject1;

import android.content.Context;
import android.graphics.Typeface;
import android.media.Image;
import android.net.Uri;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashMap;


/**
 * Created by Hoopa on 10/6/2017.
 */

public class PlayerAdapter extends BaseAdapter {
    private Context mContext;
    private LayoutInflater mInflater;
    private ArrayList<Player> mDataSource;
    private static final HashMap<String, Integer> LABEL_COLORS = new HashMap<String, Integer>() {{
        put("EX", R.color.colorRankEx);
        put("A", R.color.colorRankA);
    }};

    public PlayerAdapter(Context context, ArrayList<Player> items)
    {

        mContext=context;
        mDataSource=items;
        mInflater=(LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }


    public void clear()
    {
        mDataSource.clear();

    }

    public void addAll(ArrayList<Player> classes)
    {
        for (Player p:classes)
        {
            mDataSource.add(p);
        }
    }

    @Override
    public int getCount() {
        return mDataSource.size();
    }

    @Override
    public Object getItem(int i) {
        return mDataSource.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View rowView=mInflater.inflate(R.layout.selectclasslistview, viewGroup,false);

        TextView playerName=(TextView) rowView.findViewById(R.id.playerNameTextView);
        TextView playerDesc=(TextView) rowView.findViewById(R.id.playerDescTextView);
        ImageView img=(ImageView) rowView.findViewById(R.id.playerImageView);
        TextView playerRank=(TextView) rowView.findViewById(R.id.playerRankTextView);
        TextView hpTv=(TextView) rowView.findViewById(R.id.hpTextView);
        TextView mpTv=(TextView) rowView.findViewById(R.id.mpTextView);
        TextView atkTv=(TextView) rowView.findViewById(R.id.atkTextView);
        TextView defTv=(TextView) rowView.findViewById(R.id.defTextView);
        Player player=(Player) getItem(i);
        playerName.setText(player.getName());
        playerDesc.setText(player.getDescription());
        playerRank.setText("Ability - "+player.getAbility());
        img.setContentDescription(player.getImageUrl());
        hpTv.setText("HP: "+player.hp);
        mpTv.setText("MP: "+player.mp);
        atkTv.setText("ATK: "+player.atk);
        defTv.setText("DEF:"+player.def);


        Picasso.Builder builder = new Picasso.Builder(mContext);
        builder.listener(new Picasso.Listener()
        {

            @Override
            public void onImageLoadFailed(Picasso picasso, Uri uri, Exception exception)
            {
                exception.printStackTrace();
            }
        });

        builder.build().load(player.getImageUrl()).into(img);

        playerName.setTextColor(ContextCompat.getColor(mContext,R.color.colorText));


        playerDesc.setTextColor(ContextCompat.getColor(mContext,R.color.colorText));
        playerRank.setTextColor(ContextCompat.getColor(mContext,R.color.colorText));

        hpTv.setTextColor(ContextCompat.getColor(mContext,R.color.colorText));
        mpTv.setTextColor(ContextCompat.getColor(mContext,R.color.colorText));
        atkTv.setTextColor(ContextCompat.getColor(mContext,R.color.colorText));
        defTv.setTextColor(ContextCompat.getColor(mContext,R.color.colorText));
        return rowView;
    }


}
