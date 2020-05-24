package com.iwa.iwatesting.bindingadapter;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Button;

import androidx.databinding.BindingAdapter;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CustomBindingAdapter {

    public static final Pattern VALID_EMAIL_ADDRESS_REGEX =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

    @BindingAdapter("app:validate")
    public static void validate(TextInputEditText view, final int type) {
        final TextInputLayout name = view.getRootView().findViewWithTag("NAME_TEXT_FIELD");
        final TextInputLayout email = view.getRootView().findViewWithTag("EMAIL_TEXT_FIELD");
        final TextInputLayout confirmEmail = view.getRootView().findViewWithTag("CONFIRM_EMAIL_TEXT_FIELD");
        final TextInputLayout pass = view.getRootView().findViewWithTag("PASS_TEXT_FIELD");
        final TextInputLayout confirmPass = view.getRootView().findViewWithTag("CONFIRM_PASS_TEXT_FIELD");

        final Button btnRegister = view.getRootView().findViewWithTag("REGISTER_BUTTON");
        view.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                switch (type) {
                    case 1: {
                        if (!validEmail(editable.toString())) enableError(email);
                        else {
                            disableError(email);
                            confirmEmail.getEditText().setEnabled(true);
                        }
                        if (editable.toString().isEmpty()) {
                            confirmEmail.getEditText().setText("");
                            enableError(email);
                        }
                        break;
                    }
                    case 2: {
                        if (!isValidConfirmEmail(email.getEditText().getText().toString(), editable.toString()))
                            enableError(confirmEmail);
                        else disableError(confirmEmail);
                        break;
                    }
                    case 3: {
                        if (!validPass(editable.toString())) {
                            enableError(pass);
                        } else {
                            disableError(pass);
                            confirmPass.getEditText().setEnabled(true);
                        }
                        if (editable.toString().isEmpty()) {
                            confirmPass.getEditText().setText("");
                            enableError(pass);
                        }
                        break;
                    }
                    case 4: {
                        if (!isValCOnfirmPass(pass.getEditText().getText().toString(), editable.toString()))
                            enableError(confirmPass);
                        else {
                            disableError(confirmPass);
                        }
                        break;
                    }
                    case 0: {
                        if (!validName(editable.toString()))
                            enableError(name);
                        else {
                            disableError(name);
                        }
                        break;
                    }
                }
                btnRegister.setEnabled(enableRegisterButton(email, confirmEmail, pass, confirmPass, name));
            }


        });
    }

    private static boolean validEmail(String email) {
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(email);
        return matcher.find();
    }

    private static boolean isValidConfirmEmail(String email, String confirmEmail) {
        return email.equals(confirmEmail);
    }

    private static void enableError(TextInputLayout v) {
        v.setErrorEnabled(true);
        v.setError("Invalid");
    }

    private static void disableError(TextInputLayout v) {
        v.setErrorEnabled(false);
    }

    private static boolean validPass(String pass) {
        return !pass.isEmpty();
    }

    private static boolean isValCOnfirmPass(String pass, String confirmPass) {
        return pass.equals(confirmPass);
    }

    private static boolean validName(String name) {
        return !name.isEmpty();
    }

    private static boolean enableRegisterButton(TextInputLayout email, TextInputLayout confirmEmail, TextInputLayout pass, TextInputLayout confirmPass, TextInputLayout name) {
        if (email.getEditText().getText().toString().isEmpty() ||
                confirmEmail.getEditText().getText().toString().isEmpty() ||
                pass.getEditText().getText().toString().isEmpty() ||
                confirmPass.getEditText().getText().toString().isEmpty() ||
                name.getEditText().getText().toString().isEmpty()) return false;
        return (!email.isErrorEnabled() && !confirmEmail.isErrorEnabled()
                && !pass.isErrorEnabled() && !confirmPass.isErrorEnabled()
                && !name.isErrorEnabled());
    }
}
