package com.example.helloworld;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RectShape;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setListenersOnInputs();
    }

    public void checkText(View view) {
        String loginValue = getLoginValue();
        String passwordValue = getPasswordValue();

        if(loginValue.equals("admin") && passwordValue.equals("admin")) {
            getLoginMessage(true);
            drawBottomBorder(true);
        } else {
            getLoginMessage(false);
            drawBottomBorder(false);
        }
    }

    private String getLoginValue() {
        EditText loginText = findViewById(R.id.loginInput);

        return loginText.getText().toString();
    }

    private String getPasswordValue() {
        EditText passwordText = findViewById(R.id.passwordInput);

        return passwordText.getText().toString();
    }

    private void getLoginMessage(boolean success) {
        TextView alertTextView = findViewById(R.id.errorMessage);

        alertTextView.setVisibility(View.VISIBLE);
        alertTextView.setText(success ? R.string.loginSuccess : R.string.loginError);
        alertTextView.setTextColor(success ? Color.GREEN: Color.RED);
    }

    private void drawBottomBorder(boolean success) {
        EditText loginText = findViewById(R.id.loginInput);
        EditText passwordText = findViewById(R.id.passwordInput);

        loginText.setBackgroundTintList(ContextCompat.getColorStateList(getApplicationContext(), success ? R.color.grey : R.color.red));
        passwordText.setBackgroundTintList(ContextCompat.getColorStateList(getApplicationContext(), success ? R.color.grey : R.color.red));
    }

    private void setListenerOnTextChange(EditText field, EditText field1) {
        field.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {}

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            public void onTextChanged(CharSequence s, int start, int before, int count) {
                boolean result = field.getText().toString().length() > 0 && field1.getText().toString().length() > 0;
                setActiveButton(result);
            }
        });
    }

    private void setListenersOnInputs() {
        EditText loginText = findViewById(R.id.loginInput);
        EditText passwordText = findViewById(R.id.passwordInput);

        setListenerOnTextChange(loginText, passwordText);
        setListenerOnTextChange(passwordText, loginText);
    }

    private void setActiveButton(boolean isEnabled) {
        Button loginBtn = findViewById(R.id.loginBtn);

        loginBtn.setEnabled(isEnabled);
    }

}