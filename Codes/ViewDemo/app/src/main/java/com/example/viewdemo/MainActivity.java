package com.example.viewdemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {
    TextView txtViewWelcomeMsg;
    ImageView imgViewSample;
    Button btnShowTextOrImage,btnShowBoth;
    Boolean bigger=false;

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

        txtViewWelcomeMsg.setOnTouchListener(
                new CustomTouchListener(MainActivity.this){
                    @Override
                    public void onUpSwipe() {
                        super.onUpSwipe();
                        int horGravity=txtViewWelcomeMsg.getGravity()&
                                Gravity.HORIZONTAL_GRAVITY_MASK;
                        txtViewWelcomeMsg.setGravity(horGravity |Gravity.TOP );
                    }

                    @Override
                    public void onDownSwipe() {
                        super.onDownSwipe();
                        int horGravity=txtViewWelcomeMsg.getGravity()&
                                Gravity.HORIZONTAL_GRAVITY_MASK;
                        txtViewWelcomeMsg.setGravity(horGravity |Gravity.BOTTOM );
                    }

                    @Override
                    public void onLeftSwipe() {
                        super.onLeftSwipe();
                        int vertGravity=txtViewWelcomeMsg.getGravity()&
                                Gravity.VERTICAL_GRAVITY_MASK;
                        txtViewWelcomeMsg.setGravity(vertGravity |Gravity.LEFT );
                    }

                    @Override
                    public void onRightSwipe() {
                        super.onRightSwipe();
                        int vertGravity=txtViewWelcomeMsg.getGravity()&
                                Gravity.VERTICAL_GRAVITY_MASK;
                        txtViewWelcomeMsg.setGravity(vertGravity |Gravity.RIGHT );
                    }

                    @Override
                    public void onDoubleClick() {
                        super.onDoubleClick();
                        if(!bigger){
                            txtViewWelcomeMsg.setTextSize(
                                    txtViewWelcomeMsg.getTextSize()/
                                            getResources().getDisplayMetrics().density+10
                            );
                        }
                        else {
                            txtViewWelcomeMsg.setTextSize(
                                    txtViewWelcomeMsg.getTextSize()/
                                            getResources().getDisplayMetrics().density-10
                            );
                        }
                        bigger=!bigger;
                    }

                    @Override
                    public void onSingleClick() {
                        super.onSingleClick();
                        if (txtViewWelcomeMsg.getCurrentTextColor() !=
                                ResourcesCompat.getColor(getResources(),R.color.teal_200,getTheme())){
                            txtViewWelcomeMsg.setTextColor(
                                    ResourcesCompat.getColor(getResources(),R.color.teal_200,getTheme()));
                        } else {
                            txtViewWelcomeMsg.setTextColor(Color.rgb(255,255,255));
                        }

                    }

                    @Override
                    public void onLongClick() {
                        super.onLongClick();
                        txtViewWelcomeMsg.setPaintFlags(
                                txtViewWelcomeMsg.getPaintFlags() ^
                                        Paint.STRIKE_THRU_TEXT_FLAG
                        );
                    }

                    @Override
                    public boolean onTouch(View view, MotionEvent motionEvent) {
                        return super.onTouch(view, motionEvent);
                    }
                });

        imgViewSample.setOnTouchListener(new CustomTouchListener(this){
            @Override
            public void onDoubleClick() {
                super.onDoubleClick();
                if(imgViewSample.getScaleType()!=ImageView.ScaleType.FIT_XY)
                    imgViewSample.setScaleType(ImageView.ScaleType.FIT_XY);
                else
                    imgViewSample.setScaleType(ImageView.ScaleType.FIT_CENTER);
            }

            @Override
            public void onSingleClick() {
                super.onSingleClick();
                Drawable birdImage = ResourcesCompat.getDrawable(
                        getResources(),R.drawable.bird,getTheme());
                Objects.requireNonNull(birdImage);
                if(imgViewSample.getDrawable().getConstantState() !=
                        birdImage.getConstantState())
                    imgViewSample.setImageResource(R.drawable.bird);
                else
                    imgViewSample.setImageResource(R.drawable.fire);
            }

            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                return super.onTouch(view, motionEvent);
            }
        });
    }
}