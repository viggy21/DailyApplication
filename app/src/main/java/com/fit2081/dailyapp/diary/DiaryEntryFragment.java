package com.fit2081.dailyapp.diary;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.PickVisualMediaRequest;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.fit2081.dailyapp.R;
import com.fit2081.dailyapp.provider.DiaryEntry;
import com.fit2081.dailyapp.provider.DiaryViewModel;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DiaryEntryFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DiaryEntryFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    // Own attributes
    EditText diaryEntryEt;
    ImageButton backBtn;
    ImageButton addBtn;
    Button saveEntryBtn;
    ImageView imageView;
    ExecutorService executor = Executors.newSingleThreadExecutor();
    Handler uiHandler = new Handler(Looper.getMainLooper());
    ActivityResultLauncher<PickVisualMediaRequest> pickMedia;

    Uri imageUri;

    private DiaryViewModel diaryViewModel;

    public DiaryEntryFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DiaryEntry.
     */
    // TODO: Rename and change types and number of parameters
    public static DiaryEntryFragment newInstance(String param1, String param2) {
        DiaryEntryFragment fragment = new DiaryEntryFragment();
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

        // Own code
        // Get reference to the image view
        // Registers a photo picker activity launcher in single-select mode.
        pickMedia =
                registerForActivityResult(new ActivityResultContracts.PickVisualMedia(), uri -> {
                    // Callback is invoked after the user selects a media item or closes the
                    // photo picker.
                    if (uri != null) {
                        Log.d("PhotoPicker", "Selected URI: " + uri);
                        // upload the image to our image view
                        imageView.setImageURI(uri);
                        imageUri = uri;
                        getActivity().getContentResolver().takePersistableUriPermission(uri, (Intent.FLAG_GRANT_READ_URI_PERMISSION | Intent.FLAG_GRANT_WRITE_URI_PERMISSION));

                    } else {
                        Log.d("PhotoPicker", "No media selected");
                    }
                });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_diary_entry, container, false);
        DiaryFragmentViewModel diaryFragmentViewModel = new ViewModelProvider(getActivity()).get(DiaryFragmentViewModel.class);
        int moodSelected = diaryFragmentViewModel.getMoodId();
        String moodString = diaryFragmentViewModel.getMood();

        // Get reference to edit text
        diaryEntryEt = view.findViewById(R.id.diary_entry);
        backBtn = view.findViewById(R.id.entry_back_btn);
        addBtn = view.findViewById(R.id.add_image_button);
        imageView = view.findViewById(R.id.imageView);
        // Get the text
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().popBackStack();
            }
        });

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // use an executor to run this on a different thread so that the UI remains responsive
                executor.execute(() -> {
                    addImage();
                    // post the results to the main thread
                    uiHandler.post(() -> {

                    });

                });

            }
        });

        // Database
        diaryViewModel = new ViewModelProvider(this).get(DiaryViewModel.class);

        saveEntryBtn = view.findViewById(R.id.save_diary_entry_btn);
        saveEntryBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // create new diary entry and save it
                String description = diaryEntryEt.getText().toString();
                DiaryEntry diaryEntry = new DiaryEntry(imageUri.toString(), description, moodSelected, moodString);
                // add it to the database
                diaryViewModel.insert(diaryEntry);
                // pop off the two fragments
                getActivity().getSupportFragmentManager().popBackStack();
                getActivity().getSupportFragmentManager().popBackStack();
            }
        });



        diaryViewModel.getmAllDiaryEntries().observe(getViewLifecycleOwner(), newData -> {
            //adapter.setData(newData);
            //adapter.notifyDataSetChanged();
        });


        // Inflate the layout for this fragment
        return view;
    }

    public void addImage() {

        // Launch the photo picker and let the user choose only images.
        ActivityResultContracts.PickVisualMedia.VisualMediaType type = (ActivityResultContracts.PickVisualMedia.VisualMediaType) ActivityResultContracts.PickVisualMedia.ImageOnly.INSTANCE;

        pickMedia.launch(new PickVisualMediaRequest.Builder()
                .setMediaType(type)
                .build());
    }
}