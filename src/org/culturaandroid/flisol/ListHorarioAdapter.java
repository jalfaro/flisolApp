package org.culturaandroid.flisol;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class ListHorarioAdapter extends ArrayAdapter<Horario>{
	private Context ctx;
	private int resourceLayout;
	private List<Horario> lista;
	
	public ListHorarioAdapter(Context context, int textViewResourceId,
			List<Horario> objects) {
		super(context, textViewResourceId, objects);
		ctx = context;
		resourceLayout = textViewResourceId;
		lista = objects;
		// TODO Auto-generated constructor stub
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		View v = convertView;
        if (v == null) {
            LayoutInflater vi = (LayoutInflater)ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = vi.inflate(resourceLayout, null);
        }
        Horario o = lista.get(position);
        if (o != null) {
                TextView horario = (TextView) v.findViewById(R.id.txtHorarioRow);
                TextView charla = (TextView) v.findViewById(R.id.txtCharlaRow);
                TextView charlista = (TextView) v.findViewById(R.id.txtCharlistaRow);
                if (horario != null) {
                      horario.setText(o.getHora());                            }
                if(charla != null){
                      charla.setText(o.getTema());
                }
                if(charlista != null) {
                	charlista.setText(o.getEncargado());
                }
        }
        return v;
	}
	

}
