package org.culturaandroid.flisol;

import java.util.Iterator;
import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapActivity;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;
import com.google.android.maps.OverlayItem;
import com.parse.FindCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

public class FlisolMapActivity extends MapActivity 
	implements IMostradorHorario {
	
	private List<Overlay> mapOverlays;
	private FlisolItemizedOverlay itemizedOverlay;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.flisolmap);
        
        // Propiedades del MapView
        MapView mapView = (MapView) findViewById(R.id.mapview);
        mapView.setBuiltInZoomControls(true);
        
        Parse.initialize(this, 
        		"BYsKr1ZYksLgYRm2mJlVNRA8gcCD7lMuoMqNuC74", 
        		"IrWC6CfuvZIPY6DZlTn8vwtNcDMdJxzKFwkTWPH3");
        
        mapOverlays = mapView.getOverlays();
        Drawable drawable = this.getResources().getDrawable(R.drawable.flisolmarcador);
        itemizedOverlay = 
        	new FlisolItemizedOverlay(drawable, this, this);
        
        listarEventos();
    }
    
    private void listarEventos()
    {
    	ParseQuery query = new ParseQuery("Evento");
    	final Context contexto = this;
    	query.findInBackground(new FindCallback() {
			@Override
			public void done(List<ParseObject> listaEvento, ParseException e) {
    	        if (e == null) {
    	        	Iterator<ParseObject> iterador = listaEvento.iterator();
    	        	ParseObject evento;
    	        	int idEvento = 0;
    	        	int latitud = 0;
    	        	int longitud = 0;
    	        	String nombre = "";
    	        	while (iterador.hasNext()) {
    	        		evento = iterador.next();
    	        		idEvento = evento.getInt("idEvento");
    	        		latitud = (int)(evento.getDouble("latitud") * 1000000.0);
    	        		longitud = (int)(evento.getDouble("longitud") * 1000000.0);
    	        		nombre = evento.getString("nombre");
        	        		agregarEvento(latitud, longitud, nombre, "" + idEvento);
    	        	}
    	        	mapOverlays.add(itemizedOverlay);
    	        } else {
    	        	Toast.makeText(contexto, 
    	        			"Error: " + e.getMessage(), 
    	        			Toast.LENGTH_LONG);
    	        }
			}
    	});
    }
    
    private void agregarEvento(int latitud, int longitud, 
    		String nombre, String descripcion) {
        GeoPoint point = new GeoPoint(latitud, longitud);
        OverlayItem overlayitem = new OverlayItem(point, nombre, descripcion);
        itemizedOverlay.addOverlay(overlayitem);
    }

	@Override
	protected boolean isRouteDisplayed() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void mostrarHorario(String idEvento) {
		Intent intento = new Intent(getBaseContext(), HorarioActivity.class);
		intento.putExtra("idEvento", Integer.parseInt(idEvento));
		startActivity(intento);
	}
    
}
