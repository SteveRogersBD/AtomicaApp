package com.example.atomica.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.transition.Visibility;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.atomica.R;
import com.example.atomica.TimeFormatter;
import com.example.atomica.api.LocalAPI;
import com.example.atomica.responses.ApiResponse;
import com.example.atomica.responses.Post;
import com.example.atomica.responses.User;
import com.example.atomica.retroclients.RetrofitClient;
import com.squareup.picasso.Picasso;

import java.time.LocalDateTime;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Response;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.ViewHolder>{

    Context context;
    List<Post>postItems;

    public PostAdapter(Context context, List<Post> postItems) {
        this.context = context;
        this.postItems = postItems;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.post_item,parent,false);
        return new ViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Post postItem = postItems.get(position);
        holder.title.setText(postItem.title);
        if(postItem.link != null) Picasso.get().load(postItem.link).into(holder.postImage);
        else holder.postImage.setVisibility(View.GONE);
        fetchUsername(postItem.userId, holder.username);
        holder.roleTV.setText("USER");
        getCreatedAt(postItem.createdAt,holder.createdAt);
        holder.commentCount.setText("200");
        //holder.commentCount.setText(postItem.commentCount+"");
//        holder.itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(context, PostActivity.class);
//                intent.putExtra("id",postItem.postId);
//                context.startActivity(intent);
//            }
//        });
    }

    private void getCreatedAt(String createdAt, TextView tv) {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            LocalDateTime time = LocalDateTime.parse(createdAt);
            tv.setText(TimeFormatter.formatter(time));
        }
    }

    private void fetchUsername(long userId, TextView usernameTV) {
        LocalAPI localAPI = RetrofitClient.localApi();
        localAPI.getUserById(userId).enqueue(new retrofit2.Callback<ApiResponse<User>>() {
            @Override
            public void onResponse(@NonNull retrofit2.Call<ApiResponse<User>> call,
                                   @NonNull retrofit2.Response<ApiResponse<User>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    usernameTV.setText(response.body().data.username);
                } else {
                    usernameTV.setText("Unknown");
                }
            }

            @Override
            public void onFailure(@NonNull retrofit2.Call<ApiResponse<User>> call,
                                  @NonNull Throwable t) {
                usernameTV.setText("Error");
            }
        });
    }


    @Override
    public int getItemCount() {
        return postItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        CircleImageView userDP;
        ImageView postImage;
        TextView username,createdAt,title,commentCount,roleTV;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            userDP = itemView.findViewById(R.id.profile_image_post_item);
            commentCount = itemView.findViewById(R.id.tv_comment_count);
            username = itemView.findViewById(R.id.tv_user_name_post_item);
            createdAt = itemView.findViewById(R.id.pub_date_tv_post_item);
            title = itemView.findViewById(R.id.title_post_item);
            postImage = itemView.findViewById(R.id.post_image_post_item);
            roleTV = itemView.findViewById(R.id.role_tv_post_item);
        }
    }
}
