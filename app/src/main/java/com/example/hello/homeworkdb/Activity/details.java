package com.example.hello.homeworkdb.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hello.homeworkdb.R;

public class details extends AppCompatActivity {

    private TextView textView1,textView2;
    private ImageView img;
    private Bundle extra;
    private ImageButton button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        getSupportActionBar().hide();
        textView1 = (TextView) findViewById(R.id.name2);
        textView2 = (TextView) findViewById(R.id.textView3);
        button = (ImageButton) findViewById(R.id.imageButton);
        img = (ImageView) findViewById(R.id.imglogo);


        extra = getIntent().getExtras();
        textView1.setText(extra.getString("name"));
        textView2.setText(extra.getString("description"));
        img.setImageResource(extra.getInt("img"));

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(details.this, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });

    }
}
