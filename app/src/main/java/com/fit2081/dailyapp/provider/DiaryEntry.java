package com.fit2081.dailyapp.provider;


import static com.fit2081.dailyapp.provider.DiaryEntry.TABLE_NAME;

import android.net.Uri;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.fit2081.dailyapp.diary.DiaryEntryFragment;

@Entity(tableName = TABLE_NAME)
public class DiaryEntry {
    public static final String TABLE_NAME = "diaryEntry";
    public static final String COLUMN_ID = "diaryEntryId";
    public static final String COLUMN_URI = "diaryEntryURI";
    public static final String COLUMN_DESCRIPTION = "diaryEntryDescription";
    public static final String COLUMN_MOOD = "diaryEntryMood";
    public static final String COLUMN_MOOD_STRING = "diaryEntryMoodString";
    // public static final String COLUMN_DATE = "diaryEntryDate";

    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = COLUMN_ID)
    private int id;

    @ColumnInfo(name = COLUMN_URI)
    private String uri;

    @ColumnInfo(name = COLUMN_DESCRIPTION)
    private String description;

    @ColumnInfo(name = COLUMN_MOOD)
    private int mood;

    @ColumnInfo(name = COLUMN_MOOD_STRING)
    private String moodString;

    public DiaryEntry(String uri, String description, int mood, String moodString) {
        this.uri = uri;
        this.description = description;
        this.mood = mood;
        this.moodString = moodString;
    }

    // Getters and setters
    public void setId(@NonNull int id) {this.id = id;}

    public int getId() {return id;}
    public String getUri() {return uri;}
    public String getDescription() {return description;}
    public int getMood() {return mood;}

    public String getMoodString() {
            return moodString;
    }
}
