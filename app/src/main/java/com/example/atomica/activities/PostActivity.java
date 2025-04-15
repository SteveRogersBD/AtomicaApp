package com.example.atomica.activities;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.atomica.R;
import com.example.atomica.TimeFormatter;
import com.example.atomica.api.LocalAPI;
import com.example.atomica.databinding.ActivityPostBinding;
import com.example.atomica.responses.ApiResponse;
import com.example.atomica.responses.Post;
import com.example.atomica.retroclients.RetrofitClient;
import com.squareup.picasso.Picasso;

import java.time.LocalDateTime;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PostActivity extends AppCompatActivity {

    ActivityPostBinding binding;
    LocalAPI localAPI;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPostBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //get the post id from the intent
        long postId = getIntent().getLongExtra("id",0);
        //fetch the post from the server
        localAPI = RetrofitClient.localApi();
        localAPI.getPostById(postId).enqueue(new Callback<ApiResponse<Post>>() {
            @Override
            public void onResponse(Call<ApiResponse<Post>> call, Response<ApiResponse<Post>> response) {
                if(response.isSuccessful() && response.body() != null)
                {
                    try{
                        Toast.makeText(PostActivity.this, response.body().message,
                                Toast.LENGTH_SHORT).show();
                        runOnUiThread(
                                () -> {updateUI(response.body());}
                        );


                    }catch (Exception e)
                    {
                        Log.e("Catch",e.getLocalizedMessage());
                    }
                }
                else{

                }
            }

            @Override
            public void onFailure(Call<ApiResponse<Post>> call, Throwable throwable) {

            }
        });

        binding.sendButtonPA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String content = binding.messageInputPA.getText().toString();
                if(content.isEmpty()) return;

                binding.messageInputPA.setText("");


            }
        });
    }
    private void updateUI(ApiResponse<Post> post) {
        if(post.data.link != null) Picasso.get().load(post.data.link).into(binding.postImagePA);
        else binding.postImagePA.setVisibility(View.GONE);
//        Picasso.get().load(post.data.user.dp).into(binding.profileImagePA);
//        binding.usernamePA.setText(post.data.user.username);
//        binding.rolePA.setText(post.data.user.role);
        getPostDate(binding.postDatePA,post.data.createdAt);
        binding.captionPA.setText(post.data.title);
        binding.descriptionPA.setText(post.data.content);
//        comments = new ArrayList<>();
//        comments.addAll(post.data.comments);
//        commentAdapter = new CommentAdapter(PostActivity.this,comments);
//        binding.commentRecycler.setAdapter(commentAdapter);
//        binding.commentRecycler.setLayoutManager(new LinearLayoutManager(PostActivity.this));

    }


    private void getPostDate(TextView postDatePA, String createdAt) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            postDatePA.setText(TimeFormatter.formatter(LocalDateTime.parse(createdAt)));
        }
    }
}