package net.awesomekorean.baguni.teachers;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import net.awesomekorean.baguni.R;
import java.util.ArrayList;

public class TeachersAdapter extends RecyclerView.Adapter<TeachersAdapter.ViewHolder> {

    private ArrayList<TeachersItems> list;

    public TeachersAdapter(ArrayList<TeachersItems> list) {
        this.list = list;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView picture;
        TextView isAvailable;
        ImageButton voice;
        TextView name;
        TextView tag;

        ViewHolder(View itemView) {
            super(itemView);

            picture = itemView.findViewById(R.id.picture);
            isAvailable = itemView.findViewById(R.id.isAvailable);
            voice = itemView.findViewById(R.id.voice);
            name = itemView.findViewById(R.id.name);
            tag = itemView.findViewById(R.id.tag);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    if(position != RecyclerView.NO_POSITION) {
                        TeachersItems item = list.get(position);
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
        View view = inflater.inflate(R.layout.activity_teachers_item, parent, false);
        TeachersAdapter.ViewHolder holder = new TeachersAdapter.ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        TeachersItems item = list.get(position);

        holder.isAvailable.setText(item.getIsAvailable());
        holder.name.setText(item.getName());
        holder.tag.setText(item.getTag());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}