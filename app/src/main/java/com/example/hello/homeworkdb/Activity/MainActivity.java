package com.example.hello.homeworkdb.Activity;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.hello.homeworkdb.Data.DatabaseHandler;
import com.example.hello.homeworkdb.R;
import com.example.hello.homeworkdb.model.User;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private AlertDialog.Builder dialogBuilder;
    private AlertDialog alertDialog;
    private EditText UserName;
    private EditText Password;
    private Button saveButton;
    private EditText Description;
    private DatabaseHandler db;
    private ImageButton login, addUser, allUser;

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
        setContentView(R.layout.activity_main);

        db = new DatabaseHandler(this);

        login =(ImageButton) findViewById(R.id.login);
        addUser = (ImageButton) findViewById(R.id.addUser);
        allUser = (ImageButton) findViewById(R.id.allUser);



        //Toast.makeText(getApplicationContext(),String.valueOf(db.getCount()),Toast.LENGTH_LONG).show();
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, com.example.hello.homeworkdb.Activity.login.class);
                startActivity(intent);
            }
        });
        allUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, com.example.hello.homeworkdb.Activity.allUser.class);
                startActivity(intent);
            }
        });

        addUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popup();
            }
        });
    }

    private void popup() {
        dialogBuilder = new AlertDialog.Builder(this);

        View view = getLayoutInflater().inflate(R.layout.popup,null);
        UserName= (EditText) view.findViewById(R.id.userName);
        Password = (EditText)view.findViewById(R.id.password);
        Description = (EditText) view.findViewById(R.id.description);
        saveButton =(Button) view.findViewById(R.id.savebutton);

        dialogBuilder.setView(view);
        alertDialog = dialogBuilder.create();
        alertDialog.show();


        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!UserName.getText().toString().isEmpty() && !Password.getText().toString().isEmpty())
                {
                    User user =  new User();


                    String name = UserName.getText().toString();
                    String pass = Password.getText().toString();
                    String desc = Description.getText().toString();

                    if(valid(name,db.getUserName())){
                        Toast.makeText(getApplicationContext(), "Username already exists.Try Again", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    user.setUserName(name);
                    user.setPassword(pass);
                    user.setDescription(desc);
                    user.setImg(R.drawable.t7);

                    db.addUser(user);
                    alertDialog.dismiss();
                }

            }
        });
    }

}

