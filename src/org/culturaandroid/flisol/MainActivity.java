package org.culturaandroid.flisol;

import com.parse.Parse;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {
    /** Called when the activity is first created. */
	Button btnMapa;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        Parse.initialize(this, "BYsKr1ZYksLgYRm2mJlVNRA8gcCD7lMuoMqNuC74", "IrWC6CfuvZIPY6DZlTn8vwtNcDMdJxzKFwkTWPH3"); 
        btnMapa = (Button) findViewById(R.id.btnMapa);
        btnMapa.setOnClickListener(new Button.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				startActivity(new Intent(getBaseContext(),FlisolMapActivity.class));
			}
        	
        });
    }
    
}