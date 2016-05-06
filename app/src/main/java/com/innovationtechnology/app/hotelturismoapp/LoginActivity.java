package com.innovationtechnology.app.hotelturismoapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.commit451.nativestackblur.NativeStackBlur;
import com.nvanbenschoten.motion.ParallaxImageView;
import com.roger.missview.library.MissView;

import static com.innovationtechnology.app.hotelturismoapp.Constants.CONSTRASEÑA;
import static com.innovationtechnology.app.hotelturismoapp.Constants.NOMBRE;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText nombre;
    private EditText contraseña;
    private MissView mMissview;
    private SharedPreferences preferences;
    private SharedPreferencesUtil preferencesUtil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Util.setImmersiveMode(this);

        ParallaxImageView parallaxImageView = (ParallaxImageView) findViewById(R.id.background);
        assert parallaxImageView != null;
        parallaxImageView.setImageBitmap(NativeStackBlur.process(BitmapFactory.decodeResource(getResources(), R.drawable.ic_fondo_b), 25));

        preferences = getSharedPreferences(getPackageName(), Context.MODE_PRIVATE);
        preferencesUtil = new SharedPreferencesUtil(this);

        nombre = (EditText) findViewById(R.id.nombre);
        contraseña = (EditText) findViewById(R.id.contraseña);

        Button ingresar = (Button) findViewById(R.id.ingresar);
        Button registrar = (Button) findViewById(R.id.registrar);

        assert ingresar != null;
        ingresar.setOnClickListener(this);

        assert registrar != null;
        registrar.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();

        switch (id) {
            case R.id.ingresar:
                if (nombre.getText().length() == 0 || contraseña.getText().length() == 0) {
                    Toast.makeText(this, "Hay campos vacios", Toast.LENGTH_SHORT).show();
                } else {


                    if (preferences.contains(NOMBRE + nombre.getText())) {
                        String nombreUsuario = preferencesUtil.getString(preferences, NOMBRE + nombre.getText(), "");

                        if (preferencesUtil.getString(preferences, CONSTRASEÑA + nombreUsuario, "").equals(contraseña.getText().toString())) {
                            startActivity(new Intent(this, MainActivity.class));
                            overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
                            finish();
                        } else {
                            Toast.makeText(this, "Contraseña invalida", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Log.d("Login", NOMBRE + nombre.getText() + " - " + preferencesUtil.getString(preferences, NOMBRE + nombre.getText(), ""));

                        Toast.makeText(this, "El usuario no existe", Toast.LENGTH_SHORT).show();
                    }
                }
                break;
            case R.id.registrar:
                startActivity(new Intent(this, SignInActivity.class));
                overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
                break;
        }
    }
}
