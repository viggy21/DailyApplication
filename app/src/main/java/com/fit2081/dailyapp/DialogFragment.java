package com.fit2081.dailyapp;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;
import androidx.core.content.res.ResourcesCompat;

public class DialogFragment extends AppCompatDialogFragment {

    public interface DialogFragmentListener {
        public void onDialogPositiveClick(DialogFragment dialog);
        public void onDialogNegativeClick(DialogFragment dialog);
    }

    DialogFragmentListener dialogFragmentListener;
    EditText taskEt;

    // Override the Fragment.onAttach() method to instantiate the DialogFragmentListener
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try {
            dialogFragmentListener = (DialogFragmentListener) context;
        }
        catch (ClassCastException e) {
            // The activity doesn't implement the interface, throw exception
            throw new ClassCastException(getActivity().toString() + " must implement DialogFragmentListener");
        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        // get the layout inflater
        LayoutInflater inflater = requireActivity().getLayoutInflater();
        View v = inflater.inflate(R.layout.dialog_layout, null);
        taskEt = v.findViewById(R.id.task_input);



        // inflate and set the layout for the dialog
        // pass null as the parent view because its going in the dialog layout
        builder.setView(v)
                // add action buttons
                .setPositiveButton("Add", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // add code for adding the task here
                        dialogFragmentListener.onDialogPositiveClick(DialogFragment.this);
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // add code for cancelling the action
                        DialogFragment.this.getDialog().cancel();
                    }
                });


        Dialog dialog = builder.create();
        // Set the background of the dialog's root view to transparent, because Android puts your
        // dialog layout within a root view that hides the corners in your custom layout.
        //dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        Resources res = getContext().getResources();
        Drawable drawableBackground = ResourcesCompat.getDrawable(res, R.drawable.drawable_background, null);
        dialog.getWindow().setBackgroundDrawable(drawableBackground);
        return dialog;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}
