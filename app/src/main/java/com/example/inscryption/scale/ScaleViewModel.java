package com.example.inscryption.scale;

import android.view.View;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.inscryption.R;

public class ScaleViewModel extends ViewModel {

    private final MutableLiveData<Integer> count = new MutableLiveData<>(5);
    private final int[] images = {
            R.drawable.minus5, R.drawable.minus4, R.drawable.minus3, R.drawable.minus2, R.drawable.minus1,
            R.drawable.zero,
            R.drawable.plus1, R.drawable.plus2, R.drawable.plus3, R.drawable.plus4, R.drawable.plus5
    };

    public LiveData<Integer> getCount() {
        return count;
    }

    public int[] getImages() {
        return images;
    }

    public void increment() {
        if (count.getValue() != null && count.getValue() < images.length - 1) {
            count.setValue(count.getValue() + 1);
        }
    }

    public void decrement() {
        if (count.getValue() != null && count.getValue() > 0) {
            count.setValue(count.getValue() - 1);
        }
    }

}
