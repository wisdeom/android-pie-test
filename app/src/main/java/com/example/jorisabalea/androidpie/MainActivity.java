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

public class MainActivity extends AppCompatActivity {
    CoordinatorLayout layout;
    ImageView image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        image = findViewById(R.id.image);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                try {
                    decodeImage();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });

    }

    private void decodeImage() throws IOException {
        Drawable decodedAnimation = ImageDecoder.decodeDrawable(
                ImageDecoder.createSource(getResources(), R.drawable.tap));
        if (decodedAnimation instanceof AnimatedImageDrawable) {
            // Prior to start(), the first frame is displayed.
            ((AnimatedImageDrawable) decodedAnimation).start();
        }
        image.setImageDrawable(decodedAnimation);
    }

}
