package com.ekf.robologo;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Halado extends Activity {

	EditText text;
	Button kuldes;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_halado);
        text=(EditText)findViewById(R.id.kod);
        kuldes=(Button)findViewById(R.id.kuldes);
        
        kuldes.setOnClickListener(new View.OnClickListener() {
        	
        	public void onClick(View v) {
        		
        	   	Kuldes();
        	}
        });
    }
    public void Kuldes(){
    	
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_halado, menu);
        return true;
    }
}
