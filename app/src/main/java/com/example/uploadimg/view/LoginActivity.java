package com.example.uploadimg.view;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.uploadimg.R;
import com.example.uploadimg.model.User;
import com.example.uploadimg.utility.AsyncResponse;
import com.example.uploadimg.utility.Util;
import com.example.uploadimg.viewmodel.LoginViewModel;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener, AsyncResponse {
    private static final String TAG = "LoginActivity";

    //variables
    LoginViewModel viewModel;

    //widgets
    EditText edtUsername;
    EditText edtPassword;
    Button btnLogin;
    Button btnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        viewModel = new ViewModelProvider(this).get(LoginViewModel.class);
        edtUsername = findViewById(R.id.edtUsername);
        edtPassword = findViewById(R.id.edtPassword);
        btnLogin = findViewById(R.id.btnLogin);
        btnRegister = findViewById(R.id.btnRegister);

        btnLogin.setOnClickListener(this);
        btnRegister.setOnClickListener(this);
    }

    @Override
    public void onAsyncProcessFinish(Object output) {
        Log.d(TAG, "onAsyncProcessFinish: ");

        User user = (User) output;
        if (user != null) {
            if (user.getPassword().equals(viewModel.password)) {
                Intent intent = new Intent(this, UploadImgActivity.class);
                startActivity(intent);
                finish();
            } else {
                Util.showSnackBar(this, "Incorrect Password");
            }
        } else {
            Util.showSnackBar(this, "User does not exist. Please register first");
        }
    }

    private boolean validateFields(String username, String password) {
        return !username.isEmpty() && !password.isEmpty();
    }

    @Override
    public void onClick(View v) {
        Log.d(TAG, "onClick: ");

        int id = v.getId();

        if (id == R.id.btnLogin) {
            //collapse soft keyboard
            View view = this.getCurrentFocus();
            if (view != null) {
                InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
            }
            //validating empty fields
            if (validateFields(edtUsername.getText().toString().trim(), edtPassword.getText().toString().trim())) {
                viewModel.password = edtPassword.getText().toString();
                viewModel.getUser(edtUsername.getText().toString(), this);
            } else {
                Util.showSnackBar(this, "Please fill all fields");
            }
        } else if (id == R.id.btnRegister) {
            Intent intent = new Intent(this, RegistrationActivity.class);
            startActivity(intent);
        }
    }
}
