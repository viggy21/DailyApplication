package com.fit2081.dailyapp.diary;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

public class DiaryFragmentViewModel extends AndroidViewModel {

    // Attributes
    // Id of mood button selected
    private int moodId;
    private String mood;

    public DiaryFragmentViewModel(@NonNull Application application) {
        super(application);
    }

    public int getMoodId() {
        return moodId;
    }

    public void setMoodId(int id) {
        moodId = id;
    }

    public String getMood() {
        return mood;
    }

    public void setMood(String mood) {
        this.mood = mood;
    }
}
