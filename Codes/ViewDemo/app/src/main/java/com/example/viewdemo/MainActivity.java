package com.example.viewdemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {
    TextView txtViewWelcomeMsg;
    ImageView imgViewSample;
    Button btnShowTextOrImage,btnShowBoth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtViewWelcomeMsg=findViewById(R.id.txtViewWelcomeMsg);
        imgViewSample = findViewById(R.id.imgViewSample);

        Drawable img= ResourcesCompat.getDrawable(getResources()
                ,R.drawable.border,getTheme());
        Objects.requireNonNull(img);
        img.setBounds(0,0,img.getIntrinsicWidth(),img.getIntrinsicHeight());
        txtViewWelcomeMsg.setCompoundDrawables(img,null,img,null);
        txtViewWelcomeMsg.setCompoundDrawablePadding(8);

        btnShowTextOrImage=findViewById(R.id.btnShowTextOrImage);
        btnShowBoth=findViewById(R.id.btnShowBoth);

        btnShowBoth.setOnClickListener((View view)-> {
            txtViewWelcomeMsg.setVisibility(View.VISIBLE);
            imgViewSample.setVisibility(View.VISIBLE);
            }
        );

        btnShowTextOrImage.setOnClickListener((View view)-> {
            if(btnShowTextOrImage.getText().equals("Show Text")){
                txtViewWelcomeMsg.setVisibility(View.VISIBLE);
                imgViewSample.setVisibility(View.INVISIBLE);
                btnShowTextOrImage.setText(getResources().
                        getText(R.string.txtShowImage));
            }
            else
            {
                txtViewWelcomeMsg.setVisibility(View.GONE);
                imgViewSample.setVisibility(View.VISIBLE);
                btnShowTextOrImage.setText(getResources().getText(R.string.txtShowText));
            }
        });
    }
}