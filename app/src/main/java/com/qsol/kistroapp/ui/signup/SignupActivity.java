package com.qsol.kistroapp.ui.signup;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.qsol.kistroapp.R;
import com.qsol.kistroapp.data.VO.UserVO;
import com.qsol.kistroapp.retrofit.NetRetrofit;
import com.qsol.kistroapp.ui.home.HomeActivity;
import com.qsol.kistroapp.ui.login.LoginActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignupActivity extends AppCompatActivity {
    private static final String TAG = "SignupActivity";

    @BindView(R.id.input_UserId) EditText _IdText;
    @BindView(R.id.input_UserName) EditText _NameText;
    @BindView(R.id.input_UserPw) EditText _PwText;
    @BindView(R.id.input_UserAddress1) EditText _Address1Text;
    @BindView(R.id.input_UserAddress2) EditText _Address2Text;
    @BindView(R.id.input_UserZipcode) EditText _ZipcodeText;
    @BindView(R.id.input_UserEmail) EditText _EmailText;
    @BindView(R.id.input_UserHP) EditText _HPText;

    @BindView(R.id.btn_signup) Button _signupButton;
    @BindView(R.id.link_login) TextView _loginLink;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        ButterKnife.bind(this);

        _signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signup();
            }
        });

        _loginLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Finish the registration screen and return to the Login activity
                finish();
            }
        });
    }

    public void signup() {
        Log.d(TAG, "SignUp");

        String UserID = _IdText.getText().toString();
        String UserName = _NameText.getText().toString();
        String UserPW = _PwText.getText().toString();
        String UserAddress1 = _Address1Text.getText().toString();
        String UserAddress2 = _Address2Text.getText().toString();
        String ZIPCODE = _ZipcodeText.getText().toString();
        String UserEmail = _EmailText.getText().toString();
        String UserHP = _HPText.getText().toString();

        Log.d("UserPW", UserPW);

        Call<UserVO> res = NetRetrofit.getInstance().getService().do_signup(UserID, UserPW, UserName, UserAddress1, UserAddress2, ZIPCODE, UserEmail, UserHP);
        // Log.d("ViewLoginCall", "User = " + UserID + UserPW + UserPW + UserAddress1 + UserAddress2 + Zipcode + UserEmail + UserHP);
        res.enqueue(new Callback<UserVO>() {
            @Override
            public void onResponse(Call<UserVO> call, Response<UserVO> response) {
                // Log.d("ViewLoginResponse", response.body().toString());

                UserVO result = response.body();
                Log.d("SignUpStatus", "Result = " + result.getResult());

                if (result.getResult().equals("Success")) {
                    // onLoginSuccess();
                    Toast.makeText(getBaseContext(), "회원가입에 성공했습니다.", Toast.LENGTH_LONG).show();
                    Log.d("SignUpStatus", "SignUpSuccess");
                    Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                    startActivity(intent);
                } else {
                    // onLoginFailed();
                    Toast.makeText(getBaseContext(), "회원가입에 실패했습니다.", Toast.LENGTH_LONG).show();
                    Log.d("SignUpStatus", "SignUpFail");
                    Intent intent = new Intent(getApplicationContext(), SignupActivity.class);
                    startActivity(intent);
                }

            }

            @Override
            public void onFailure(Call<UserVO> call, Throwable t) {
                Toast.makeText(getBaseContext(), "회원가입에 실패했습니다.", Toast.LENGTH_LONG).show();
                Log.e("ErrSignUp", t.getMessage());
                Intent intent = new Intent(getApplicationContext(), SignupActivity.class);
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


    /*public void onSignupSuccess() {
        _signupButton.setEnabled(true);
        setResult(RESULT_OK, null);
        finish();
    }

    public void onSignupFailed() {
        Toast.makeText(getBaseContext(), "회원가입 실패", Toast.LENGTH_LONG).show();

        _signupButton.setEnabled(true);
    }

    public boolean validate() {
        boolean valid = true;

        String name = _NameText.getText().toString();
        String id = _IdText.getText().toString();
        String password = _PwText.getText().toString();

        if (name.isEmpty() || name.length() < 2) {
            _NameText.setError("유효한 이름을 입력해주세요.");
            valid = false;
        } else {
            _NameText.setError(null);
        }

        if (id.isEmpty() || id.length() < 4) {
            _IdText.setError("유효한 아이디를 입력해주세요.");
            valid = false;
        } else {
            _IdText.setError(null);
        }

        if (password.isEmpty() || password.length() < 4 || password.length() > 10) {
            _PwText.setError("4-10 글자의 알파벳을 입력해주세요.");
            valid = false;
        } else {
            _PwText.setError(null);
        }

        return valid;
    }*/
}