package com.example.helloworld;

import android.graphics.Color;
import android.os.Bundle;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Login#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Login extends Fragment {
    public Login() {
    }

    public static Login newInstance() {
        Login fragment = new Login();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        setListenersOnInputs(view);
        setListenerOnButton(view);
        return view;
    }

    public void checkText(View view) {
        String loginValue = getLoginValue();
        String passwordValue = getPasswordValue();

        if(loginValue.equals(BuildConfig.USER_LOGIN) && passwordValue.equals(BuildConfig.USER_PASSWORD)) {
            getLoginMessage(true);
            drawBottomBorder(true);
            Navigation.findNavController(view).navigate(R.id.nav_to_myLocation);
        } else {
            getLoginMessage(false);
            drawBottomBorder(false);
        }
    }

    private String getLoginValue() {
        EditText loginText = getView().findViewById(R.id.loginInput);

        return loginText.getText().toString();
    }

    private String getPasswordValue() {
        EditText passwordText = getView().findViewById(R.id.passwordInput);

        return passwordText.getText().toString();
    }

    private void getLoginMessage(boolean success) {
        TextView alertTextView = getView().findViewById(R.id.errorMessage);

        alertTextView.setVisibility(View.VISIBLE);
        alertTextView.setText(success ? R.string.loginSuccess : R.string.loginError);
        alertTextView.setTextColor(success ? Color.GREEN: Color.RED);
    }

    private void drawBottomBorder(boolean success) {
        EditText loginText = getView().findViewById(R.id.loginInput);
        EditText passwordText = getView().findViewById(R.id.passwordInput);

        loginText.setBackgroundTintList(ContextCompat.getColorStateList(getActivity().getApplicationContext(), success ? R.color.grey : R.color.red));
        passwordText.setBackgroundTintList(ContextCompat.getColorStateList(getActivity().getApplicationContext(), success ? R.color.grey : R.color.red));
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

    private void setListenersOnInputs(View view) {
        EditText loginText = view.findViewById(R.id.loginInput);
        EditText passwordText = view.findViewById(R.id.passwordInput);

        setListenerOnTextChange(loginText, passwordText);
        setListenerOnTextChange(passwordText, loginText);
    }

    private void setActiveButton(boolean isEnabled) {
        Button loginBtn = getView().findViewById(R.id.loginBtn);

        loginBtn.setEnabled(isEnabled);
    }

    private void setListenerOnButton(View view) {
        Button btn = (Button) view.findViewById(R.id.loginBtn);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkText(view);
            }
        });
    }
}