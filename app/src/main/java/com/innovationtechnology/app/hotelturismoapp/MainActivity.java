package com.innovationtechnology.app.hotelturismoapp;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
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

        Button hospedar = (Button) findViewById(R.id.hospedar);
        assert hospedar != null;
        hospedar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), HostingActivity.class));
                overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
            }
        });
    }
}
