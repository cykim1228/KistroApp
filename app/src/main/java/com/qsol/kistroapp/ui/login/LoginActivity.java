package com.qsol.kistroapp.ui.login;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;
import com.google.gson.JsonParser;
import com.qsol.kistroapp.R;
import com.qsol.kistroapp.data.VO.UserVO;
import com.qsol.kistroapp.retrofit.NetRetrofit;
import com.qsol.kistroapp.ui.home.HomeActivity;
import com.qsol.kistroapp.ui.menu.MenuActivity;
import com.qsol.kistroapp.ui.signup.SignupActivity;

import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    private static final String TAG = "LoginActivity";
    private static final int REQUEST_SIGNUP = 0;
    private Gson mGson;

    @BindView(R.id.input_id)
    EditText _idText;
    @BindView(R.id.input_password)
    EditText _passwordText;
    @BindView(R.id.btn_login)
    Button _loginButton;
    @BindView(R.id.link_signup)
    TextView _signupLink;
    @BindView(R.id.switch_autologin)
    Switch _autoLogin;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        _loginButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                login();
            }
        });

        _signupLink.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // Start the Signup activity
                Intent intent = new Intent(getApplicationContext(), SignupActivity.class);
                startActivityForResult(intent, REQUEST_SIGNUP);
            }
        });
    }

    public void login() {
        Log.d(TAG, "Login");

        if (!validate()) {
            onLoginFailed();
            return;
        }

        _loginButton.setEnabled(false);

        String UserID = _idText.getText().toString();
        String UserPW = _passwordText.getText().toString();
        // String UseToken = _autoLogin.getTextOff().toString();
        String UseToken = "FALSE";

        Call<UserVO> res = NetRetrofit.getInstance().getService().do_login(UserID, UserPW, UseToken);
        Log.d("ViewLoginCall", "User = " + UserID + UserPW + UseToken);
        res.enqueue(new Callback<UserVO>() {
            @Override
            public void onResponse(Call<UserVO> call, Response<UserVO> response) {
                // Log.d("ViewLoginResponse", response.body().toString());

                UserVO result = response.body();

                final ProgressDialog progressDialog = new ProgressDialog(LoginActivity.this);
                progressDialog.setIndeterminate(true);
                progressDialog.setMessage("로그인중입니다...");
                progressDialog.setProgressStyle(android.R.style.Widget_ProgressBar_Horizontal);
                progressDialog.show();

                new android.os.Handler().postDelayed(
                        new Runnable() {
                            public void run() {
                                if (result.getUserID() != null) {
                                    onLoginSuccess();
                                    Log.d("LoginStatus", "LoginSuccess");
                                } else {
                                    onLoginFailed();
                                    Log.d("LoginStatus", "LoginFail");
                                }
                                // On complete call either onLoginSuccess or onLoginFailed

                                // onLoginFailed();
                                progressDialog.dismiss();
                            }
                        }, 3000);

                if (result.getUserID() != null) {
                    Toast.makeText(getBaseContext(), "로그인을 성공했습니다.", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                    intent.putExtra("UserInfo", result);
                    startActivity(intent);
                } else {
                    Toast.makeText(getBaseContext(), "로그인을 실패했습니다.", Toast.LENGTH_LONG).show();
                    Log.d("LoginFailure", "LoginID NULL");
                    Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                    startActivity(intent);
                }

            }

            @Override
            public void onFailure(Call<UserVO> call, Throwable t) {
                Toast.makeText(getBaseContext(), "로그인을 실패했습니다.", Toast.LENGTH_LONG).show();
                Log.e("ErrLogin", t.getMessage());
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
            }
        });

    }

    @Override
    public void onBackPressed() {
        // disable going back to the MainActivity
        moveTaskToBack(true);
    }

    public void onLoginSuccess() {
        _loginButton.setEnabled(true);
    }

    public void onLoginFailed() {
        _loginButton.setEnabled(true);
    }

    public boolean validate() {
        boolean valid = true;

        String id = _idText.getText().toString();
        String password = _passwordText.getText().toString();

        if (id.isEmpty()) {
            _idText.setError("아이디를 입력해주세요.");
            valid = false;
        } else {
            _idText.setError(null);
        }

        if (password.isEmpty() || password.length() < 4 || password.length() > 10) {
            _passwordText.setError("비밀번호는 4~10 글자의 알파벳으로 이루어져있습니다.");
            valid = false;
        } else {
            _passwordText.setError(null);
        }

        return valid;
    }
}