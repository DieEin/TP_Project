package com.example.tpproject.project;

import android.app.Activity;
import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Tomi on 15.5.2016 г..
 */
public class MyAdapter extends ArrayAdapter<String> {

    //ImageView theImageView;

    private final Context context;
    private final List<String> values;
    private final List<Integer> imageId;

    public MyAdapter(Context context, List<String> values, List<Integer> imageId) {
        super(context, R.layout.row_layout_2, values);
        this.context = context;
        this.values = values;
        this.imageId = imageId;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater theInflater = LayoutInflater.from(getContext());

        View theView = theInflater.inflate(R.layout.row_layout_2, parent, false);

        String user = getItem(position);

        TextView theTextView = (TextView) theView.findViewById(R.id.theTextView1);
        theTextView.setText(user);

        ImageView theImageView = (ImageView) theView.findViewById(R.id.rankings_imageView);
        theImageView.setImageResource(imageId.get(position));

        /*
        if (Rankings.logged == 1) {
            theImageView.setImageResource(R.drawable.logged_in);
        } else {
            theImageView.setImageResource(R.drawable.logged_out);
        }*/

        return theView;
    }
}
