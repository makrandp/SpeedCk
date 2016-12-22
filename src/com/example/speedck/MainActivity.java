package com.example.speedck;

import java.util.Currency;
import java.util.Timer;
import java.util.TimerTask;

import com.facebook.network.connectionclass.ConnectionClassManager;
import com.facebook.network.connectionclass.ConnectionQuality;
import com.facebook.network.connectionclass.DeviceBandwidthSampler;

import android.support.v7.app.ActionBarActivity;
import android.app.Activity;
import android.app.ProgressDialog;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnPreparedListener;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;


public class MainActivity extends Activity {

	
	private ConnectionClassManager mConnectionClassManager;
	private DeviceBandwidthSampler mDeviceBandwidthSampler;
	private ConnectionChangedListener mListener,mListener2;
	private ConnectionQuality mConnectionClass = ConnectionQuality.UNKNOWN;

	int cnt ,flag=1,count=0,curent=0;
	int arr[]=new int[5];
	TextView tv1;
Button bt,stop;
ProgressDialog pDialog;
VideoView videoview;
	
//Insert your Video URL
String VideoURL = "";

	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        tv1=(TextView)findViewById(R.id.textView1);
        bt=(Button)findViewById(R.id.button1);
        stop=(Button)findViewById(R.id.button2);
        videoview = (VideoView) findViewById(R.id.videoView1);
        mConnectionClassManager = ConnectionClassManager.getInstance();
        mDeviceBandwidthSampler = DeviceBandwidthSampler.getInstance();
        
        tv1.setText(mConnectionClassManager.getCurrentBandwidthQuality().toString());
       
        mListener = new ConnectionChangedListener();
        
        bt.setOnClickListener(new OnClickListener() {
    		
    		@Override
    		public void onClick(View v) {
    			// TODO Auto-generated method stub
    	

    		    mDeviceBandwidthSampler.startSampling();
    		    
    			
    			pDialog = new ProgressDialog(MainActivity.this);
    			// Set progressbar title
    			pDialog.setTitle("Demo Android Video Streaming ");
    			// Set progressbar message
    			pDialog.setMessage("Buffering...");
    			pDialog.setIndeterminate(false);
    			pDialog.setCancelable(false);
    			// Show progressbar
    			pDialog.show();
    	 
    			try {
    				// Start the MediaController
    				MediaController mediacontroller = new MediaController(
    						MainActivity.this);
    				mediacontroller.setAnchorView(videoview);
    				// Get the URL from String VideoURL
    				Uri video = Uri.parse(VideoURL);
    				videoview.setMediaController(mediacontroller);
    				videoview.setVideoURI(video);
    	 
    			} catch (Exception e) {
    				Log.e("Error", e.getMessage());
    				e.printStackTrace();
    			}
    	 
    			videoview.requestFocus();
    			videoview.setOnPreparedListener(new OnPreparedListener() {
    				// Close the progress bar and play the video
    				public void onPrepared(MediaPlayer mp) {
    					pDialog.dismiss();
    					videoview.start();
    				}
    			});
    	 
    			
    			
    		
    		}
    	});
        
        
    	
    stop.setOnClickListener(new OnClickListener() {
    	
    	@Override
    	public void onClick(View v) {
    		// TODO Auto-generated method stub
    	
    		videoview.stopPlayback();
    		 mDeviceBandwidthSampler.stopSampling();
    	}
    });
       
    
    
    Timer T=new Timer();
    T.scheduleAtFixedRate(new TimerTask() {         
            @Override
            public void run() {
                runOnUiThread(new Runnable()
                {
                    @Override
                    public void run()
                    {
                       
                        count++;                
                        if(count==15){
                        	count=0;
                        	algo(1,"dum");
                        }
                    }
                });
            }
        }, 1000, 1000);


    
        
    }

    
    
    
