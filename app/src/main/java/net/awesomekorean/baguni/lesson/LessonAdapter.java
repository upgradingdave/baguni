package net.awesomekorean.baguni.lesson;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import net.awesomekorean.baguni.R;

import java.util.ArrayList;

public class LessonAdapter extends RecyclerView.Adapter<LessonAdapter.ViewHolder> {

    private ArrayList<LessonItems> list;

    public LessonAdapter(ArrayList<LessonItems> list) {
        this.list = list;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView title;
        TextView subTitle;
        ImageView lessonImage;
        TextView textSpecial;
        ImageView iconLock;
        LinearLayout layoutCompleted;

        ViewHolder(View itemView) {
            super(itemView);

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
                        LessonItems item = list.get(position);
                        // 아이템 클릭 이벤트트
                    }
                }
            });
        }
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.main_lesson_listview_item, parent, false);
        LessonAdapter.ViewHolder holder = new LessonAdapter.ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        LessonItems items = list.get(position);

        holder.title.setText(items.getTitle());
        holder.subTitle.setText(items.getSubTitle());
        holder.lessonImage.setImageResource(items.getLessonImage());
        if(items.getIsSpecial()) { holder.textSpecial.setVisibility(View.VISIBLE);
        } else{holder.textSpecial.setVisibility(View.INVISIBLE);}
        if(items.getIsLock()) { holder.iconLock.setVisibility(View.VISIBLE);
        } else{holder.iconLock.setVisibility(View.INVISIBLE);}
        if(items.getIsCompleted()) { holder.layoutCompleted.setVisibility(View.VISIBLE);
        } else{holder.layoutCompleted.setVisibility(View.INVISIBLE);}
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
