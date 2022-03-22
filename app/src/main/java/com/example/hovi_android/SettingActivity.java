package com.example.hovi_android;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class SettingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        Button remove_btn = (Button) findViewById(R.id.remove_btn);

        remove_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Dialog
                AlertDialog.Builder builder = new AlertDialog.Builder(SettingActivity.this);

                builder.setTitle("Delete user data").setMessage("Choose an option.");

                builder.setPositiveButton("OK", new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialog, int id)
                    {
                        Toast.makeText(getApplicationContext(), "Complete", Toast.LENGTH_SHORT).show();
                        removeData();
                    }
                });

                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialog, int id)
                    {
                        Toast.makeText(getApplicationContext(), "Cancel", Toast.LENGTH_SHORT).show();
                    }
                });

                AlertDialog alertDialog = builder.create();
                alertDialog.show();

            }
        });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        Intent intent = new Intent(this, SplashActivity.class); //지금 액티비티에서 다른 액티비티로 이동하는 인텐트 설정
//        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP); //인텐트 플래그 설정 -> 더 알아보기
        startActivity(intent);  //인텐트 이동
        finish();   //현재 액티비티 종료
    }

    private void removeData(){
        SharedPreferences.Editor prefs = getSharedPreferences("user", MODE_PRIVATE).edit();
        SharedPreferences actioneSP = getSharedPreferences("action", MODE_PRIVATE);
        SharedPreferences.Editor actioneditor = actioneSP.edit();
        prefs.remove("userId");
        actioneditor.clear();
        prefs.commit();
        actioneditor.commit();
        Toast.makeText(getBaseContext(), "Remove user data in local", Toast.LENGTH_SHORT).show();
        //todo 서버에서도 기존 데이터 삭제
    }

}