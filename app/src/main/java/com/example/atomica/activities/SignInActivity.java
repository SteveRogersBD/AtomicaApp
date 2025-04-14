package com.example.atomica.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.atomica.MainActivity;
import com.example.atomica.R;
import com.example.atomica.api.LocalAPI;
import com.example.atomica.databinding.ActivitySignInBinding;
import com.example.atomica.responses.ApiResponse;
import com.example.atomica.responses.User;
import com.example.atomica.retroclients.RetrofitClient;
import com.example.atomica.roomDB.UserDB;

import java.util.concurrent.Executors;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignInActivity extends AppCompatActivity {

    ActivitySignInBinding binding;
    LocalAPI localAPI;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySignInBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.signInBtnSignIn.setOnClickListener(v -> {
            doSignIn();
        });
    }

    private void doSignIn() {
        String email = binding.emailSignIn.getText().toString();
        String password = binding.passwordSignIn.getText().toString();
        if (email.isEmpty()) {
            binding.emailSignIn.setError("Email is required");
            return;
        }
        if (password.isEmpty()) {
            binding.passwordSignIn.setError("Password is required");
            return;
        }
        localAPI = RetrofitClient.localApi();
        Call<ApiResponse> call = localAPI.loginUser(email,password);
        call.enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                try{
                    if(response.isSuccessful() && response.body()!=null)
                    {
                        String token = response.body().data.toString();
                        storeToken(token);
                        retrieveAndStoreUser(token);
                        startActivity(new Intent(SignInActivity.this, MainActivity.class));
                    }
                    else{
                        Toast.makeText(SignInActivity.this, response.message(),
                                Toast.LENGTH_SHORT).show();
                    }
                }catch (Exception e)
                {
                    Toast.makeText(SignInActivity.this, e.getMessage(),
                            Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<ApiResponse> call, Throwable throwable) {
                Toast.makeText(SignInActivity.this, throwable.getMessage(),
                        Toast.LENGTH_SHORT).show();
            }
        });
    }


    private void storeToken(String token) {
        SharedPreferences sp = getSharedPreferences("JWT_TOKEN",MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("token",token);
        editor.apply();
    }

    private void retrieveAndStoreUser(String token) {
        String breaerToken = "Bearer "+token;
        Call<ApiResponse<User>>call = localAPI.getUser(breaerToken);
        call.enqueue(new Callback<ApiResponse<User>>() {
            @Override
            public void onResponse(Call<ApiResponse<User>> call, Response<ApiResponse<User>> response) {
                try{
                    if(response.isSuccessful() && response.body()!=null)
                    {
                        User user = response.body().data;
                        UserDB rm = Room.databaseBuilder(SignInActivity.this,
                                UserDB.class, "USERDB").build();
                        com.example.atomica.roomDB.User myUser = new com.example.atomica.roomDB.User();
                        myUser.id= user.id;
                        myUser.fullName = user.fullName;
                        myUser.username = user.username;
                        myUser.email = user.email;
                        myUser.password = user.password;
                        myUser.role = user.role;
                        myUser.dp = user.dp;
                        myUser.about = user.about;
                        myUser.createdAt = user.createdAt;

                        Executors.newSingleThreadExecutor().execute(() -> {
                            rm.userDao().insertUser(myUser);
                            runOnUiThread(() -> {
                                Toast.makeText(SignInActivity.this, "User saved to DB",
                                        Toast.LENGTH_LONG).show();
                            });
                        });
                        Toast.makeText(SignInActivity.this, myUser.email,
                                Toast.LENGTH_LONG).show();
                    }
                    else{
                        Toast.makeText(SignInActivity.this, response.message(),
                                Toast.LENGTH_LONG).show();
                        Log.e("Else block",response.message());
                    }
                }catch (Exception e)
                {
                    Toast.makeText(SignInActivity.this, e.getMessage(),
                            Toast.LENGTH_LONG).show();
                    Log.e("Catch block",e.getMessage());
                }
            }

            @Override
            public void onFailure(Call<ApiResponse<User>> call, Throwable throwable) {
                Log.e("onFailure",throwable.getMessage());
            }
        });
    }
}