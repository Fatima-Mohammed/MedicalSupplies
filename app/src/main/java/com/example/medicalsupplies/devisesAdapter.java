package com.example.medicalsupplies;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class devisesAdapter  extends ArrayAdapter<String> {
    private final Context context;
    private final String[] maintitle;
    private final String[] subtitle;
    private final Integer[] imgid;
    interface  Interaction {
        void donate(String position );
    }
    Interaction interaction;
    public devisesAdapter (Context context, Interaction interaction, String[] maintitle,String[] subtitle, Integer[] imgid) {
        super(context, R.layout.donardevises, maintitle);

        // TODO Auto-generated constructor stub
        this.context=context;
        this.maintitle=maintitle;
        this.subtitle=subtitle;
        this.imgid=imgid;
        this.interaction = interaction;
    }
    public View getView(final int position, View view, ViewGroup parent) {
        View rowView = LayoutInflater.from(getContext()).inflate(R.layout.donardevises, null, true);
        TextView titleText = (TextView) rowView.findViewById(R.id.title1);
        ImageView imageView = (ImageView) rowView.findViewById(R.id.icon1);
        TextView subtitleText = (TextView) rowView.findViewById(R.id.subtitle1);
        Button buClick = (Button) rowView.findViewById(R.id.budonate);
        buClick.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                    interaction.donate(maintitle[position]);
                    }
                }
        );
        titleText.setText(maintitle[position]);
        imageView.setImageResource(imgid[position]);
        subtitleText.setText(subtitle[position]);
        return rowView;
    }}