package com.srkakadesir.calendarteachers;

import androidx.appcompat.app.AppCompatActivity;

import android.media.Image;
import android.os.Bundle;
import android.widget.ImageView;

import com.github.chrisbanes.photoview.PhotoView;

public class ShowImageActivity extends AppCompatActivity {

    PhotoView iv_image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_image);

        iv_image = findViewById(R.id.iv_show_image_activity);
        iv_image.setImageResource(R.drawable.header_main);


    }
}
