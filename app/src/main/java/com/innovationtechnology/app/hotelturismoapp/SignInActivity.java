package com.innovationtechnology.app.hotelturismoapp;

import android.content.Context;
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

import static com.innovationtechnology.app.hotelturismoapp.Constants.CONSTRASEÑA;
import static com.innovationtechnology.app.hotelturismoapp.Constants.EMAIL;
import static com.innovationtechnology.app.hotelturismoapp.Constants.NOMBRE;
import static com.innovationtechnology.app.hotelturismoapp.Constants.NOMBRE_USUARIO;
import static com.innovationtechnology.app.hotelturismoapp.Constants.TELEFONO;

public class SignInActivity extends AppCompatActivity {

    private EditText nombre;
    private EditText contraseña;
    private EditText nombreUsuario;
    private EditText telefono;
    private EditText email;
    private SharedPreferences preferences;
    private SharedPreferencesUtil preferencesUtil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        Util.setImmersiveMode(this);

        ParallaxImageView parallaxImageView = (ParallaxImageView) findViewById(R.id.background);
        assert parallaxImageView != null;
        parallaxImageView.setImageBitmap(NativeStackBlur.process(BitmapFactory.decodeResource(getResources(), R.drawable.ic_fondo_d), 25));

        nombre = (EditText) findViewById(R.id.nombre);
        contraseña = (EditText) findViewById(R.id.contraseña);
        nombreUsuario = (EditText) findViewById(R.id.nombre_usuario);
        telefono = (EditText) findViewById(R.id.telefono);
        email = (EditText) findViewById(R.id.email);

        preferences = getSharedPreferences(getPackageName(), Context.MODE_PRIVATE);
        preferencesUtil = new SharedPreferencesUtil(this);

        Button registrar = (Button) findViewById(R.id.registrar);
        assert registrar != null;
        registrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isEmpty(nombre) || isEmpty(contraseña) || isEmpty(nombreUsuario) || isEmpty(telefono) || isEmpty(email)) {
                    Toast.makeText(getApplicationContext(), "Hay campos vacios", Toast.LENGTH_SHORT).show();
                } else {
                    preferencesUtil.putValue(preferences, NOMBRE + nombre.getText(), nombre.getText().toString());
                    preferencesUtil.putValue(preferences, CONSTRASEÑA + nombre.getText(), contraseña.getText().toString());
                    preferencesUtil.putValue(preferences, NOMBRE_USUARIO + nombre.getText(), nombreUsuario.getText().toString());
                    preferencesUtil.putValue(preferences, TELEFONO + nombre.getText(), telefono.getText().toString());
                    preferencesUtil.putValue(preferences, EMAIL + nombre.getText(), email.getText().toString());

                    Log.d("Login", NOMBRE + nombre.getText() + " - " + preferencesUtil.getString(preferences, NOMBRE + nombre.getText(), ""));

                    Toast.makeText(getApplicationContext(), "Registro existoso", Toast.LENGTH_SHORT).show();

                    overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                    finish();
                }
            }
        });
    }

    public boolean isEmpty(EditText editText) {
        return editText.length() == 0;
    }
}
