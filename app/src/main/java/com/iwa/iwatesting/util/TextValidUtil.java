package com.iwa.iwatesting.util;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

public abstract class TextValidUtil implements TextWatcher {
    private final EditText edtText;
    TextValidUtil(EditText edtText) {
        this.edtText = edtText;
    }
    public abstract void validate (EditText edtText, String text);
    @Override
    public void afterTextChanged(Editable editable) {
        String text = edtText.getText().toString();
        validate(edtText,text);
    }



    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }
}
