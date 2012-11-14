package com.ekf.robologo;

import java.io.DataOutputStream;
import java.io.IOException;

import android.os.Bundle;
import android.os.Handler;
import android.app.Activity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.Spinner;
import android.widget.TextView;

public class Egyszeru extends Activity {

	SeekBar csuszka1;
	SeekBar csuszka2;
	//TextView par1;
	//TextView par2;
	EditText par1;
	EditText par2;
	Spinner sp;
	TextView p1;
	TextView p2;
	Button kuldes;
	String vmi;
	int tartalom1;
	int tartalom2;
	
	private static final String[] itemek={
		/*"Kör"*/"Sokszög", "Téglalap", "Négyzet"
	};
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_egyszeru);
        
        csuszka1=(SeekBar)findViewById(R.id.seek1);
        csuszka2=(SeekBar)findViewById(R.id.seek2);
        //par1=(TextView)findViewById(R.id.parameter1);
        //par2=(TextView)findViewById(R.id.parameter2);
        par1=(EditText)findViewById(R.id.parameter1);
        par2=(EditText)findViewById(R.id.parameter2);
        sp=(Spinner)findViewById(R.id.alakzat);
        p1=(TextView)findViewById(R.id.param1);
        p2=(TextView)findViewById(R.id.param2);
        kuldes=(Button)findViewById(R.id.kuldes);
        
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.alakzatok, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        sp.setAdapter(adapter);
        
        sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            //public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                
            //}
            public void onNothingSelected(AdapterView<?> parent) {
            }
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				// TODO Auto-generated method stub
				//Object item = arg0.getItemAtPosition(arg2);
				vmi=sp.getSelectedItem().toString();
				csuszka1.setMax(1000);
				Log.i("sdfd",vmi);
				if(vmi.equals("Kör")){
					Log.i("sdfd","lefutok1");
					
					p1.setText("Sugár (r)");
					p1.setVisibility(View.VISIBLE);
					csuszka1.setVisibility(View.VISIBLE);
					par1.setVisibility(View.VISIBLE);
					
					p2.setVisibility(View.INVISIBLE);
					csuszka2.setVisibility(View.INVISIBLE);
					par2.setVisibility(View.INVISIBLE);
				}
				if(vmi.equals("Téglalap")){
					Log.i("sdfd","lefutok2");
					p1.setText("Oldal (a)");
					p1.setVisibility(View.VISIBLE);
					csuszka1.setVisibility(View.VISIBLE);
					par1.setVisibility(View.VISIBLE);
					
					p2.setText("Oldal (b)");
					p2.setVisibility(View.VISIBLE);
					csuszka2.setVisibility(View.VISIBLE);
					par2.setVisibility(View.VISIBLE);
				}
				if(vmi.equals("Négyzet")){
					p1.setText("Oldalak (a)");
					p1.setVisibility(View.VISIBLE);
					csuszka1.setVisibility(View.VISIBLE);
					par1.setVisibility(View.VISIBLE);
					
					p2.setVisibility(View.INVISIBLE);
					csuszka2.setVisibility(View.INVISIBLE);
					par2.setVisibility(View.INVISIBLE);
				}
				if(vmi.equals("Sokszög")){
					csuszka1.setMax(3);
					
					
					p1.setText("Oldalak száma");
					p1.setVisibility(View.VISIBLE);
					csuszka1.setVisibility(View.VISIBLE);
					par1.setVisibility(View.VISIBLE);
					
					p2.setText("Oldalak hossza");
					p2.setVisibility(View.VISIBLE);
					csuszka2.setVisibility(View.VISIBLE);
					par2.setVisibility(View.VISIBLE);
				}
			}
        });
        
        par1.addTextChangedListener(new TextWatcher(){

			public void afterTextChanged(Editable arg0) {
				// TODO Auto-generated method stub
				try{
				tartalom1=Integer.parseInt(par1.getText().toString());
				}
				catch(Exception e)
				{
					
				}
			}

			public void beforeTextChanged(CharSequence arg0, int arg1,
					int arg2, int arg3) {
				// TODO Auto-generated method stub
				
			}

			public void onTextChanged(CharSequence arg0, int arg1, int arg2,
					int arg3) {
				// TODO Auto-generated method stub
				
			}
        	
        });
        par2.addTextChangedListener(new TextWatcher(){

			public void afterTextChanged(Editable arg0) {
				// TODO Auto-generated method stub
				try{
				tartalom2=Integer.parseInt(par2.getText().toString());
				}
				catch(Exception e)
				{
					
				}
			}

			public void beforeTextChanged(CharSequence arg0, int arg1,
					int arg2, int arg3) {
				// TODO Auto-generated method stub
				
			}

			public void onTextChanged(CharSequence arg0, int arg1, int arg2,
					int arg3) {
				// TODO Auto-generated method stub
				
			}
        	
        });
        
        csuszka1.setOnSeekBarChangeListener(new OnSeekBarChangeListener(){
        	public void onProgressChanged(SeekBar s, int tartalom, boolean user_e) {
                // TODO Auto-generated method stub
        		//if(user_e){
        		
        		if(vmi.equals("Sokszög")){
        			par1.setText(Integer.toString(tartalom+3));
        			tartalom1=tartalom+3;
        		}else{
        			par1.setText(Integer.toString(tartalom));
                    tartalom1=tartalom;
        		}
                
        		//}
            }

            public void onStartTrackingTouch(SeekBar s) {
                // TODO Auto-generated method stub
            }

            public void onStopTrackingTouch(SeekBar s) {
                // TODO Auto-generated method stub
            }
        });
        csuszka2.setOnSeekBarChangeListener(new OnSeekBarChangeListener(){
        	public void onProgressChanged(SeekBar s, int tartalom, boolean user_e) {
                // TODO Auto-generated method stub
        		//if(user_e){
                par2.setText(Integer.toString(tartalom));
                tartalom2=tartalom;
        		//}
            }

            public void onStartTrackingTouch(SeekBar s) {
                // TODO Auto-generated method stub
            }

            public void onStopTrackingTouch(SeekBar s) {
                // TODO Auto-generated method stub
            }
        });
        kuldes.setOnClickListener(new View.OnClickListener() {
        	
        	public void onClick(View v) {
        		
        	   	Kuldes();
        	}
        });
    }
    public void Kuldes(){
    	String msg="";
    	if(vmi.equals("Kör")){//ez a ism360[elõre 1 jobbra 1]
    		/*for(int i=0;i<360;i++){
    			msg="11";
    			msg+='\n';
    			try {
					RoboLOGO.myOutputStream.write(msg.getBytes());
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
    			msg="41";
    			msg+='\n';
    			try {
					RoboLOGO.myOutputStream.write(msg.getBytes());
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
    		}*/
    		for(int i=0;i<360;i++){//ism360[elõre2*3.14*sugár/360 balra 1] 
    			float ertek=(float) (2*3.14*(float)tartalom1)/360;
    			//String s=String.format("%.1f", ertek);
    			msg="1"+Float.toString(ertek);
    			//msg="1"+s;
    			msg+='\n';
    			try {
					RoboLOGO.myOutputStream.write(msg.getBytes());
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
    			msg="41";
    			msg+='\n';
    			try {
					RoboLOGO.myOutputStream.write(msg.getBytes());
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
    		}
    	}
    	if(vmi.equals("Sokszög")){
    		int valt=0;
			if(tartalom1<3 || tartalom1>6){
			if(tartalom1<3){
				valt=3;
			}
			if(tartalom1>6){
				valt=6;
			}
			}else{valt=tartalom1;}
    		for(int i=0;i<valt;i++){
    			
    			msg="1"+Integer.toString(tartalom2);
    			msg+='\n';
    			try {
					RoboLOGO.myOutputStream.write(msg.getBytes());
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
    			int sz=360/valt;
    			msg="4"+Integer.toString(sz);
    			msg+='\n';
    			try {
					RoboLOGO.myOutputStream.write(msg.getBytes());
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
    			
    		}
    		
    	}
    	if(vmi.equals("Téglalap")){//jobbra=4 balra=3
    		for(int i=0;i<2;i++){
    			msg="1"+Integer.toString(tartalom1);
    			msg+='\n';
    			try {
					RoboLOGO.myOutputStream.write(msg.getBytes());
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
    			msg="490";
    			msg+='\n';
    			try {
					RoboLOGO.myOutputStream.write(msg.getBytes());
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
    			msg="1"+Integer.toString(tartalom2);
    			msg+='\n';
    			try {
					RoboLOGO.myOutputStream.write(msg.getBytes());
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
    			msg="490";
    			msg+='\n';
    			try {
					RoboLOGO.myOutputStream.write(msg.getBytes());
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
    		}
    	}
    	if(vmi.equals("Négyzet")){
    		for(int i=0;i<2;i++){
    			msg="1"+Integer.toString(tartalom1);
    			msg+='\n';
    			try {
					RoboLOGO.myOutputStream.write(msg.getBytes());
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
    			msg="490";
    			msg+='\n';
    			try {
					RoboLOGO.myOutputStream.write(msg.getBytes());
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
    			msg="1"+Integer.toString(tartalom1);
    			msg+='\n';
    			try {
					RoboLOGO.myOutputStream.write(msg.getBytes());
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
    			msg="490";
    			msg+='\n';
    			try {
					RoboLOGO.myOutputStream.write(msg.getBytes());
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
    		}
    	}
    	//String msg = myTextbox.getText().toString();
        //msg += "\n";
        
        /*try {
			RoboLOGO.myOutputStream.write(msg.getBytes());
			DataOutputStream out=new DataOutputStream(RoboLOGO.socket.getOutputStream());
			out.writeInt(tartalom1);
			out.writeInt(tartalom2);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			Log.i("vmi","gond van a változó kiolvasásával");
			e.printStackTrace();
		}*/
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_egyszeru, menu);
        return true;
    }
}
