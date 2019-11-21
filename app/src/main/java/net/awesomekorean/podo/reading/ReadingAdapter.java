package net.awesomekorean.podo.reading;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import net.awesomekorean.podo.R;

import java.util.ArrayList;

public class ReadingAdapter extends RecyclerView.Adapter<ReadingAdapter.ViewHolder> {

    private ArrayList<ReadingItems> list;

    public ReadingAdapter(ArrayList<ReadingItems> list) {
        this.list = list;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView title;
        TextView subTitle;
        ImageView readingImage;
        LinearLayout layoutCompleted;

        ViewHolder(View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.title);
            subTitle = itemView.findViewById(R.id.subTitle);
            readingImage = itemView.findViewById(R.id.readingImage);
            layoutCompleted = itemView.findViewById(R.id.layoutCompleted);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    if(position != RecyclerView.NO_POSITION) {
                        ReadingItems item = list.get(position);
                        System.out.println("CLICKED : " + position);
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

        View view = inflater.inflate(R.layout.main_reading_item, parent, false);
        ReadingAdapter.ViewHolder holder = new ReadingAdapter.ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ReadingItems items = list.get(position);

        holder.title.setText(items.getTitle());
        holder.subTitle.setText(items.getSubTitle());
        holder.readingImage.setImageResource(items.getReadingImage());
        if(items.getIsCompleted()) { holder.layoutCompleted.setVisibility(View.VISIBLE);
        } else{holder.layoutCompleted.setVisibility(View.INVISIBLE);}
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
