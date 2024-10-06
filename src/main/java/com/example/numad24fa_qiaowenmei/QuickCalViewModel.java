package com.example.numad24fa_qiaowenmei;

import androidx.databinding.ObservableField;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.Objects;

public class QuickCalViewModel extends ViewModel {
    private final ObservableField<String> text = new ObservableField<>("CALC");

    public ObservableField<String> getText() {
        return text;
    }

    // Method to update the text when the button is clicked
    public void setText(String str) {
        text.set(str);
    }
}
