package com.example.transportcostcomparator;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private Button btnStartCalculation;
    private Button btnListenToMe;
    private Button btnExit;
    private MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnStartCalculation = findViewById(R.id.btnStartCalculation);
        btnListenToMe = findViewById(R.id.btnListenToMe);
        btnExit = findViewById(R.id.btnExit);

        btnStartCalculation.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, InputActivity.class);
            startActivity(intent);
        });

        btnListenToMe.setOnClickListener(v -> playAudio());


        btnExit.setOnClickListener(v -> finishAffinity());
    }

    private void playAudio() {
        if (mediaPlayer == null) {
            mediaPlayer = MediaPlayer.create(this, R.raw.transport_audio);
        }
        if (mediaPlayer != null) {
            mediaPlayer.start();
        } else {
            Toast.makeText(this, "Audio file not found.", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_home) {
            return true;
        } else if (id == R.id.action_details) {
            Toast.makeText(this, "Details screen not yet available.", Toast.LENGTH_SHORT).show();
            return true;
        } else if (id == R.id.action_reports) {
            Toast.makeText(this, "Reports screen not yet available.", Toast.LENGTH_SHORT).show();
            return true;
        } else if (id == R.id.action_help) {
        startActivity(new Intent(this, HelpActivity.class));
        return true;
    }


        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }
}