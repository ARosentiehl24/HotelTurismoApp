package com.innovationtechnology.app.hotelturismoapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class HostingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hosting);

        Util.setImmersiveMode(this);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        assert fab != null;
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), MapsActivity.class));
                overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
            }
        });

        ArrayList<String> listaHoteles = new ArrayList<>();
        listaHoteles.add("Hotel 1");
        listaHoteles.add("Hotel 2");
        listaHoteles.add("Hotel 3");
        listaHoteles.add("Hotel 4");
        listaHoteles.add("Hotel 5");

        RecyclerView hoteles = (RecyclerView) findViewById(R.id.hoteles);
        assert hoteles != null;
        hoteles.setAdapter(new HotelAdapter(listaHoteles));
        hoteles.setHasFixedSize(true);
        hoteles.setLayoutManager(new LinearLayoutManager(this));
    }

    public class HotelAdapter extends RecyclerView.Adapter<HotelAdapter.ViewHolder> {

        private ArrayList<String> hoteles;

        public HotelAdapter(ArrayList<String> hoteles) {
            this.hoteles = hoteles;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            Context context = parent.getContext();

            LayoutInflater inflater = LayoutInflater.from(context);

            View files = inflater.inflate(R.layout.hoteles_item_row, parent, false);

            return new ViewHolder(files);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
//            holder.nombre.setText("ssss");
        }

        @Override
        public int getItemCount() {
            return hoteles.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {

            private TextView nombre;

            public ViewHolder(View itemView) {
                super(itemView);

                nombre = (TextView) findViewById(R.id.nombreHotel);
            }
        }
    }
}
