package com.example.uploadimg.view;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import com.example.uploadimg.R;
import com.example.uploadimg.databinding.ActivityRegistrationBinding;
import com.example.uploadimg.model.User;
import com.example.uploadimg.utility.AsyncResponse;
import com.example.uploadimg.utility.Util;
import com.example.uploadimg.viewmodel.LoginViewModel;

public class RegistrationActivity extends AppCompatActivity implements View.OnClickListener, AsyncResponse {
    private static final String TAG = "RegistrationActivity";

    //variables
    User user;
    private LoginViewModel viewModel;
    private ActivityRegistrationBinding binding;

    //widgets
    Button btnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_registration);
        user = new User(null, "", null, null, null, null);
        binding.setUser(user);

        viewModel = new ViewModelProvider(this).get(LoginViewModel.class);
        btnRegister = findViewById(R.id.btnRegister);

        btnRegister.setOnClickListener(this);
    }

    @Override
    public void onAsyncProcessFinish(Object output) {
        Log.d(TAG, "onAsyncProcessFinish: ");

        User user = (User) output;

        if (user == null) {
            viewModel.insert(this.user);
            Intent intent = new Intent(this, LoginActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        } else {
            Util.showSnackBar(this, "Email id/Phone is already registered.");
        }
    }

    private boolean validateFields(User user) {
        return !user.getName().isEmpty() && !user.getEmailId().isEmpty() && !user.getPhoneNo().isEmpty() && !user.getState().isEmpty() && !user.getCity().isEmpty() && !user.getPassword().isEmpty();
    }

    @Override
    public void onClick(View v) {
        Log.d(TAG, "onClick: ");

        int id = v.getId();

        if (id == R.id.btnRegister) {
            //collapse soft keyboard
            View view = this.getCurrentFocus();
            if (view != null) {
                InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
            }
            //validating empty fields
            if (validateFields(user)) {
                viewModel.getDuplicate(user.getEmailId(), user.getPhoneNo(), this);
            }
        }
    }
}
