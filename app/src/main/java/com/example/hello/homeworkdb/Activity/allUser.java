package com.example.hello.homeworkdb.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import com.example.hello.homeworkdb.Adapter.myAdapter;
import com.example.hello.homeworkdb.Data.DatabaseHandler;
import com.example.hello.homeworkdb.R;
import com.example.hello.homeworkdb.model.User;
import com.example.hello.homeworkdb.model.descriptionsOnly;

public class allUser extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private List<User> userList;
    private List<User> listItem;
    private DatabaseHandler db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_user);

        db = new DatabaseHandler(this);

        recyclerView = (RecyclerView)findViewById(R.id.recycler1);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        String str[] = new String[ ]{ "Diedara","Black Zetsu","Hiden","Itachi Uchiha","Kakuzu","Kisame","Konan","Pain","Sasori","Tobi"};
        int t = R.drawable.t1;
        listItem = new ArrayList<>();
        descriptionsOnly obj = new descriptionsOnly();

        userList = new ArrayList<>();



        //get all db values
        userList=db.getAllUser();
        for(User ite : userList){
            User user = new User();
            user.setPassword(ite.getPassword());
            user.setUserName(ite.getUserName());
            user.setDescription(ite.getDescription());
            user.setId(ite.getId());

            listItem.add(user);
        }

        adapter = new myAdapter(this,listItem);
        recyclerView.setAdapter(adapter);
    }
}
