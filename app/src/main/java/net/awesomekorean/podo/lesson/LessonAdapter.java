package net.awesomekorean.podo.lesson;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import net.awesomekorean.podo.R;

import java.util.ArrayList;
import java.util.List;

public class LessonAdapter extends RecyclerView.Adapter<LessonAdapter.ViewHolder> {

    // 아이템 클릭 이벤트를 MainLesson 에서 처리하기 위한 인터페이스
    public interface OnItemClickListener {
        void onItemClick(View v, int pos);
    }

    private OnItemClickListener listener = null;

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    Context context;
    Animation animation;

    private ArrayList<LessonItem> list;

    public LessonAdapter(Context context, ArrayList<LessonItem> list) {
        this.list = list;
        this.animation = AnimationUtils.loadAnimation(context, R.anim.move_left_reading_item);

    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        LinearLayout lessonItem;
        TextView title;
        TextView subTitle;
        ImageView lessonImage;
        TextView textSpecial;
        ImageView iconLock;
        LinearLayout layoutCompleted;

        ViewHolder(View itemView) {
            super(itemView);

            lessonItem = itemView.findViewById(R.id.lessonItem);
            title = itemView.findViewById(R.id.title);
            subTitle = itemView.findViewById(R.id.subTitle);
            lessonImage = itemView.findViewById(R.id.lessonImage);
            textSpecial = itemView.findViewById(R.id.textSpecial);
            iconLock = itemView.findViewById(R.id.iconLock);
            layoutCompleted = itemView.findViewById(R.id.layoutCompleted);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    if(position != RecyclerView.NO_POSITION) {
                        System.out.println("CLICKED : " + position);

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
    public int getItemViewType(int position) {

        if(list.get(position).getIsSpecial()) { // 스페셜 레슨
            return 0;
        } else {  // 일반 레슨
            return 1;
        }
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View view;

        if(viewType == 0) {
            view = inflater.inflate(R.layout.main_lesson_item_a, parent, false);
        } else {
            view = inflater.inflate(R.layout.main_lesson_item_b, parent, false);
        }

        LessonAdapter.ViewHolder holder = new LessonAdapter.ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        LessonItem items = list.get(position);
        int viewType = holder.getItemViewType();

        if(viewType==0) {
            if(items.getIsSpecial()) { holder.textSpecial.setVisibility(View.VISIBLE);
            } else{holder.textSpecial.setVisibility(View.INVISIBLE);}
            if(items.getIsLock()) { holder.iconLock.setVisibility(View.VISIBLE);
            } else{holder.iconLock.setVisibility(View.INVISIBLE);}
        }

        holder.title.setText(items.getTitle());
        holder.subTitle.setText(items.getSubTitle());
        holder.lessonImage.setImageResource(items.getLessonImage());
        if(items.getIsCompleted()) { holder.layoutCompleted.setVisibility(View.VISIBLE);
        } else{holder.layoutCompleted.setVisibility(View.INVISIBLE);}

        holder.lessonItem.startAnimation(animation);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
