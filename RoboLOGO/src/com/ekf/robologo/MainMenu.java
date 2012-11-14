package com.ekf.robologo;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

public class MainMenu extends Activity {

	Button kezdo, halado, egyszeru;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        kezdo=(Button)findViewById(R.id.kezdo);
        halado=(Button)findViewById(R.id.halado);
        egyszeru=(Button)findViewById(R.id.egyszeru);
        
        kezdo.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				
			   	Kezdo();
			}
        });
        halado.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				
			   	Halado();
			}
        });
        egyszeru.setOnClickListener(new View.OnClickListener() {
	
        	public void onClick(View v) {
        		
        	   	Egyszeru();
        	}
        });
    }
    public void Kezdo(){
    	Intent intent = new Intent(this, Kezdo.class);
	   	startActivity(intent);
    }
    public void Halado(){
    	Intent intent = new Intent(this, Halado.class);
	   	startActivity(intent);
    }
    public void Egyszeru(){
    	Intent intent = new Intent(this, Egyszeru.class);
	   	startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main_menu, menu);
        return true;
    }
}
