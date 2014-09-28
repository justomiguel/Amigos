package com.amigos;

import android.app.Activity;
import android.app.FragmentManager;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.amigos.fragments.AmigosDialog;

import java.util.Timer;
import java.util.TimerTask;


public class DescriptionActivity extends FragmentActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_description);

        getActionBar().setDisplayHomeAsUpEnabled(true);

        ImageView imageView = (ImageView) findViewById(R.id.imageViewPlaces);
        imageView.setBackgroundResource(R.drawable.frame_animation);
        AnimationDrawable animationDrawable = (AnimationDrawable) imageView.getBackground();
        animationDrawable.start();

        ImageView imageViewRes = (ImageView) findViewById(R.id.imageViewResta);
        imageViewRes.setBackgroundResource(R.drawable.frame_animation_resta);
        final AnimationDrawable animationDrawableResta = (AnimationDrawable) imageViewRes.getBackground();


        Timer timer = new Timer();

        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                animationDrawableResta.start();
            }
        }, 1500);

        Button payButton = (Button)findViewById(R.id.buttonOk);

        payButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                payButton();
            }
        });



//        ImageView imageView = (ImageView) findViewById(R.id.imageViewPlaces);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.description, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        switch (id)
        {
            case R.id.action_settings:
                break;
            case android.R.id.home:
                finish();
                break;

        }

        return super.onOptionsItemSelected(item);
    }


    private void payButton(){

        android.support.v4.app.FragmentManager fragmentManager = getSupportFragmentManager();
        AmigosDialog amigosDialog = AmigosDialog.newInstance();
        amigosDialog.show(fragmentManager,"amigos_fragment");


    }
}
