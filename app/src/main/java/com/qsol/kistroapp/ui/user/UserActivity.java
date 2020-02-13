package com.qsol.kistroapp.ui.user;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.qsol.kistroapp.R;
import com.qsol.kistroapp.data.VO.UserVO;
import com.qsol.kistroapp.retrofit.NetRetrofit;
import com.qsol.kistroapp.ui.home.HomeActivity;
import com.qsol.kistroapp.ui.login.LoginActivity;
import com.qsol.kistroapp.ui.signup.SignupActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserActivity extends AppCompatActivity {

    @BindView(R.id.userId) TextView _IdText;
    @BindView(R.id.userName) TextView _NameText;
    @BindView(R.id.userAddress1) TextView _Address1Text;
    @BindView(R.id.userAddress2) TextView _Address2Text;
    @BindView(R.id.userZipcode) TextView _ZipcodeText;
    @BindView(R.id.userEmail) TextView _EmailText;
    @BindView(R.id.userHP) TextView _HPText;

    @BindView(R.id.link_modify) ImageView _modifyLink;
    @BindView(R.id.btn_signout) Button _signoutBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        ButterKnife.bind(this);

        UserVO UserInfo = (UserVO) getIntent().getSerializableExtra("UserInfo");

        _IdText.setText(UserInfo.getUserID());
        _NameText.setText(UserInfo.getUserName());
        _Address1Text.setText(UserInfo.getUserAddress1());
        _Address2Text.setText(UserInfo.getUserAddress2());
        _ZipcodeText.setText(UserInfo.getZipcode());
        _EmailText.setText(UserInfo.getUserEmail());
        _HPText.setText(UserInfo.getUserHP());

        _modifyLink.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // Start the Signup activity
                Toast.makeText(getBaseContext(), "회원 정보 수정", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(getApplicationContext(), ModifyActivity.class);
                intent.putExtra("UserInfo", UserInfo);
                startActivity(intent);
            }
        });

        _signoutBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // Start the Signup activity

                String UserID = UserInfo.getUserID();

                AlertDialog.Builder builder = new AlertDialog.Builder(UserActivity.this);
                builder.setTitle("회원 탈퇴");
                builder.setMessage("회원을 탈퇴 하시겠습니까?");
                builder.setPositiveButton("예", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Call<UserVO> res = NetRetrofit.getInstance().getService().do_signout(UserID);

                        res.enqueue(new Callback<UserVO>() {
                            @Override
                            public void onResponse(Call<UserVO> call, Response<UserVO> response) {
                                // Log.d("ViewLoginResponse", response.body().toString());
                                UserVO result = response.body();
                                Toast.makeText(getBaseContext(), "회원 탈퇴를 완료했습니다.", Toast.LENGTH_LONG).show();
                                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                                startActivity(intent);
                            }

                            @Override
                            public void onFailure(Call<UserVO> call, Throwable t) {
                                Toast.makeText(getBaseContext(), "회원 탈퇴를 실패했습니다.", Toast.LENGTH_LONG).show();
                                Log.e("ErrLogin", t.getMessage());
                                Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                                intent.putExtra("UserInfo", UserInfo);
                                startActivity(intent);
                            }
                        });
                    }
                });

                builder.setNegativeButton("아니오", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(getBaseContext(), "회원 탈퇴를 취소했습니다.", Toast.LENGTH_LONG).show();
                    }
                });
                AlertDialog alert = builder.create();
                alert.show();

            }
        });

    }
}
