package com.qsol.kistroapp.ui.user;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
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

public class ModifyActivity extends AppCompatActivity {

    private static final String TAG = "ModifyActivity";

    @BindView(R.id.input_UserId) EditText _IdText;
    @BindView(R.id.input_UserName) EditText _NameText;
    @BindView(R.id.input_UserPw) EditText _PwText;
    @BindView(R.id.input_UserAddress1) EditText _Address1Text;
    @BindView(R.id.input_UserAddress2) EditText _Address2Text;
    @BindView(R.id.input_UserZipcode) EditText _ZipcodeText;
    @BindView(R.id.input_UserEmail) EditText _EmailText;
    @BindView(R.id.input_UserHP) EditText _HPText;

    @BindView(R.id.btn_modify) Button _ModifyButton;

    UserVO userVO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify);

        ButterKnife.bind(this);

        UserVO UserInfo = (UserVO) getIntent().getSerializableExtra("UserInfo");
        userVO = (UserVO) getIntent().getSerializableExtra("UserInfo");

        Log.d("UserInfo", UserInfo.getUserID());

        _IdText.setText(UserInfo.getUserID());
        _NameText.setText(UserInfo.getUserName());
        _PwText.setText("");
        _Address1Text.setText(UserInfo.getUserAddress1());
        _Address2Text.setText(UserInfo.getUserAddress2());
        _ZipcodeText.setText(UserInfo.getZipcode());
        _EmailText.setText(UserInfo.getUserEmail());
        _HPText.setText(UserInfo.getUserHP());

        _ModifyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                modify();
            }
        });

    }

    public void modify() {
        Log.d(TAG, "Modify");

        String UserID = _IdText.getText().toString();
        String UserName = _NameText.getText().toString();
        String UserPW = _PwText.getText().toString();
        String UserAddress1 = _Address1Text.getText().toString();
        String UserAddress2 = _Address2Text.getText().toString();
        String ZIPCODE = _ZipcodeText.getText().toString();
        String UserEmail = _EmailText.getText().toString();
        String UserHP = _HPText.getText().toString();

        Call<UserVO> res = NetRetrofit.getInstance().getService().do_modify(UserID, UserPW, UserName, UserAddress1, UserAddress2, ZIPCODE, UserEmail, UserHP);
        // Log.d("ViewLoginCall", "User = " + UserID + UserPW + UserPW + UserAddress1 + UserAddress2 + Zipcode + UserEmail + UserHP);
        res.enqueue(new Callback<UserVO>() {
            @Override
            public void onResponse(Call<UserVO> call, Response<UserVO> response) {
                // Log.d("ViewLoginResponse", response.body().toString());

                UserVO result = response.body();
                Log.d("ModifyStatus", "Result = " + result.getResult());

                if (result.getResult().equals("Success")) {
                    // onLoginSuccess();
                    Toast.makeText(getBaseContext(), "회원정보 수정에 성공했습니다.", Toast.LENGTH_LONG).show();
                    Log.d("ModifyStatus", "ModifySuccess");
                    Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                    intent.putExtra("UserInfo", userVO);
                    startActivity(intent);
                } else {
                    // onLoginFailed();
                    Toast.makeText(getBaseContext(), "회원정보 수정에 실패했습니다.", Toast.LENGTH_LONG).show();
                    Log.d("ModifyStatus", "ModifyFail");
                    Intent intent = new Intent(getApplicationContext(), ModifyActivity.class);
                    intent.putExtra("UserInfo", userVO);
                    startActivity(intent);
                }

            }

            @Override
            public void onFailure(Call<UserVO> call, Throwable t) {
                Toast.makeText(getBaseContext(), "회원정보 수정에 실패했습니다.", Toast.LENGTH_LONG).show();
                Log.e("ErrModify", t.getMessage());
                Intent intent = new Intent(getApplicationContext(), ModifyActivity.class);
                intent.putExtra("UserInfo", userVO);
                startActivity(intent);
            }
        });

        /*if (!validate()) {
            onSignupFailed();
            return;
        }

        _signupButton.setEnabled(false);

        final ProgressDialog progressDialog = new ProgressDialog(SignupActivity.this,
                R.style.Theme_AppCompat_DayNight);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Creating Account...");
        progressDialog.show();

        String name = _NameText.getText().toString();
        String id = _IdText.getText().toString();
        String password = _PwText.getText().toString();

        // TODO: Implement your own signup logic here.

        new android.os.Handler().postDelayed(
                new Runnable() {
                    public void run() {
                        // On complete call either onSignupSuccess or onSignupFailed
                        // depending on success
                        // onSignupSuccess();
                        // onSignupFailed();
                        progressDialog.dismiss();
                    }
                }, 3000);*/
    }

}
