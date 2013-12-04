package com.example.soundmeterandroid;


import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.os.Environment;
import android.widget.Button;
import android.media.MediaRecorder;
import java.io.File;
import java.io.IOException;
import java.lang.Math;


import android.app.Activity;

public class MainActivity extends Activity {
    private MediaRecorder recorder;
    private String OUTPUT_FILE;
    double n=0;
    double cos=0;
    
    TextView nameField;
    Button button3, button1, button2;
    
    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button3 = (Button) findViewById(R.id.button3);
        button2 = (Button) findViewById(R.id.button2);
        button1 = (Button) findViewById(R.id.button1);
        nameField = (TextView) findViewById(R.id.tekst);
        
        OUTPUT_FILE=Environment.getExternalStorageDirectory()+"/audiorecorder.3gpp";
    }
    public void buttonTapped(View view){
        switch(view.getId()){
            case R.id.button1:
                try {
                	beginRecording();
                }catch (Exception e){
                    e.printStackTrace();
                }
                break;
            case R.id.button2:
                try {
                	stopRecording();
                }catch (Exception e){
                    e.printStackTrace();
                }
                break;
            case R.id.button3:
                try {
                	nameField.setText("Sound pressure: " + String.valueOf((int) cos) 
                			+ " dB");
                }catch (Exception e){
                    e.printStackTrace();
                }
                break;
        }
    }

    
	
	private void beginRecording() throws IOException {
        ditchMediaRecorder();
        File outFile = new File(OUTPUT_FILE);

        if (outFile.exists())
        {
            outFile.delete();
        }

        recorder = new MediaRecorder();
        recorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        recorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        recorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
        recorder.setOutputFile(OUTPUT_FILE);

        recorder.prepare();
        recorder.start();
        cos=recorder.getMaxAmplitude();
    }

    private void ditchMediaRecorder() {
        if (recorder != null)
        {
            recorder.release();
        }
    }

    private void stopRecording() {
        if (recorder != null)
        {
        	n=(recorder.getMaxAmplitude()*2)/1000;
        	cos = (20 * Math.log10(n / 0.00002))/3;
        	recorder.stop();
        }
    }
    
    
    /*public double getNoiseLevel() 
	{
	    //Log.d("SPLService", "getNoiseLevel() ");
	    double x = cos;
	    double x2 = x;
	    Log.d("SPLService", "x="+x);
	    double db = (20 * Math.log10(x2 / 0.1));
	    //Log.d("SPLService", "db="+db);
	    if(db>0)
	    {
	        cos=db;
	    	return cos;
	    }
	    else
	    {
	        return 0;
	    }
	}*/

}
