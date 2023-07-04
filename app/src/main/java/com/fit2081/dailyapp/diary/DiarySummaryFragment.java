package com.fit2081.dailyapp.diary;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.fit2081.dailyapp.DiaryRecyclerAdapter;
import com.fit2081.dailyapp.R;
import com.fit2081.dailyapp.ToDoRecyclerAdapter;
import com.fit2081.dailyapp.provider.DiaryEntry;
import com.fit2081.dailyapp.provider.DiaryViewModel;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DiarySummaryFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DiarySummaryFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    // Own attributes
    Button addEntryBtn;
    RecyclerView recyclerView;

    RecyclerView.LayoutManager layoutManager;
    DiaryRecyclerAdapter adapter;
    DiaryViewModel diaryViewModel;
    List<DiaryEntry> diaryEntries;

    public DiarySummaryFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DiarySummaryFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DiarySummaryFragment newInstance(String param1, String param2) {
        DiarySummaryFragment fragment = new DiarySummaryFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Get a reference to the layout view
        View view =  inflater.inflate(R.layout.fragment_diary_summary, container, false);

        // Get a reference to he button and set its on click listener
        addEntryBtn = view.findViewById(R.id.add_diary_entry_btn);
        addEntryBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // when the button is clicked, we want to inflate the layout for adding the diary entry
                // so we need to call the method in main activity
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, new DiaryMoodFragment()).addToBackStack("DiaryMoodFragment").commit();
            }
        });

        recyclerView = view.findViewById(R.id.diary_summary_recycler_view);
        layoutManager = new LinearLayoutManager(this.getContext());
        recyclerView.setLayoutManager(layoutManager);
        adapter = new DiaryRecyclerAdapter();
        recyclerView.setAdapter(adapter);

        diaryViewModel = new ViewModelProvider(this).get(DiaryViewModel.class);
        diaryViewModel.getmAllDiaryEntries().observe(getViewLifecycleOwner(), newData -> {
            adapter.setData(newData);
            adapter.notifyDataSetChanged();
        });





        // Inflate the layout for this fragment
        return view;
    }
}