void algo(int ch,String speed){
	int newc = 0;
	if(ch==0){
		  Log.e("ok","ok");
	  		//pDialog.setMessage("Next Buffering...");
	  	 // pDialog.show();
	  	 
	      tv1.setText(speed);
	  cnt=videoview.getCurrentPosition()/1000;
	  Uri video1 = null;
	  if(speed.equalsIgnoreCase("poor")){
		  curent=0;
	  }
	  else{
	 if(speed.equalsIgnoreCase("moderate")){
	      video1 = Uri.parse("");
	      curent=0;
	 }
	  else if(speed.equalsIgnoreCase("good")){
	      video1 = Uri.parse("");
	      curent=1;
	  }
	  else if(speed.equalsIgnoreCase("excellent")){
	      video1 = Uri.parse("");
	      curent=2;
	  }
	  else if(speed.equalsIgnoreCase("unknown")){
	      video1 = Uri.parse("");
	      curent=0;
	  }
	  videoview.setVideoURI(video1);
	  videoview.start();
	  videoview.seekTo(cnt*1000);
	  }
	  
	}
	else{
		 Uri video1 = null;
		
		int largest = arr[0], index = 0;
		for (int i = 1; i < arr.length; i++) {
		  if ( arr[i] > largest ) {
		      largest = arr[i];
		      index = i;
		   }
		}
		arr[1]=0;
		arr[2]=0;
		arr[3]=0;
		arr[4]=0;
		
	
		
		switch (index) {
		case 0:
			
			break;
		case 1:
			if(curent==0){
				newc=0;
			}else if(curent==1){
				video1 = Uri.parse("");
				newc=0;
			}else if(curent==2){
				 video1 = Uri.parse("");
				 newc=0;
			}
			break;
		case 2:
			if(curent==0){
				 newc=0;
			}else if(curent==1){
				 newc=1;
			}else if(curent==2){
				video1 = Uri.parse("");
				 newc=1;
			}
			break;
		case 3:
			if(curent==0){
				 newc=1;
				video1 = Uri.parse("");
			}else if(curent==1){
				 newc=1;
			}else if(curent==2){
				 newc=2;
			}
			break;
		case 4:
			if(curent==0){
				 newc=2;
				video1 = Uri.parse("");
			}else if(curent==1){
				 newc=2;
				video1 = Uri.parse("");
			}else if(curent==2){
				 newc=2;
			}
			break;	
		default:
			break;
		}
		
		if(curent!=newc)
		{
		curent=newc;
	Toast.makeText(getApplicationContext(), ""+curent, Toast.LENGTH_SHORT).show();	
		  Log.e("ok","ok");
  	//	pDialog.setMessage("Next Buffering...");
  	 // pDialog.show();
  	 
   //   tv1.setText(speed);
  cnt=videoview.getCurrentPosition()/1000;
 
 
  videoview.setVideoURI(video1);
  videoview.start();
  videoview.seekTo(cnt*1000);
		}
	}
}

    @Override
    protected void onPause() {
      super.onPause();
      mConnectionClassManager.remove(mListener);
    
    }
    
    @Override
    protected void onResume() {
      super.onResume();
      mConnectionClassManager.register(mListener);
    }

    
    private class ConnectionChangedListener
    implements ConnectionClassManager.ConnectionClassStateChangeListener {

    public void onBandwidthStateChange(ConnectionQuality bandwidthState) {
    mConnectionClass = bandwidthState;
   
    runOnUiThread(new Runnable() {
      @Override
      public void run() {
    	  String speed= mConnectionClass.toString();
    	  if(flag==1){
    		  flag=0;
    		  algo(0,speed);
    	  }
    	  else{
    		  tv1.setText(speed);
    if(speed.equalsIgnoreCase("poor"))
    	arr[1]=arr[1]+1;
    else if(speed.equalsIgnoreCase("good"))
    	arr[3]=arr[3]+1;
    else if(speed.equalsIgnoreCase("moderate"))
    	arr[2]=arr[2]+1;
    else if(speed.equalsIgnoreCase("excellent"))
    	arr[4]=arr[4]+1;
    	  }
      }
      
    });
    }
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
    
  

}
