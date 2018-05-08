package com.samapps.aajabhai.sampleapp;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class FirebaseHomeViewHolder extends RecyclerView.ViewHolder {
    View mView;
    Context mContext;

    public FirebaseHomeViewHolder(View itemView) {
        super(itemView);
        mView = itemView;
        mContext = itemView.getContext();
    }

    public void bindImages(UserId model) {
        ImageView imageView = (ImageView) mView.findViewById(R.id.image);
        TextView imagename = (TextView) mView.findViewById(R.id.image_name);
        TextView image_title = (TextView) mView.findViewById(R.id.image_title);

        Picasso.get()
                .load(model.getImage_url())
                .resize(80,450)
                .centerInside()
                .into(imageView);

        imagename.setText(model.getAuthor());
        image_title.setText(model.getTitle());
    }
}
