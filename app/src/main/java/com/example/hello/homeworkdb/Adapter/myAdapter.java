package com.example.hello.homeworkdb.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hello.homeworkdb.R;

import com.example.hello.homeworkdb.model.User;
import java.util.List;

public class myAdapter extends RecyclerView.Adapter<myAdapter.ViewHolder> {

    private Context context;
    private List<User> list;

    public myAdapter(Context context, List<User> list) {
            this.context = context;
            this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            View view = LayoutInflater.from(viewGroup.getContext())
            .inflate(R.layout.relative,viewGroup,false);
            return new ViewHolder(view);
            }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
            User  user = list.get(i);
            viewHolder.img.setImageResource(user.getImg());
            viewHolder.name.setText(user.getUserName());

            }


    @Override
    public int getItemCount()  {
            return list.size();
            }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView name;
        public ImageView img;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            img = (ImageView) itemView.findViewById(R.id.imageView4);
            name = (TextView) itemView.findViewById(R.id.textView1);
        }
        @Override
        public void onClick(View v) {

        }
    }

}
