package com.example.jorisabalea.androidpie;

import android.graphics.ImageDecoder;
import android.graphics.drawable.AnimatedImageDrawable;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.PrecomputedText;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.lang.ref.WeakReference;

import com.microsoft.appcenter.AppCenter;
import com.microsoft.appcenter.analytics.Analytics;
import com.microsoft.appcenter.crashes.Crashes;
import com.microsoft.appcenter.distribute.Distribute;

public class MainActivity extends AppCompatActivity {
    CoordinatorLayout layout;
    ImageView image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        // UI elements
        Toolbar toolbar = findViewById(R.id.toolbar);
        image = findViewById(R.id.image);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = findViewById(R.id.fab);
        
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    decodeImage();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }    
        
        //Initialize SDK
        if (!BuildConfig.APPCENTER_APP_SECRET.equals("")) {
            // Use APPCENTER_APP_SECRET environment variable if it exists
            AppCenter.start(getApplication(), BuildConfig.APPCENTER_APP_SECRET,
                    Analytics.class, Crashes.class, Distribute.class);
        } else {
            // Otherwise use the hardcoded string value here
            AppCenter.start(getApplication(), "5448823f-4467-47f2-8842-c347fc6c930e",
                    Analytics.class, Crashes.class, Distribute.class);
        } 

        if (BuildConfig.DEBUG) {
            AppCenter.setLogLevel(Log.VERBOSE);
        }
       
            
        }); //onCreate

    } //MainActivity

    private void decodeImage() throws IOException {
        Drawable decodedAnimation = ImageDecoder.decodeDrawable(
                ImageDecoder.createSource(getResources(), R.drawable.pie));
        if (decodedAnimation instanceof AnimatedImageDrawable) {
            ((AnimatedImageDrawable) decodedAnimation).start();
        }
        image.setImageDrawable(decodedAnimation);
    }

}
