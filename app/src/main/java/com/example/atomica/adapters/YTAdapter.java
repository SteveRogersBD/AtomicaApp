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

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class YTAdapter extends RecyclerView.Adapter<YTAdapter.ViewHolder> {

    private Context context;
    private List<YTResponse.Content> videoList;

    public YTAdapter(Context context, List<YTResponse.Content> videoList) {
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
        YTResponse.Content content = videoList.get(position);

        if (content.video != null) {
            YTResponse.Video video = content.video;

            // Set video title
            holder.caption.setText(video.title != null ? video.title : "No title available");

            // Set description snippet
            holder.description.setText(video.descriptionSnippet != null ? video.descriptionSnippet : "No description available");

            // Set views and date
            if (video.stats != null) {
                holder.views.setText(formatViewCount(video.stats.views));
            } else {
                holder.views.setText("No views data");
            }

            holder.date.setText(video.publishedTimeText != null ? video.publishedTimeText : "No date available");

            // Load video thumbnail
            if (video.thumbnails != null && !video.thumbnails.isEmpty()) {
                Glide.with(context)
                        .load(video.thumbnails.get(0).url)
                        .centerCrop()
                        .placeholder(R.drawable.thumb)
                        .into(holder.posterVideo);
            } else {
                holder.posterVideo.setImageResource(R.drawable.thumb);
            }

            // Load channel image
            if (video.author != null && video.author.avatar != null && !video.author.avatar.isEmpty()) {
                Glide.with(context)
                        .load(video.author.avatar.get(0).url)
                        .placeholder(R.drawable.beaker)
                        .into(holder.channelImg);
            } else {
                holder.channelImg.setImageResource(R.drawable.beaker);
            }

            // Open YouTube video on click
            holder.itemView.setOnClickListener(v -> {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=" + video.videoId));
                context.startActivity(intent);
            });
        }
    }

    @Override
    public int getItemCount() {
        return videoList.size();
    }

    private String formatViewCount(long views) {
        if (views < 1000) {
            return views + " views"; // Less than 1K, show as is
        } else if (views < 1_000_000) {
            // Format as K (thousands)
            return String.format("%.1fK views", views / 1000.0);
        } else if (views < 1_000_000_000) {
            // Format as M (millions)
            return String.format("%.1fM views", views / 1_000_000.0);
        } else {
            // Format as B (billions)
            return String.format("%.1fB views", views / 1_000_000_000.0);
        }
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
