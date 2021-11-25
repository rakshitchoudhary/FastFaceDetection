package com.tastenkunst.brfv4.brfv4_android_examples;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.facegoggle.FaceActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btn_lips, btn_eyebrow, btn_eyeshadow, btn_eyeliner, btn_face, btn_smile, btn_yawn, btn_blink, btn_full, btn_filter;
    private Toolbar toolbar;
    private Intent i;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setUpView();
    }

    private void setUpView() {
        toolbar = findViewById(R.id.toolbar);

        btn_lips = findViewById(R.id.btn_lips);
        btn_eyebrow = findViewById(R.id.btn_eyebrow);
        btn_eyeshadow = findViewById(R.id.btn_eyeshadow);
        btn_eyeliner = findViewById(R.id.btn_eyeliner);
        btn_face = findViewById(R.id.btn_face);
        btn_smile = findViewById(R.id.btn_smile);
        btn_yawn = findViewById(R.id.btn_yawn);
        btn_blink = findViewById(R.id.btn_blink);
        btn_full = findViewById(R.id.btn_full);
        btn_filter = findViewById(R.id.btn_filter);

        btn_lips.setOnClickListener(this);
        btn_eyebrow.setOnClickListener(this);
        btn_eyeshadow.setOnClickListener(this);
        btn_eyeliner.setOnClickListener(this);
        btn_face.setOnClickListener(this);
        btn_smile.setOnClickListener(this);
        btn_yawn.setOnClickListener(this);
        btn_blink.setOnClickListener(this);
        btn_full.setOnClickListener(this);
        btn_filter.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_full:
                i = new Intent(MainActivity.this, BRFv4ExampleActivity.class);
                i.putExtra("option", "1");
                startActivity(i);
                break;
            case R.id.btn_lips:
                Log.e("Lips", "Clicked");
                i = new Intent(MainActivity.this, BRFv4ExampleActivity.class);
                i.putExtra("option", "1");
                startActivity(i);
                break;
            case R.id.btn_eyebrow:
                Log.e("Eyebrow", "Clicked");
                i = new Intent(MainActivity.this, BRFv4ExampleActivity.class);
                i.putExtra("option", "2");
                startActivity(i);
                break;
            case R.id.btn_eyeshadow:
                Log.e("Eyeshadow", "Clicked");
                i = new Intent(MainActivity.this, BRFv4ExampleActivity.class);
                i.putExtra("option", "3");
                startActivity(i);
                break;
            case R.id.btn_eyeliner:
                Log.e("Eyeliner", "Clicked");
                i = new Intent(MainActivity.this, BRFv4ExampleActivity.class);
                i.putExtra("option", "4");
                startActivity(i);
                break;
            case R.id.btn_face:
                Log.e("Eyeliner", "Clicked");
                i = new Intent(MainActivity.this, BRFv4ExampleActivity.class);
                i.putExtra("option", "5");
                startActivity(i);
                break;
            case R.id.btn_smile:
                Log.e("Eyeliner", "Clicked");
                i = new Intent(MainActivity.this, BRFv4ExampleActivity.class);
                i.putExtra("option", "6");
                startActivity(i);
                break;
            case R.id.btn_yawn:
                Log.e("Eyeliner", "Clicked");
                i = new Intent(MainActivity.this, BRFv4ExampleActivity.class);
                i.putExtra("option", "7");
                startActivity(i);
                break;
            case R.id.btn_blink:
                Log.e("Eyeliner", "Clicked");
                i = new Intent(MainActivity.this, BRFv4ExampleActivity.class);
                i.putExtra("option", "8");
                startActivity(i);
                break;
            case R.id.btn_filter:
                i = new Intent(MainActivity.this, FaceActivity.class);
                i.putExtra("option", "8");
                startActivity(i);
                break;
        }
    }
}
