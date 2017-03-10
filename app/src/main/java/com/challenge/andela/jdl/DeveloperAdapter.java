package com.challenge.andela.jdl;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by mokeam on 3/4/17.
 */
//This class handles setting the contents of the recyclerview adapter

public class DeveloperAdapter extends RecyclerView.Adapter<DeveloperAdapter.MyViewHolder> {

    private List<Developer.ItemsEntity> itemsEntities;

    private Context mContext;

    public Context getContext() {
        return mContext;
    }

    public DeveloperAdapter(List<Developer.ItemsEntity> itemsEntities) {
        this.itemsEntities = itemsEntities;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.each_row_item, parent, false);

            mContext = parent.getContext();
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(DeveloperAdapter.MyViewHolder holder, int position) {

        final DeveloperAdapter.MyViewHolder holder1 = holder;

       Picasso.with(getContext()).load(itemsEntities.get(position).getAvatar_url()).into(holder1.circularImageView);
        holder1.username.setText(itemsEntities.get(position).getLogin());

        final String name = itemsEntities.get(position).getLogin();
        final String image = itemsEntities.get(position).getAvatar_url();
        final String profileUrl = itemsEntities.get(position).getHtml_url();


        holder1.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context context = v.getContext();
                Intent intent = new Intent(context,DetailActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("name",name);
                bundle.putString("image",image);
                bundle.putString("profileUrl",profileUrl);
                intent.putExtras(bundle);
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return itemsEntities.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView username;
        public ImageView circularImageView;

        public MyViewHolder(View view) {
            super(view);
            circularImageView = (ImageView) view.findViewById(R.id.profileImage);
            username = (TextView) view.findViewById(R.id.username);

        }
    }

}