package com.example.medicalsupplies;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


public class MyListAdapter  extends ArrayAdapter<String>{

    private final Context context;
    private final String[] maintitle;
    private final String[] subtitle;
    private final Integer[] imgid;
    interface  Interaction2 {
        void Req(String position);
    }
     Interaction2 interaction2;
    public MyListAdapter(Context context, MyListAdapter.Interaction2 interaction2, String[] maintitle, String[] subtitle, Integer[] imgid) {
        super(context, R.layout.beneficiaredevises, maintitle);

        // TODO Auto-generated constructor stub

        this.context=context;
        this.maintitle=maintitle;
        this.subtitle=subtitle;
        this.imgid=imgid;
        this.interaction2 = interaction2;
    }

    public View getView(final int position,View view,ViewGroup parent) {
       // LayoutInflater inflater = context.getLayoutInflater();
        View rowView = LayoutInflater.from(getContext()).inflate(R.layout.beneficiaredevises, null, true);

        TextView titleText = (TextView) rowView.findViewById(R.id.title);
        ImageView imageView = (ImageView) rowView.findViewById(R.id.icon);
        TextView subtitleText = (TextView) rowView.findViewById(R.id.subtitle);
        Button buClick = (Button) rowView.findViewById(R.id.buReq);
       // ماحطيت اون كلك عشان ينقلني لقاعدة البيانات
        buClick.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        interaction2.Req(maintitle[position]);
                    }
                }
        );

        titleText.setText(maintitle[position]);
        imageView.setImageResource(imgid[position]);
        subtitleText.setText(subtitle[position]);

        return rowView;

    }
}

