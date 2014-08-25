package com.example.androidclient;

import java.io.*;
import java.net.Socket;
import android.support.v7.app.ActionBarActivity;
import android.os.*;

import com.example.androidclient.DataObject;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends ActionBarActivity{
	
	String string = null;
	boolean flag = false;
	ObjectInputStream ois = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		new Thread(){
			public void run(){
				try{
				Socket s = new Socket("192.168.1.112",9999);
				ois = new ObjectInputStream(s.getInputStream());
				}catch(Exception e){
					e.printStackTrace();
				}
				DataObject obj = new DataObject();
				while(true){
			        	try {
							obj = (DataObject) ois.readObject();
						} catch (OptionalDataException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (ClassNotFoundException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						System.out.println(obj.getUrl()+"我在这里");   
				}
			}
		}.start();
		SystemClock.sleep(1000);
		Toast.makeText(getApplicationContext(), string, Toast.LENGTH_SHORT).show();
	}
	
	
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	public static String InputStreamTOString(InputStream in) throws Exception{  
        
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();  
        byte[] data = new byte[1024];  
        int count = -1;  
        while((count = in.read(data,0,1024)) != -1)  
            outStream.write(data, 0, count);  
          
        data = null;  
        return new String(outStream.toByteArray(),"ISO-8859-1");  
    }  
}
