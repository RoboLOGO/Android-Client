package com.ekf.robologo;

import java.io.DataOutputStream;
import java.io.IOException;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;

public class Kezdo extends Activity {

	ImageButton fel, le, jobbra, balra,tfel,tle;
	SeekBar csuszka;
	//TextView text;
	EditText text;
	int t=0;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kezdo);
        fel=(ImageButton)findViewById(R.id.Fel);
        le=(ImageButton)findViewById(R.id.Le);
        jobbra=(ImageButton)findViewById(R.id.Jobbra);
        balra=(ImageButton)findViewById(R.id.Balra);
        csuszka=(SeekBar)findViewById(R.id.seekBar1);
        //text=(TextView)findViewById(R.id.textview);
        text=(EditText)findViewById(R.id.textview);
        tfel=(ImageButton)findViewById(R.id.Tfel);
        tle=(ImageButton)findViewById(R.id.Tle);
        text.addTextChangedListener(new TextWatcher(){

			public void afterTextChanged(Editable arg0) {
				// TODO Auto-generated method stub
				try{
				t=Integer.parseInt(text.getText().toString());
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
        csuszka.setOnSeekBarChangeListener(new OnSeekBarChangeListener(){
        	public void onProgressChanged(SeekBar s, int tartalom, boolean user_e) {
                // TODO Auto-generated method stub
        		//if(user_e){
        		text.setText(Integer.toString(tartalom));
                t=tartalom;
        		//}
            }

            public void onStartTrackingTouch(SeekBar s) {
                // TODO Auto-generated method stub
            }

            public void onStopTrackingTouch(SeekBar s) {
                // TODO Auto-generated method stub
            }
        });
        fel.setOnClickListener(new View.OnClickListener() {
        	
        	public void onClick(View v) {
        		
        	   	Fel();
        	}
        });
        le.setOnClickListener(new View.OnClickListener() {
        	
        	public void onClick(View v) {
        		
        	   	Le();
        	}
        });
        jobbra.setOnClickListener(new View.OnClickListener() {
        	
        	public void onClick(View v) {
        		
        	   	Jobbra();
        	}
        });
        balra.setOnClickListener(new View.OnClickListener() {
        	
        	public void onClick(View v) {
        		
        	   	Balra();
        	}
        });
        tfel.setOnClickListener(new View.OnClickListener() {
        	
        	public void onClick(View v) {
        		
        	   	TollFel();
        	}
        });
        tle.setOnClickListener(new View.OnClickListener() {
        	
        	public void onClick(View v) {
        		
        	   	TollLe();
        	}
        });
    }
    public void TollLe(){
    	String msg="6";
    	msg += '\n';
    	try {
			RoboLOGO.myOutputStream.write(msg.getBytes());
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			
			
		}
    
    }
    public void TollFel(){
    	String msg="5";
    	msg += '\n';
    	try {
			RoboLOGO.myOutputStream.write(msg.getBytes());
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			
			
		}
    }
    public void Fel(){
    	String msg="1"+Integer.toString(t);
    	msg += '\n';
    	try {
			RoboLOGO.myOutputStream.write(msg.getBytes());
			//RoboLOGO.myOutputStream.flush();
			//String elval="e";
			//RoboLOGO.myOutputStream.write(elval.getBytes());
			//RoboLOGO.myOutputStream.flush();
			//DataOutputStream out=new DataOutputStream(RoboLOGO.socket.getOutputStream());
			//out.writeInt(t);
			//String parameter=Integer.toString(t);---------------
			//RoboLOGO.myOutputStream.write(parameter.getBytes());-----------
			//byte[] bytes=null;
			//RoboLOGO.myInputStream.read(bytes);
			//String ack=bytes.toString();
			//if(ack=="ok;"){
			//	Log.i("visszaigazol","visszaigazol");
			//}
			//out.write(t);
			//out.flush();
			//RoboLOGO.myOutputStream.close();  lehet, h kelleni fog
		} catch (IOException e) {
			// TODO Auto-generated catch block
			
			
		}
    }
    public void Le(){
    	String msg="2"+Integer.toString(t);
    	msg += '\n';
    	try {
			RoboLOGO.myOutputStream.write(msg.getBytes());
			//String parameter=Integer.toString(t);
			//RoboLOGO.myOutputStream.write(parameter.getBytes());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			
		}
    }
    public void Jobbra(){
    	int valt=0;
    	if(t>360){
    		valt=360;
    	}else{valt=t;}
    	String msg="4"+Integer.toString(valt);
    	msg += '\n';
    	try {
			RoboLOGO.myOutputStream.write(msg.getBytes());
			//String parameter=Integer.toString(t);
			//RoboLOGO.myOutputStream.write(parameter.getBytes());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			
		}
    }
    public void Balra(){
    	int valt=0;
    	if(t>360){
    		valt=360;
    	}else{valt=t;}
    	String msg="3"+Integer.toString(valt);
    	msg += '\n';
    	try {
			RoboLOGO.myOutputStream.write(msg.getBytes());
			//String parameter=Integer.toString(t);
			//RoboLOGO.myOutputStream.write(parameter.getBytes());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			/*Intent intent = new Intent(this, RoboLOGO.class);
			intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		   	startActivity(intent);
		   	finish();*/
		}
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_kezdo, menu);
        return true;
    }
}
