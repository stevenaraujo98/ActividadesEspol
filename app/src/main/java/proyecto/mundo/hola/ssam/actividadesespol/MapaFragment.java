package proyecto.mundo.hola.ssam.actividadesespol;


import android.location.Geocoder;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;


/**
 * A simple {@link Fragment} subclass.
 */
public class MapaFragment extends Fragment {
    private GoogleMap mMap;
    SupportMapFragment mapFragment;
    Geocoder geocoder;
    Marker marker;

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);
        //view.findViewIde(Id.id.nombre);

        geocoder = new Geocoder(this.getContext());
        mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.mapa_ubicacion);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View v = inflater.inflate(R.layout.fragment_mapa, container, false);
        if(mapFragment == null){
            mapFragment = SupportMapFragment.newInstance();
            mapFragment.getMapAsync(new OnMapReadyCallback() {
                @Override
                public void onMapReady(GoogleMap googleMap) {
                    mMap = googleMap;
                    mMap.setMinZoomPreference(17);
                    LatLng gyeLocation = new LatLng( -2.1459243, -79.9657831 );
                    mMap.animateCamera(CameraUpdateFactory.newLatLng(gyeLocation));
                }
            });
        }
        getChildFragmentManager().beginTransaction().replace(R.id.mapa_ubicacion, mapFragment).commit();
        return v;
    }

    private void adMaker(LatLng lat, String titulo, boolean moveCam){
        if(marker != null){
            marker.remove();
        }

        marker = mMap.addMarker(new MarkerOptions()
            .position(lat).title(titulo)
        );

        if(moveCam){
            mMap.moveCamera(CameraUpdateFactory.newLatLng(lat));
            mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(lat, 17));
        }
    }
}
