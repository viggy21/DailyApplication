package com.fit2081.dailyapp.provider;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class DiaryViewModel extends AndroidViewModel {
    // reference to repository
    private TaskRepository mRepository;
    private LiveData<List<DiaryEntry>> mAllDiaryEntries;


    public DiaryViewModel(@NonNull Application application) {
        super(application);
        mRepository = new TaskRepository(application);
        mAllDiaryEntries = mRepository.getAllDiaryEntries();
    }
    public LiveData<List<DiaryEntry>> getmAllDiaryEntries() {
        return mAllDiaryEntries;
    }

    // have calls to the other methods
    public void insert(DiaryEntry diaryEntry) {
        mRepository.insertDiary(diaryEntry);
    }

    public void deleteAll() {
        mRepository.deleteAllDiaryEntries();
    }

    public void deleteTask(int id) {
        mRepository.deleteSpecificDiaryEntry(id);
    }
}
