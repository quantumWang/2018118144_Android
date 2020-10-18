package com.example.myrecyclerviewtest;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ActorAdapter extends RecyclerView.Adapter<ActorAdapter.ViewHolder> {

    private List<Actor> mActorList;

    static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView actorImage;
        TextView actorName;

        public ViewHolder(View view) {
            super(view);
            actorImage = (ImageView) view.findViewById(R.id.actor_image);
            actorName = (TextView) view.findViewById(R.id.actor_name);
        }
    }

    public ActorAdapter(List<Actor> actorList) {
        mActorList = actorList;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.actor_item, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Actor actor = mActorList.get(position);
        holder.actorImage.setImageResource(actor.getImageId());
        holder.actorName.setText(actor.getName());
    }

    @Override
    public int getItemCount() {
        return mActorList.size();
    }
}
