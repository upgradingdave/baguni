package net.awesomekorean.podo.teachers;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import net.awesomekorean.podo.R;
import java.util.ArrayList;

public class TeachersAdapter extends RecyclerView.Adapter<TeachersAdapter.ViewHolder> {

    // 아이템 클릭 이벤트를 MainLesson 에서 처리하기 위한 인터페이스
    public interface OnItemClickListener {
        void onItemClick(View v, int pos);
    }

    private OnItemClickListener listener = null;

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }


    Context context;

    private ArrayList<TeachersItems> list;

    public TeachersAdapter(Context context, ArrayList<TeachersItems> list) {
        this.context = context;
        this.list = list;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        LinearLayout itemLayout;
        ImageView teacherImage;
        TextView available;
        TextView vacation;
        ImageView btnAudio;
        TextView teacherName;
        TextView teacherTag;
        ImageView teacherCheck;

        ViewHolder(View itemView) {
            super(itemView);

            itemLayout = itemView.findViewById(R.id.itemLayout);
            teacherImage = itemView.findViewById(R.id.teacherImage);
            available = itemView.findViewById(R.id.available);
            vacation = itemView.findViewById(R.id.vacation);
            btnAudio = itemView.findViewById(R.id.btnAudio);
            teacherName = itemView.findViewById(R.id.teacherName);
            teacherTag = itemView.findViewById(R.id.teacherTag);
            teacherCheck = itemView.findViewById(R.id.teacherCheck);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    if(position != RecyclerView.NO_POSITION) {
                        TeachersItems item = list.get(position);

                        // 아이템 클릭 이벤트
                        if(listener != null) {

                            for(int i=0; i<list.size(); i++) {
                                list.get(i).setIsChecked(false);
                            }

                            item.setIsChecked(true);
                            notifyDataSetChanged();
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
        View view = inflater.inflate(R.layout.activity_teachers_item, parent, false);
        TeachersAdapter.ViewHolder holder = new TeachersAdapter.ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        TeachersItems item = list.get(position);

        if(item.getIsAvailable()) {
            holder.available.setVisibility(View.VISIBLE);
            holder.vacation.setVisibility(View.GONE);
        } else {
            holder.available.setVisibility(View.GONE);
            holder.vacation.setVisibility(View.VISIBLE);
        }

        if(item.getIsChecked()) {
            holder.itemLayout.setBackground(ContextCompat.getDrawable(context, R.drawable.bg_white_10_stroke_purple));
            holder.teacherCheck.setVisibility(View.VISIBLE);
        } else  {
            holder.itemLayout.setBackground(ContextCompat.getDrawable(context, R.drawable.bg_white_10));
            holder.teacherCheck.setVisibility(View.GONE);
        }

        holder.teacherName.setText(item.getName());
        holder.teacherTag.setText(item.getTag());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
