package com.example.hovi_android;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ReviseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_revise);

        EditText edit_action1 = (EditText) findViewById(R.id.edit_action1);
        EditText edit_action2 = (EditText) findViewById(R.id.edit_action2);
        Button save_action = (Button) findViewById(R.id.btn_save);

        Button more_action = (Button) findViewById(R.id.btn_more); //todo 액션 추가

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://15.164.218.200:8080/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        RetrofitAPI retrofitAPI = retrofit.create(RetrofitAPI.class);

        save_action.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String action1, action2;

                String getEdit1 = edit_action1.getText().toString();
                String getEdit2 = edit_action2.getText().toString();

                getEdit1 = getEdit1.trim();
                getEdit2 = getEdit2.trim();

                if (getEdit1.getBytes().length <= 0 && getEdit2.getBytes().length <= 0) {

                    Toast.makeText(getBaseContext(), "값을 입력하세요.", Toast.LENGTH_SHORT).show();
                } else {
                    //액션1과 액션2 넘기기
                    action1 = getEdit1;
                    action2 = getEdit2;
                    Toast.makeText(getBaseContext(), "Action1 : " + action1 + "  Action2 : " + action2, Toast.LENGTH_SHORT).show();

                    SharedPreferences sharedPreferences= getSharedPreferences("user", MODE_PRIVATE);
                    String save_user = sharedPreferences.getString("userId","");

                    Update update1 = new Update();
                    update1.setAction(action1);
                    update1.setDeviceId(save_user);
                    update1.setActionNum(1);

                    Update update2 = new Update();
                    update2.setAction(action2);
                    update2.setDeviceId(save_user);
                    update2.setActionNum(2);

                    retrofitAPI.updateData(update1).enqueue(new Callback<UpdateResponse>() {

                        @Override
                        public void onResponse(Call<UpdateResponse> call, Response<UpdateResponse> response) {
                            if (response.isSuccessful()) {
                                UpdateResponse updateresponse = response.body();
                                Log.d("액션1", updateresponse.getMes());
                                } else{
                                    Log.d("액션1업데이트 오류", response.body().toString());
                                }

                            }

                        @Override
                        public void onFailure(Call<UpdateResponse> call, Throwable t) {
                            Log.d("액션1업뎃리스폰 오류", t.toString());
                        }
                    });

                    retrofitAPI.updateData(update2).enqueue(new Callback<UpdateResponse>() {

                        @Override
                        public void onResponse(Call<UpdateResponse> call, Response<UpdateResponse> response) {
                            if (response.isSuccessful()) {
                                UpdateResponse updateresponse = response.body();
                                Log.d("액션2", updateresponse.getMes());
                            } else{
                                Log.d("액션2업데이트 오류", response.body().toString());
                            }

                        }

                        @Override
                        public void onFailure(Call<UpdateResponse> call, Throwable t) {
                            Log.d("액션2업뎃리스폰 오류", t.toString());
                        }
                    });

                    Intent intent = new Intent(ReviseActivity.this, EyeActivity.class);
                    SharedPreferences actionSP = getSharedPreferences("action", MODE_PRIVATE);
                    SharedPreferences.Editor editor = actionSP.edit();
                    editor.putString("action1", action1);
                    editor.putString("action2", action2);
                    editor.commit();
//                    intent.putExtra("action1",action1);
//                    intent.putExtra("action2",action2);
                    startActivity(intent);
                    finish();

                }
            }
        });
    }
}
