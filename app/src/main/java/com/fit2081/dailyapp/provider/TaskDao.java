package com.fit2081.dailyapp.provider;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

/**
 * The DAO is an interface that defines all the operations that should be performed on a database
 */

@Dao
public interface TaskDao {

    // get all tasks
    @Query("select * from tasks")
    LiveData<List<Task>> getAllTasks();

    // add task
    @Insert
    void addTask(Task task);

    // delete specific task
    @Query("delete from tasks where taskItem= :taskItem")
    void deleteTask(String taskItem);

    // delete all tasks
    @Query("delete from tasks")
    void deleteAllTasks();

}
