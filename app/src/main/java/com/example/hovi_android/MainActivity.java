package com.example.hovi_android;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hovi_android.databinding.ActivityMainBinding;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    // Used to load the 'hovi_android' library on application startup.
    static {
        System.loadLibrary("hovi_android");
    }

    private ActivityMainBinding binding;

    Retrofit retrofit;
    RetrofitAPI retrofitAPI;
    TextView txt_action1, txt_action2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Example of a call to a native method
//        TextView tv = binding.sampleText;
//        tv.setText(stringFromJNI());

        Button check_btn = binding.checkBtn;
        Button revise_btn = binding.reviseBtn;
        txt_action1 = binding.txtAction1;
        txt_action2 = binding.txtAction2;

        Button remove_btn = binding.removeBtn;

        retrofit = new Retrofit.Builder()
                .baseUrl("http://15.164.218.200:8080/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        retrofitAPI = retrofit.create(RetrofitAPI.class);

        SharedPreferences sharedPreferences= getSharedPreferences("user", MODE_PRIVATE);
        String save_user = sharedPreferences.getString("userId","");
        if(save_user == null || save_user == ""){

            String user_id = getuniqueid(); //생성
            SharedPreferences.Editor editor= sharedPreferences.edit(); 
            editor.putString("userId", user_id);
            editor.commit();
            Toast.makeText(this, "Welcome!", Toast.LENGTH_SHORT).show();

            User user = new User();
            startJoin(user, user_id);
            getAction(user_id);

        }else{
            User user = new User();
            startJoin(user, save_user);
            getAction(save_user);
        }

        check_btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, EyeActivity.class);
                startActivity(intent);
            }
        });

        revise_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ReviseActivity.class);
                startActivity(intent);
            }
        });

        remove_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences.Editor prefs = getSharedPreferences("user", MODE_PRIVATE).edit();
                prefs.remove("userId");
                prefs.commit();
                Toast.makeText(getBaseContext(), "Remove data", Toast.LENGTH_SHORT).show();
            }
        });




    }


    private String getuniqueid(){
        String android_id = Settings.Secure.getString(this.getContentResolver(), Settings.Secure.ANDROID_ID);
        return android_id;
    }

    private void startJoin(User user, String user_id){
        retrofitAPI.checkData(user_id).enqueue(new Callback<Check>() {
            @Override
            public void onResponse(Call<Check> call, Response<Check> response) {
                if (response.isSuccessful()) {
                    Check check = response.body();
                    if (check.getdata() == true) {
                        Log.d("데이터있음", "성공");
                    } else{
                        Log.d("데이터없음", user_id);
                        setUserInfo(user_id);
                    }

                }
            }

            @Override
            public void onFailure(Call<Check> call, Throwable t) {
                Log.d("데이터", "실실패" + t);
            }
        });
    }

    private void setUserInfo(String user_id){
        retrofitAPI.setUser(user_id).enqueue(new Callback<User>() {

            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                Log.e("post", String.valueOf(response));
                if (response.isSuccessful()) {
                    User user = response.body();
                    Integer status = user.getStatus();
                    Log.d("set사용자", user.toString());
                    Log.d("set사용자1", status.toString());
                }
                Log.d("set사용자 실패",  response.body().toString());
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Log.d("set사용자", "리스폰실패");
                t.printStackTrace();
            }
        });
    }

        private void getAction(String user_id){

        retrofitAPI.getActions(user_id).enqueue(new Callback<Action>(){

            @Override
            public void onResponse(Call<Action> call, Response<Action> response) {
                if (response.isSuccessful()) {
                    Action message = response.body();
                    if (message.getMes() != null) {
                        Log.d("액션", message.getMes());
                        List<ActionList> actions = message.getActions();
                        String action1 = actions.get(0).getActionBody();
                        String action2 = actions.get(1).getActionBody();
                        Log.d("액션들",action1);
                    } else{
                        Log.d("액션없음", message.toString());
                    }

                }
            }

            @Override
            public void onFailure(Call<Action> call, Throwable t) {
                Log.d("액션리스폰실패", t.toString());
            }
        });
    }



    /**
     * A native method that is implemented by the 'hovi_android' native library,
     * which is packaged with this application.
     */
    public native String stringFromJNI();
}