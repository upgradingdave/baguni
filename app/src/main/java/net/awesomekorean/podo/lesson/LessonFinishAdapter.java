package net.awesomekorean.podo.lesson;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ToggleButton;

import androidx.recyclerview.widget.RecyclerView;

import net.awesomekorean.podo.R;

import java.util.ArrayList;

public class LessonFinishAdapter extends RecyclerView.Adapter<LessonFinishAdapter.ViewHolder> {

    private ArrayList<LessonFinishItems> list;
    public LessonFinishAdapter(ArrayList<LessonFinishItems> list) {
        this.list = list;
    }
    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView dot;
        TextView tvFront;
        TextView tvBack;

        ViewHolder(final View itemView) {
            super(itemView);

            dot = itemView.findViewById(R.id.dot);
            tvFront = itemView.findViewById(R.id.tvFront);
            tvBack = itemView.findViewById(R.id.tvBack);
        }
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View view;
        view = inflater.inflate(R.layout.activity_lesson_finish_item, parent, false);
        LessonFinishAdapter.ViewHolder holder = new LessonFinishAdapter.ViewHolder(view);
        return holder;
    }


    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        LessonFinishItems items = list.get(position);
        System.out.println("하이!");
        if(position %2 == 0) {
            holder.dot.setImageResource(R.drawable.purple_dot);
        } else {
            holder.dot.setImageResource(R.drawable.pink_dot);
        }
        holder.tvFront.setText(items.getFront());
        holder.tvBack.setText(items.getBack());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
