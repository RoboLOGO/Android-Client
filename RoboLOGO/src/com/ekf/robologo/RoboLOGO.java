package com.ekf.robologo;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.UUID;

import android.os.Bundle;
import android.os.Handler;
import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class RoboLOGO extends Activity {
	
	private static final int REQUEST_ENABLE = 1;
	//private int progressbarstatus=0;
	//private Handler handler=new Handler();

	TextView tv_robot_status;
	Button btn_connect;
	BluetoothAdapter bt_adapter;
	BluetoothDevice device;
	UUID uuid;
	public static BluetoothSocket socket;
	public static OutputStream myOutputStream;
	public static InputStream myInputStream;
	ProgressBar progbar;
	boolean csatlakozok=false;
	volatile boolean stopWorker;
	byte[] readBuffer;
    int readBufferPosition;
    Thread workerThread;
    public static String kapott="ures";
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_robo_logo);
        
        tv_robot_status = (TextView)findViewById(R.id.tv_robot_status);
        btn_connect = (Button)findViewById(R.id.btn_connect);
        progbar=(ProgressBar)findViewById(R.id.progressbar);
        
        //megvan = false;
        
        bt_adapter = BluetoothAdapter.getDefaultAdapter();
        
        if(bt_adapter.isEnabled() == false) {
        	
            Intent enabler = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(enabler, REQUEST_ENABLE);
        }
        
        uuid = UUID.fromString("00001101-0000-1000-8000-00805f9b34fb");
        
        btn_connect.setOnClickListener(new View.OnClickListener() {
			
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(btn_connect.getText()!="Csatlakozás"){
					
				
				progbar.setVisibility(View.VISIBLE);
				tv_robot_status.setText("Robot keresése...");
				
				
				
				if(bt_adapter.isDiscovering() == false)
				{
					bt_adapter.startDiscovery(); 
				}
				
				IntentFilter filter = new IntentFilter(BluetoothDevice.ACTION_FOUND);
				registerReceiver(br, filter);
				}
				
				if(btn_connect.getText() == "Csatlakozás")
				{
					progbar.setVisibility(View.VISIBLE);
					
					new Thread(new Runnable(){
						public void run(){
							do{
							try{
								socket = device.createRfcommSocketToServiceRecord(uuid);        
							    socket.connect();
							    myOutputStream = socket.getOutputStream();
							    myInputStream = socket.getInputStream();
							    csatlakozok=false;
								}
								catch(IOException e)
								{
									Log.i("bug","bugos a programod");
									csatlakozok=true;
								}
							//progbar.setVisibility(View.GONE);
							}while(csatlakozok);
							csatlakozok=false;
							Start();
							
						}
					}).start();
					//beginListenForData();
					//progbar.refreshDrawableState();
					//if(progbar.isFocusable()){
					//	progbar.requestFocus();
					//}
					
				}
			}
			
		});    
    }
    void beginListenForData()
    {
        final Handler handler = new Handler(); 
        final byte delimiter = 10; //This is the ASCII code for a newline character
        
        stopWorker = false;
        readBufferPosition = 0;
        readBuffer = new byte[1024];
        workerThread = new Thread(new Runnable()
        {
            public void run()
            {                
               while(!Thread.currentThread().isInterrupted() && !stopWorker)
               {
                    try 
                    {
                        int bytesAvailable = myInputStream.available();                        
                        if(bytesAvailable > 0)
                        {
                            byte[] packetBytes = new byte[bytesAvailable];
                            myInputStream.read(packetBytes);
                            for(int i=0;i<bytesAvailable;i++)
                            {
                                byte b = packetBytes[i];
                                if(b == delimiter)
                                {
                                    byte[] encodedBytes = new byte[readBufferPosition];
                                    System.arraycopy(readBuffer, 0, encodedBytes, 0, encodedBytes.length);
                                    final String data = new String(encodedBytes, "UTF-8");
                                    readBufferPosition = 0;
                                    
                                    handler.post(new Runnable()
                                    {
                                        public void run()
                                        {
                                            kapott=data;
                                        }
                                    });
                                }
                                else
                                {
                                    readBuffer[readBufferPosition++] = b;
                                }
                            }
                        }
                    } 
                    catch (IOException ex) 
                    {
                        stopWorker = true;
                    }
               }
            }
        });

        workerThread.start();
    }
    public void Start()
    {
       	Intent intent = new Intent(this, MainMenu.class);
	   	startActivity(intent);
    }

    BroadcastReceiver br = new BroadcastReceiver() {	
		public void onReceive(Context context, Intent intent) {
			
			String action = intent.getAction();
			
			if(BluetoothDevice.ACTION_FOUND.equals(action)) {
				device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
				if(device.getName().equals("linvor"))
				{
					tv_robot_status.setText("Megtaláltam a robotot!");
					progbar.setVisibility(View.GONE);
						//btn_connect.setEnabled(true);
						//megvan = true;
					btn_connect.setText("Csatlakozás");
					
				}
			}	
		}
    };
    
    @Override
    protected void onDestroy()
    {
    	//bt_adapter.disable();
    	unregisterReceiver(br);
    	
    	super.onDestroy();
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_robo_logo, menu);
        return true;
    }
}
