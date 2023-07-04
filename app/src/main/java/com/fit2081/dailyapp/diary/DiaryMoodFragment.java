package com.fit2081.dailyapp.diary;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.fit2081.dailyapp.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DiaryMoodFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DiaryMoodFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    // Own attributes
    ImageButton happyBtn;
    ImageButton sadBtn;
    ImageButton neutralBtn;
    ImageButton angryBtn;
    ImageButton shockedBtn;
    ImageButton backBtn;



    public DiaryMoodFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DiaryFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DiaryMoodFragment newInstance(String param1, String param2) {
        DiaryMoodFragment fragment = new DiaryMoodFragment();
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
        View view = inflater.inflate(R.layout.fragment_diary_mood, container, false);

        // set the method for each of the buttons

        happyBtn = view.findViewById(R.id.happy_image_button);
        neutralBtn = view.findViewById(R.id.neutral_image_button);
        sadBtn = view.findViewById(R.id.sad_image_button);
        angryBtn = view.findViewById(R.id.angry_image_button);
        shockedBtn = view.findViewById(R.id.shocked_image_button);
        backBtn = view.findViewById(R.id.back_btn);

        // set the tags for each for the buttons
        happyBtn.setTag(R.drawable.happy_face);
        neutralBtn.setTag(R.drawable.neutral_face);
        sadBtn.setTag(R.drawable.sad_face);
        angryBtn.setTag(R.drawable.angry_face);
        shockedBtn.setTag(R.drawable.shocked_face);


        ArrayList<ImageButton> buttonList = new ArrayList<ImageButton>();
        buttonList.add(happyBtn);
        buttonList.add(neutralBtn);
        buttonList.add(sadBtn);
        buttonList.add(angryBtn);
        buttonList.add(shockedBtn);
        for (int i = 0; i < buttonList.size(); i++) {
            // set the on click listener on each of the buttons and pass in their id
            ImageButton button = buttonList.get(i);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int drawableId = (int) button.getTag();
                    //onMoodBtnClick(button.getId());
                    onMoodBtnClick(drawableId);
                }
            });
        }

        // if the back button gets clicked, pop the fragment off the activity backstack
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().popBackStack();
            }
        });

        // Inflate the layout for this fragment
        return view;
    }

    // Method that gets called when all of the buttons get clicked to process the type of button

    // On click method for the mood buttons for fragments
    public void onMoodBtnClick(int id) {
        // Get the button from the clicked view
        // put the button that gets clicked into the view model
        DiaryFragmentViewModel diaryFragmentViewModel = new ViewModelProvider(getActivity()).get(DiaryFragmentViewModel.class);
        diaryFragmentViewModel.setMoodId(id);

        // CHECKING
        String resourceName = getResources().getResourceEntryName(id);
        diaryFragmentViewModel.setMood(resourceName);

        // inflate the fragment for the user to entry text and an image
        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, new DiaryEntryFragment()).addToBackStack("DiaryEntryFragment").commit();
    }


}