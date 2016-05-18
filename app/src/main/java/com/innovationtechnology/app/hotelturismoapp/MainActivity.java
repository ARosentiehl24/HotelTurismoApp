package com.innovationtechnology.app.hotelturismoapp;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.commit451.nativestackblur.NativeStackBlur;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Util.setImmersiveMode(this);

        ImageView background = (ImageView) findViewById(R.id.background);

        assert background != null;
        Bitmap bitmap = ((BitmapDrawable) background.getDrawable()).getBitmap();

        background.setImageBitmap(NativeStackBlur.process(bitmap, 25));


    }
}
