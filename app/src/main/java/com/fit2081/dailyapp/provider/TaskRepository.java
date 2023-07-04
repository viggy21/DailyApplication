package com.fit2081.dailyapp.provider;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

public class TaskRepository {
    private TaskDao mTaskDao;
    private DiaryDao mDiaryDao;
    private LiveData<List<Task>> mAllTasks;
    private LiveData<List<DiaryEntry>> mAllDiaryEntries;

    TaskRepository(Application application) {
        // get the reference to the database instance
        TaskDatabase taskDatabase = TaskDatabase.getDatabase(application);
        // get the database dao
        mTaskDao = taskDatabase.taskDao();
        mDiaryDao = taskDatabase.diaryDao();
        // get all the task s
        mAllTasks = mTaskDao.getAllTasks();
        // get all diary entries
        mAllDiaryEntries = mDiaryDao.getAllDiaryEntries();
    }

    LiveData<List<Task>> getAllTasks() {
        return mAllTasks;
    }

    LiveData<List<DiaryEntry>> getAllDiaryEntries() { return mAllDiaryEntries; }

//    List<DiaryEntry> getSpecificDiaryEntry(int id) {
//        TaskDatabase.databaseWriteExecutor.execute(() -> mDiaryDao.getSpecificDiaryEntry(id));
//
//        // ToDo: RETURN SPECIFIC DIARY ENTRY
//    }

    void insertTask(Task task) {
        TaskDatabase.databaseWriteExecutor.execute(() -> mTaskDao.addTask(task));
    }

    void insertDiary(DiaryEntry diaryEntry) {
        TaskDatabase.databaseWriteExecutor.execute(() -> mDiaryDao.addDiaryEntry(diaryEntry));
    }

    void deleteAllTasks() {
        TaskDatabase.databaseWriteExecutor.execute(() -> mTaskDao.deleteAllTasks());
    }

    void deleteAllDiaryEntries() {
        TaskDatabase.databaseWriteExecutor.execute(() -> mDiaryDao.deleteAllDiaryEntries());
    }


    void deleteTask(String taskItem) {
        TaskDatabase.databaseWriteExecutor.execute(() -> mTaskDao.deleteTask(taskItem));
    }

    void deleteSpecificDiaryEntry(int id) {
        TaskDatabase.databaseWriteExecutor.execute(() -> mDiaryDao.deleteSpecificDiaryEntry(id));
    }
}
