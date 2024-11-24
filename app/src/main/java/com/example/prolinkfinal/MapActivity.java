package com.example.prolinkfinal;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.osmdroid.config.Configuration;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.Marker;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MapActivity extends AppCompatActivity {
    private MapView mapView;
    ImageView serviceImage;
    TextView titleView;
    TextView direcView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Configurar OSMDroid
        Context ctx = getApplicationContext();
        Configuration.getInstance().load(ctx, PreferenceManager.getDefaultSharedPreferences(ctx));

        setContentView(R.layout.activity_map);

        String serviceType = getIntent().getStringExtra("SERVICE_TYPE");
        setTitle("Proveedores de " + serviceType);

        serviceImage = findViewById(R.id.service_img_map);
        titleView = findViewById(R.id.title_view);
        direcView = findViewById(R.id.direction);
        switch (serviceType) {
            case "plomeria":
                serviceImage.setImageResource(R.drawable.plomeria);
                titleView.setText("Plomeria los dos Hermanos");
                direcView.setText("Cl. 72 #10-34, Chapinero, Bogotá, Cundinamarca");
                break;
            case "jardineria":
                serviceImage.setImageResource(R.drawable.jardineria);
                titleView.setText("Tu Matera.com");
                direcView.setText("Ak 68 #72-43, Bogotá");
                break;
            case "reparacion":
                serviceImage.setImageResource(R.drawable.reparacion);
                titleView.setText("Repara todo");
                direcView.setText("Ak. 9 #131a-2, Bogotá");
                break;
            case "tutoria":
                serviceImage.setImageResource(R.drawable.tutoria);
                titleView.setText("Udemy bosa");
                direcView.setText("Cra. 58d #146-51, Bogotá");
                break;
            case "construccion":
                serviceImage.setImageResource(R.drawable.construccion);
                titleView.setText("Home Center");
                direcView.setText("Cl. 72 #10-34, Chapinero, Bogotá, Cundinamarca");
                break;
            case "electrodomesticos":
                serviceImage.setImageResource(R.drawable.electrodomesticos);
                titleView.setText("Electro S.A.");
                direcView.setText("Ak 68 #72-43, Bogotá");
                break;
            case "ferreteria":
                serviceImage.setImageResource(R.drawable.reparacion);
                titleView.setText("Clavos y Clavos");
                direcView.setText("Ak. 9 #131a-2, Bogotá");
                break;
            case "supermercado":
                serviceImage.setImageResource(R.drawable.tutoria);
                titleView.setText("Oxxo");
                direcView.setText("Cra. 58d #146-51, Bogotá");
                break;

        }

        mapView = findViewById(R.id.mapView);
        mapView.setTileSource(TileSourceFactory.MAPNIK);
        mapView.setBuiltInZoomControls(true);
        mapView.setMultiTouchControls(true);

        GeoPoint startPoint = new GeoPoint(4.666776, -74.097164);
        mapView.getController().setCenter(startPoint);
        mapView.getController().setZoom(10.0);
        addSampleProviders();

        RatingBar ratingBar = findViewById(R.id.ratingBar);
        ratingBar.setRating((float)Math.random()*5);
    }

    private void addSampleProviders() {
        addProviderMarker(new GeoPoint(4.756776, -74.091164), "Donde Jonh Jairo", "4.5★");
        addProviderMarker(new GeoPoint(4.616256, -74.196364), "Aqui fue", "4.8★");
        addProviderMarker(new GeoPoint(4.896906, -74.299064), "La esquina barata de Juan", "4.2★");
        addProviderMarker(new GeoPoint(4.506906, -74.192064), "Tabogo", "4.2★");
        addProviderMarker(new GeoPoint(4.006776, -74.001164), "Donde Jonh Jairo", "4.5★");
        addProviderMarker(new GeoPoint(4.516256, -74.496364), "Aqui fue", "4.8★");
        addProviderMarker(new GeoPoint(4.916906, -74.799064), "La esquina barata de Juan", "4.2★");
        addProviderMarker(new GeoPoint(4.106906, -74.892064), "Tabogo", "4.2★");
        addProviderMarker(new GeoPoint(4.706776, -74.991164), "Donde Jonh Jairo", "4.5★");
        addProviderMarker(new GeoPoint(4.556256, -74.186364), "Aqui fue", "4.8★");
        addProviderMarker(new GeoPoint(4.106906, -74.219064), "La esquina barata de Juan", "4.2★");
        addProviderMarker(new GeoPoint(4.106906, -74.102064), "Tabogo", "4.2★");
    }

    private void addProviderMarker(GeoPoint point, String name, String rating) {
        Marker marker = new Marker(mapView);
        marker.setPosition(point);
        marker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM);
        marker.setTitle(name);
        marker.setSnippet(rating);

        // Hacer clic en el marcador para abrir la actividad de contratación
        marker.setOnMarkerClickListener((marker1, mapView1) -> {
            Intent intent = new Intent(MapActivity.this, BookingActivity.class);
            intent.putExtra("PROVIDER_NAME", marker1.getTitle()); // Pasar el nombre del proveedor
            startActivity(intent);
            return true;
        });

        mapView.getOverlays().add(marker);
    }


    @Override
    protected void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mapView.onPause();
    }
}
