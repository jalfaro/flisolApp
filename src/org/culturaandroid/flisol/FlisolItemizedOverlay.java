package org.culturaandroid.flisol;

import java.util.ArrayList;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;

import com.google.android.maps.ItemizedOverlay;
import com.google.android.maps.OverlayItem;

public class FlisolItemizedOverlay extends ItemizedOverlay {
	
	private ArrayList<OverlayItem> mOverlays = new ArrayList<OverlayItem>();
	private Context mContext;
	private IMostradorHorario mMostradorHorario;

	public FlisolItemizedOverlay(Drawable defaultMarker) {
		super(boundCenterBottom(defaultMarker));
	}
	
	public FlisolItemizedOverlay(Drawable defaultMarker, Context context,
			IMostradorHorario mostradorHorario) {
		super(boundCenterBottom(defaultMarker));
		mContext = context; 
		mMostradorHorario = mostradorHorario;
	}
	
	public void addOverlay(OverlayItem overlay) {
		mOverlays.add(overlay);
		populate();
	}

	@Override
	protected OverlayItem createItem(int indice) {
		return mOverlays.get(indice);
	}

	@Override
	public int size() {
		return mOverlays.size();
	}

	@Override
	protected boolean onTap(int indice) {
		OverlayItem item = mOverlays.get(indice);
		mMostradorHorario.mostrarHorario(item.getSnippet());
		return true;
	}
}
