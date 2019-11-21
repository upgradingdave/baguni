package net.awesomekorean.podo.myquestions;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import net.awesomekorean.podo.R;
import net.awesomekorean.podo.faq.FaqItems;

import java.util.ArrayList;

public class MyquestionsAdapter extends RecyclerView.Adapter<MyquestionsAdapter.ViewHolder> {

    private ArrayList<MyquestionsItems> list;

    public MyquestionsAdapter(ArrayList<MyquestionsItems> list) {
        this.list = list;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView category;
        TextView summary;
        TextView uploadDate;
        LinearLayout confirmed;
        LinearLayout rejected;

        ViewHolder(View itemView) {
            super(itemView);

            category = itemView.findViewById(R.id.category);
            summary = itemView.findViewById(R.id.summary);
            uploadDate = itemView.findViewById(R.id.uploadDate);
            confirmed = itemView.findViewById(R.id.confirmed);
            rejected = itemView.findViewById(R.id.rejected);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    if(position != RecyclerView.NO_POSITION) {
                        MyquestionsItems item = list.get(position);
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

        View view = inflater.inflate(R.layout.main_myquestions_list_item, parent, false);
        MyquestionsAdapter.ViewHolder holder = new MyquestionsAdapter.ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        MyquestionsItems items = list.get(position);

        holder.category.setText(items.getCategory());
        holder.summary.setText((items.getSummary()));
        holder.uploadDate.setText(items.getUploadDate());

        if(items.getIsConfirmed()==null) {
            holder.confirmed.setVisibility(View.GONE);
            holder.rejected.setVisibility(View.GONE);

        }else if(items.getIsConfirmed().equals(1)) {
            holder.confirmed.setVisibility(View.VISIBLE);
            holder.rejected.setVisibility(View.GONE);

        }else if(items.getIsConfirmed().equals(0)) {
            holder.confirmed.setVisibility(View.GONE);
            holder.rejected.setVisibility(View.VISIBLE);

        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
