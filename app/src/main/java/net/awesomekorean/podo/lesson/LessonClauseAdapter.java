package net.awesomekorean.podo.lesson;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;
import net.awesomekorean.podo.R;
import java.util.ArrayList;

public class LessonClauseAdapter extends RecyclerView.Adapter<LessonClauseAdapter.ViewHolder> {

    // 아이템 클릭 이벤트를 MainLesson 에서 처리하기 위한 인터페이스
    public interface OnItemClickListener {
        void onItemClick(View v, int pos);
    }

    private OnItemClickListener listener = null;

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }



    private ArrayList<LessonClauseItems> list;

    public LessonClauseAdapter(ArrayList<LessonClauseItems> list) {
        this.list = list;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView peopleImage;
        TextView tvClause;

        ViewHolder(final View itemView) {
            super(itemView);

            peopleImage = itemView.findViewById(R.id.peopleImage);
            tvClause = itemView.findViewById(R.id.tvClause);

            tvClause.setOnClickListener(new View.OnClickListener() {
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

        if(list.get(position).getAOrB() == 0) { // a타입
            return 0;
        } else {  // b타입
            return 1;
        }
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View view;

        if(viewType == 0) {
            view = inflater.inflate(R.layout.lesson_clause_item_a, parent, false);
        } else {
            view = inflater.inflate(R.layout.lesson_clause_item_b, parent, false);
        }

        LessonClauseAdapter.ViewHolder holder = new LessonClauseAdapter.ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        LessonClauseItems items = list.get(position);

        holder.peopleImage.setImageResource(items.getPeopleImage());
        holder.tvClause.setText(items.getClause());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

}
