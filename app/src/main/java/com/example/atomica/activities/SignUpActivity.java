package com.example.atomica.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.atomica.R;
import com.example.atomica.api.LocalAPI;
import com.example.atomica.databinding.ActivitySignUpBinding;
import com.example.atomica.responses.ApiResponse;
import com.example.atomica.responses.User;
import com.example.atomica.retroclients.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignUpActivity extends AppCompatActivity {

    ActivitySignUpBinding binding;
    View.OnClickListener onClickListener;
    LocalAPI localAPI;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySignUpBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(view.getId() == R.id.sign_up_btn_sign_up)
                {
                    boolean valid = true;

                    if(!verifyET(binding.nameSignUp)) valid = false;
                    if(!verifyET(binding.emailSignUp)) valid = false;
                    if(!verifyET(binding.passwordSignUp)) valid = false;

                    if(valid){
                        registerUser();
                    }
                }



            }
        };
        binding.signUpBtnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean valid = true;

                if(!verifyET(binding.nameSignUp)) valid = false;
                if(!verifyET(binding.emailSignUp)) valid = false;
                if(!verifyET(binding.passwordSignUp)) valid = false;

                if(valid){
                    registerUser();
                }
            }
        });


    }

    private void registerUser() {
        String name = binding.nameSignUp.getText().toString();
        String email = binding.emailSignUp.getText().toString();
        String password = binding.passwordSignUp.getText().toString();
        User user = new User();
        user.fullName = name;
        user.email = email;
        user.password = password;
        user.role = "ROLE";
        user.username = "";
        user.dp = null;
        user.about = null;
        localAPI = RetrofitClient.localApi();
        Call<ApiResponse>call = localAPI.registerUser(user);
        call.enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                if(response.isSuccessful() && response.body() != null)
                {
                    try{
                        User user = (User) response.body().data;
                        Toast.makeText(SignUpActivity.this, "User Id: " + user.id,
                                Toast.LENGTH_SHORT).show();
                    }catch (Exception e)
                    {
                        Toast.makeText(SignUpActivity.this, response.body().message,
                                Toast.LENGTH_LONG).show();
                    }
                }
                else
                {
                    Toast.makeText(SignUpActivity.this,response.message(),
                            Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ApiResponse> call, Throwable throwable) {
                Toast.makeText(SignUpActivity.this, throwable.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    private boolean verifyET(View view) {
        String text = ((EditText)view).getText().toString();
        if(text.isEmpty()){
            ((EditText)view).setError("Field cannot be empty");
            return false;
        }
        return true;
    }



}