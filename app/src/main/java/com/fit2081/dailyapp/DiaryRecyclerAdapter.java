package com.fit2081.dailyapp;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.fit2081.dailyapp.provider.DiaryEntry;

import java.io.InputStream;
import java.util.List;

public class DiaryRecyclerAdapter extends RecyclerView.Adapter<DiaryRecyclerAdapter.DiaryViewHolder> {

    // List of diary entries
    List<DiaryEntry> data;
    Context context;

    public void setData(List<DiaryEntry> data) { this.data = data; }

    @NonNull
    @Override
    public DiaryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.diary_card_layout, parent, false);
        DiaryViewHolder diaryViewHolder = new DiaryViewHolder(v);

        // set the context
        context = parent.getContext();

        return diaryViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull DiaryViewHolder holder, int position) {
        DiaryEntry diaryEntry = data.get(position);
        holder.descriptionTv.setText(diaryEntry.getDescription());
        holder.imageView.setImageURI(Uri.parse(diaryEntry.getUri()));

        // get the mood and set it
        String moodString = diaryEntry.getMoodString();
        Drawable moodDrawable;
        int id = context.getResources().getIdentifier(moodString, "drawable", context.getPackageName());
        moodDrawable = ResourcesCompat.getDrawable(context.getResources(), id, context.getTheme());

        holder.moodImageView.setImageDrawable(moodDrawable);


    }

    @Override
    public int getItemCount() {
        if (data == null) {
            return 0;
        }
        else {
            return data.size();
        }
    }

    public class DiaryViewHolder extends RecyclerView.ViewHolder {

        public TextView descriptionTv;
        public ImageView imageView;
        public ImageView moodImageView;

        public DiaryViewHolder(@NonNull View itemView) {
            super(itemView);
            descriptionTv = itemView.findViewById(R.id.entry_description_text_view);
            imageView = itemView.findViewById(R.id.entry_image_view);
            moodImageView = itemView.findViewById(R.id.entry_mood);
        }
    }
}
