package com.example.blde.simpleapp;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import static com.example.blde.simpleapp.R.layout.activity_sample;

public class Sample extends AppCompatActivity {
    Button alert,notification,toast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(activity_sample);

        alert= (Button) findViewById(R.id.alert);
        notification= (Button) findViewById(R.id.notification);
        toast= (Button) findViewById(R.id.toast);

        alert.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View view){
                AlertDialog.Builder builder=new AlertDialog.Builder(Sample.this);
                builder.setTitle("Alert Dailog Box");
                builder.setMessage("Are you sure ,you want delete");
                builder.setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                        Toast.makeText(Sample.this, "you clicked ok button", Toast.LENGTH_LONG).show();
                        dialog.dismiss();

                    }
                });
                AlertDialog alertDialog=builder.create();
                alertDialog.show();
            }
        });

        toast.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view){
                Toast toast=new Toast(Sample.this);
                View View= LayoutInflater.from(Sample.this).inflate(R.layout.item_view,(ViewGroup) findViewById(R.id.sample));
                toast.setView(View);
                toast.show();

            }
        });

    }
}
