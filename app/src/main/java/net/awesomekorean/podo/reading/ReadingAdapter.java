package net.awesomekorean.podo.reading;

import android.content.Context;
import android.content.Intent;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.crashlytics.FirebaseCrashlytics;
import com.google.firebase.database.collection.LLRBBlackValueNode;

import net.awesomekorean.podo.R;
import net.awesomekorean.podo.lesson.LessonFrame;

import java.util.ArrayList;

public class ReadingAdapter extends RecyclerView.Adapter<ReadingAdapter.ViewHolder> {

    // 아이템 클릭 이벤트를 MainLesson 에서 처리하기 위한 인터페이스
    public interface OnItemClickListener {
        void onItemClick(View v, int pos);
    }

    private OnItemClickListener listener = null;

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    Context context;

    private ArrayList<Reading> list;

    public ReadingAdapter(Context context, ArrayList<Reading> list) {
        this.context = context;
        this.list = list;
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        LinearLayout readingItem;
        TextView title;
        ImageView readingImage;
        TextView readingId;
        ImageView iconLock;
        ImageView rocket;
        ProgressBar progressBar;
        ImageView complete;

        private ColorMatrixColorFilter filterGrey;
        private ColorMatrixColorFilter filterNormal;

        ViewHolder(View itemView) {
            super(itemView);

            readingItem = itemView.findViewById(R.id.readingItem);
            title = itemView.findViewById(R.id.title);
            readingImage = itemView.findViewById(R.id.readingImage);
            readingId = itemView.findViewById(R.id.readingId);
            iconLock = itemView.findViewById(R.id.iconLock);
            rocket = itemView.findViewById(R.id.rocket);
            progressBar = itemView.findViewById(R.id.progressBar);
            complete = itemView.findViewById(R.id.complete);


            ColorMatrix matrix = new ColorMatrix();

            matrix.setSaturation(0);

            filterGrey = new ColorMatrixColorFilter(matrix);

            matrix.setSaturation(1);

            filterNormal = new ColorMatrixColorFilter(matrix);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    if(position != RecyclerView.NO_POSITION) {

                        // 아이템 클릭 이벤트
                        if(listener != null) {
                            listener.onItemClick(view, position);
                        }
                    }
                }
            });
        }
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View view = inflater.inflate(R.layout.main_reading_item, parent, false);
        ReadingAdapter.ViewHolder holder = new ReadingAdapter.ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        Reading items = list.get(position);

        holder.title.setText(items.getTitle());
        // outOfMemoryError 방지용
        try {
            holder.readingImage.setImageResource(items.getReadingImage());
        } catch (OutOfMemoryError e) {
            FirebaseCrashlytics.getInstance().log("OutOfMemoryError");
        }

        holder.readingId.setText(items.getReadingId().substring(2));

        if (items.getReadingProgress() == 100) {
            holder.progressBar.setVisibility(View.GONE);
            holder.complete.setVisibility(View.VISIBLE);

        } else {
            holder.progressBar.setProgress(items.getReadingProgress());
            holder.progressBar.setVisibility(View.GONE);
            holder.complete.setVisibility(View.INVISIBLE);
        }

        if (items.getIsLock()) {
            holder.progressBar.setVisibility(View.GONE);
            holder.readingImage.setColorFilter(holder.filterGrey);
            holder.rocket.setColorFilter(holder.filterGrey);
            holder.iconLock.setVisibility(View.VISIBLE);

        } else {
            holder.readingImage.setColorFilter(holder.filterNormal);
            holder.rocket.setColorFilter(holder.filterNormal);
            holder.iconLock.setVisibility(View.INVISIBLE);
        }

        int readingLevel = items.getReadingLevel();

        if(readingLevel == 1) {
            holder.rocket.setImageResource(R.drawable.rocket1);

        } else if(readingLevel == 2) {
            holder.rocket.setImageResource(R.drawable.rocket2);

        } else if(readingLevel == 3) {
            holder.rocket.setImageResource(R.drawable.rocket3);
        }
    }


    @Override
    public int getItemCount() {
        return list.size();
    }
}
