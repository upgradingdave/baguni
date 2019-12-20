package net.awesomekorean.podo.reading;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

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



    private ArrayList<Reading> list;

    public ReadingAdapter(ArrayList<Reading> list) {
        this.list = list;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView title;
        ImageView readingImage;
        LinearLayout layoutCompleted;

        ViewHolder(View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.title);
            readingImage = itemView.findViewById(R.id.readingImage);
            layoutCompleted = itemView.findViewById(R.id.layoutCompleted);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    if(position != RecyclerView.NO_POSITION) {
                        Reading item = list.get(position);

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
        holder.readingImage.setImageResource(items.getReadingImage());
        if(items.getIsCompleted()) { holder.layoutCompleted.setVisibility(View.VISIBLE);
        } else{holder.layoutCompleted.setVisibility(View.INVISIBLE);}
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
