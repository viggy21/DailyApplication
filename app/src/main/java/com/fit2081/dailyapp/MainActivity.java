package com.fit2081.dailyapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import com.fit2081.dailyapp.diary.DiarySummaryFragment;
import com.fit2081.dailyapp.provider.Task;
import com.fit2081.dailyapp.provider.TaskViewModel;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity implements DialogFragment.DialogFragmentListener {
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    ToDoRecyclerAdapter adapter;
    CheckBox checkBox;
    Button deleteBtn;
    BottomNavigationView bottomNavigationView;

    private TaskViewModel taskViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.app_bar_layout);


        // place the fragment on the frame layout

        inflateFragment(new ToDoFragment(), "ToDoFragment");

        taskViewModel = new ViewModelProvider(this).get(TaskViewModel.class);



        bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new MyBottomNavigationListener());



    }

    public void showAddTaskDialog() {
        DialogFragment dialogFragment = new DialogFragment();
        dialogFragment.show(getSupportFragmentManager(), "Dialog");
    }


    // Methods for the DialogFragment 'add' and 'cancel' buttons
    @Override
    public void onDialogPositiveClick(DialogFragment dialog) {
        EditText taskEt = dialog.taskEt;
        String taskString = taskEt.getText().toString();

        // Create a new task and add it to the database
        Task task = new Task(taskString);
        taskViewModel.insert(task);

    }

    @Override
    public void onDialogNegativeClick(DialogFragment dialog) {

    }

    class MyBottomNavigationListener implements BottomNavigationView.OnNavigationItemSelectedListener {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            int id = item.getItemId();
            if (id == R.id.diary) {
                //getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, new DiaryMoodFragment()).addToBackStack("DiaryFragment").commit();
                inflateFragment(new DiarySummaryFragment(), "DiaryFragment");

            }
            else if (id == R.id.calendar) {
                inflateFragment(new CalendarFragment(), "CalendarFragment");
            }
            else if (id == R.id.to_do_list) {
                //getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, new ToDoFragment()).addToBackStack("ToDoFragment").commit();
                inflateFragment(new ToDoFragment(), "ToDoFragment");
            }

            return true;
        }
    }

    // Method for inflating a fragment
    public void inflateFragment(Fragment fragment, String tag) {
        getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, fragment).addToBackStack(tag).commit();

    }




}