package com.example.atomica.adapters;


import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.atomica.R;
import com.example.atomica.responses.YTResponse;
import de.hdodenhof.circleimageview.CircleImageView;

import java.util.List;

public class YTAdapter extends RecyclerView.Adapter<YTAdapter.ViewHolder> {

    private Context context;
    private List<YTResponse.VideoResult> videoList;

    public YTAdapter(Context context, List<YTResponse.VideoResult> videoList) {
        this.context = context;
        this.videoList = videoList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.video_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        YTResponse.VideoResult video = videoList.get(position);

        // Set title and description
        holder.caption.setText(video.title);
        holder.description.setText(video.description);
        holder.views.setText(video.views + " views");
        holder.date.setText("Published on " + video.published_date);

        // Load video thumbnail
        Glide.with(context)
                .load(video.thumbnail.mystatic)
                .centerCrop()
                .placeholder(R.drawable.thumb)
                .into(holder.posterVideo);

        // Load channel image
        Glide.with(context)
                .load(video.channel.thumbnail)
                .placeholder(R.drawable.beaker)
                .into(holder.channelImg);

        // Open YouTube video on click
        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(video.link));
            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return videoList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView posterVideo;
        CircleImageView channelImg;
        TextView caption, description, views, date;

        public ViewHolder(View itemView) {
            super(itemView);
            posterVideo = itemView.findViewById(R.id.poster_video);
            channelImg = itemView.findViewById(R.id.chanel_img);
            caption = itemView.findViewById(R.id.caption);
            description = itemView.findViewById(R.id.description);
            views = itemView.findViewById(R.id.views_video);
            date = itemView.findViewById(R.id.date_video);
        }
    }
}
