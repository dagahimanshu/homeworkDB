package com.example.hello.homeworkdb.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.hello.homeworkdb.Data.DatabaseHandler;
import com.example.hello.homeworkdb.R;
import com.example.hello.homeworkdb.model.User;

import java.util.List;

public class login extends AppCompatActivity {

    private EditText editText, editText2;
    private ImageButton button;
    private DatabaseHandler db;

    private boolean valid(String str, List<String > list)
    {
        for(String s : list)
        {
            if(s.compareTo(str)==0)
                return true;
        }
        return false;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        db = new DatabaseHandler(this);
        editText = (EditText) findViewById(R.id.editText);
        editText2 = (EditText) findViewById(R.id.editText1);
        button = (ImageButton) findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str = editText.getText().toString();
                String str2 = editText2.getText().toString();


                if(valid(str,db.getUserName())){
                    //Toast.makeText(getApplicationContext()," User name found",Toast.LENGTH_SHORT).show();
                    if((str2.compareTo(db.getPassword(str))!=0))
                        Toast.makeText(getApplicationContext(), "Incorrect password", Toast.LENGTH_SHORT).show();
                    else
                    {
                        //Toast.makeText(getApplicationContext(), "Valid user details", Toast.LENGTH_SHORT).show();
                        User user = db.getUser(str);
                        Intent intent = new Intent(getBaseContext(),details.class);
                        intent.putExtra("name",user.getUserName());
                        intent.putExtra("description",user.getDescription());
                        intent.putExtra("img",user.getImg());
                        startActivity(intent);
                    }
                }
                else {
                    Toast.makeText(getApplicationContext(), "Invalid user details", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}
