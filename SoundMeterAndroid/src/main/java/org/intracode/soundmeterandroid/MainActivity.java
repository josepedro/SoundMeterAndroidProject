package org.intracode.soundmeterandroid;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.os.Build;

import android.widget.LinearLayout;
import android.os.Bundle;
import android.os.Environment;
import android.view.ViewGroup;
import android.widget.Button;
import android.view.View;
import android.view.View.OnClickListener;
import android.content.Context;
import android.util.Log;
import android.media.MediaRecorder;
import android.media.MediaPlayer;

import java.io.File;
import java.io.IOException;


import android.app.Activity;

public class MainActivity extends Activity {
    private MediaPlayer mediaPlayer;
    private MediaRecorder recorder;
    private String OUTPUT_FILE;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
                    playRecording();
                }catch (Exception e){
                    e.printStackTrace();
                }
                break;
            case R.id.button4:
                try {
                    stopPlaying();
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
            recorder.stop();
        }
    }

    private void playRecording() throws IOException {
        ditchMediaPlayer();
        mediaPlayer=new MediaPlayer();
        mediaPlayer.setDataSource(OUTPUT_FILE);
        mediaPlayer.prepare();
        mediaPlayer.start();
    }

    private void ditchMediaPlayer() {
        if (mediaPlayer != null)
        {
            try
            {
                mediaPlayer.release();
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
        }
    }

    private void stopPlaying() {
        if (mediaPlayer != null)
        {
            mediaPlayer.stop();
        }
    }

}
