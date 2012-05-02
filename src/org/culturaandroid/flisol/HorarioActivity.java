package org.culturaandroid.flisol;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.parse.FindCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class HorarioActivity extends Activity {

	private ListView mListView;
	private ArrayList<Horario> horario;
	private int idEvento;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.horario);
    	
        Parse.initialize(this, 
        		"BYsKr1ZYksLgYRm2mJlVNRA8gcCD7lMuoMqNuC74", 
        		"IrWC6CfuvZIPY6DZlTn8vwtNcDMdJxzKFwkTWPH3");
        mListView = (ListView) findViewById(R.id.listHorarios);
        horario = new ArrayList<Horario>();
        idEvento = this.getIntent().getIntExtra("idEvento", -1);
        Toast.makeText(this,""+ idEvento ,Toast.LENGTH_LONG).show();
    
        listarEventos();
        
        //mTituloView.setText("" + this.getIntent().getIntExtra("idEvento", -1));
    }
    
    private void listarEventos()
    {
    	ParseQuery query = new ParseQuery("Actividad");
    	final Context contexto = this;
    	query.findInBackground(new FindCallback() {
			@Override
			public void done(List<ParseObject> listaEvento, ParseException e) {
    	        if (e == null) {
    	        	Iterator<ParseObject> iterador = listaEvento.iterator();
    	        	ParseObject evento;
    	        	String hora = "";
    	        	String charla = "";
    	        	String charlista = "";
    
    	        	while (iterador.hasNext()) {
    	        		evento = iterador.next();
    	        		if (evento.getInt("idEvento") == idEvento) {
    	        			hora = evento.getString("horaInicio") + " - " + evento.getString("horaFin");
    	        			charlista = evento.getString("encargado");
    	        			charla = evento.getString("nombre");
    	        			horario.add(new Horario(hora,charla,charlista));
    	        		}
    	        	}
    	        	mListView.setAdapter(new ListHorarioAdapter(contexto,R.layout.row_horario,horario));
    	        } else {
    	        	Toast.makeText(contexto, 
    	        			"Error: " + e.getMessage(), 
    	        			Toast.LENGTH_LONG);
    	        }
			}
    	});
    }
    
}
