package com.example.blde.simpleapp;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by BLDE on 7/28/2018.
 */

public class item extends RecyclerView.ViewHolder {
    ImageView image;
    TextView name;
    TextView DOB;

    public item(View itemView){
        super(itemView);
        image= (ImageView) itemView.findViewById(R.id.image);
        name= (TextView) itemView.findViewById(R.id.name);
        DOB= (TextView) itemView.findViewById(R.id.dob);

    }

}
