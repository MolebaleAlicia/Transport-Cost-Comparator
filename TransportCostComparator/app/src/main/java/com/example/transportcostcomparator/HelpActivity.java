package com.example.transportcostcomparator; // replace with your actual package name

import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class HelpActivity extends AppCompatActivity {

    private Button btnExitHelp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);

        btnExitHelp = findViewById(R.id.btnExitHelp);
        btnExitHelp.setOnClickListener(v -> finishAffinity());
    }
}