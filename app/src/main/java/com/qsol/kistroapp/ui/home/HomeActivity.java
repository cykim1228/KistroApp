package com.qsol.kistroapp.ui.home;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.qsol.kistroapp.R;
import com.qsol.kistroapp.data.VO.UserVO;
import com.qsol.kistroapp.retrofit.NetRetrofit;
import com.qsol.kistroapp.ui.login.LoginActivity;
import com.qsol.kistroapp.ui.pay.QrActivity;
import com.qsol.kistroapp.ui.user.UserActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeActivity extends AppCompatActivity {

    Button btn_userInfo;
    Button btn_createQR;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        TextView textView = (TextView) findViewById(R.id.textView);
        UserVO UserInfo = (UserVO) getIntent().getSerializableExtra("UserInfo");
        textView.setText(UserInfo.getUserID());
        btn_createQR = (Button) findViewById(R.id.btn_createQR);


        String UserID = UserInfo.getUserID();

        Call<UserVO> res = NetRetrofit.getInstance().getService().do_select(UserID);

        res.enqueue(new Callback<UserVO>() {
            @Override
            public void onResponse(Call<UserVO> call, Response<UserVO> response) {
                // Log.d("ViewLoginResponse", response.body().toString());
                UserVO result = response.body();

                btn_userInfo = (Button)findViewById(R.id.btn_userInfo);

                btn_userInfo.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(getApplicationContext(), UserActivity.class);
                        intent.putExtra("UserInfo", result);
                        startActivity(intent);
                    }
                });

            }

            @Override
            public void onFailure(Call<UserVO> call, Throwable t) {
                Toast.makeText(getBaseContext(), "정보 불러오기를 실패했습니다.", Toast.LENGTH_LONG).show();
                Log.e("ErrLogin", t.getMessage());
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
            }
        });

        btn_createQR.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent intent = new Intent(HomeActivity.this, QrActivity.class);
                startActivity(intent);
            }
        });

    }
}
