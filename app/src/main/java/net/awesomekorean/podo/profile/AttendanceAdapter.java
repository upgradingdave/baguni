package net.awesomekorean.podo.profile;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import net.awesomekorean.podo.R;

import java.util.List;

public class AttendanceAdapter extends RecyclerView.Adapter<AttendanceAdapter.ViewHolder> {


    private List<DayOfWeekItem> list;

    public AttendanceAdapter(List<DayOfWeekItem> list) {
        this.list = list;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView day;

        ViewHolder(View itemView) {
            super(itemView);

            day = itemView.findViewById(R.id.day);
        }
    }

    @Override
    public int getItemViewType(int position) {

        if(list.get(position).isChecked()) { // 출석함
            return 0;
        } else {  // 결석함
            return 1;
        }
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View view;

        if(viewType == 0) {
            view = inflater.inflate(R.layout.activity_profile_item_a, parent, false);
        } else {
            view = inflater.inflate(R.layout.activity_profile_item_b, parent, false);
        }

        AttendanceAdapter.ViewHolder holder = new AttendanceAdapter.ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        DayOfWeekItem item = list.get(position);
        holder.day.setText(item.getDay());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
