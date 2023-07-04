package com.fit2081.dailyapp.provider;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class TaskViewModel extends AndroidViewModel {
    // reference to repository
    private TaskRepository mRepository;
    private LiveData<List<Task>> mAllTasks;

    public TaskViewModel(@NonNull Application application) {
        super(application);
        mRepository = new TaskRepository(application);
        mAllTasks = mRepository.getAllTasks();
    }

    public LiveData<List<Task>> getAllTasks() {
        return mAllTasks;
    }

    // have calls to the other methods
    public void insert(Task task) {
        mRepository.insertTask(task);
    }

    public void deleteAll() {
        mRepository.deleteAllTasks();
    }

    public void deleteTask(String taskItem) {
        mRepository.deleteTask(taskItem);
    }
}
