package com.fit2081.dailyapp.provider;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;


/**
 * Defines all the operations that should be performed on the diary table
 */

@Dao
public interface DiaryDao {
    // get all diary entries
    @Query("select * from diaryEntry")
    LiveData<List<DiaryEntry>> getAllDiaryEntries();

    // select a specific diary entry
    @Query("select * from diaryEntry where diaryEntryId=:id")
    List<DiaryEntry> getSpecificDiaryEntry(int id);

    // add diary entry
    @Insert
    void addDiaryEntry(DiaryEntry diaryEntry);

    // delete specific diary entry
    @Query("delete from diaryEntry where diaryEntryId=:id")
    void deleteSpecificDiaryEntry(int id);

    // delete all diary entries
    @Query("delete from diaryEntry")
    void deleteAllDiaryEntries();
}
