package net.awesomekorean.podo.qna;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import net.awesomekorean.podo.R;

import java.util.ArrayList;

public class FaqAdapter extends RecyclerView.Adapter<FaqAdapter.ViewHolder> {

    private ArrayList<FaqItems> list;

    public FaqAdapter(ArrayList<FaqItems> list) {
        this.list = list;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView userImage;
        TextView userName;
        TextView uploadDate;
        TextView summary;

        ViewHolder(View itemView) {
            super(itemView);

            userImage = itemView.findViewById(R.id.userImage);
            userName = itemView.findViewById(R.id.userName);
            uploadDate = itemView.findViewById(R.id.uploadDate);
            summary = itemView.findViewById(R.id.summary);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    if(position != RecyclerView.NO_POSITION) {
                        FaqItems item = list.get(position);
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

        View view = inflater.inflate(R.layout.main_qna_faq_item, parent, false);
        FaqAdapter.ViewHolder holder = new FaqAdapter.ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        FaqItems items = list.get(position);

        holder.userImage.setImageResource(items.getUserImage());
        holder.userName.setText(items.getUserName());
        holder.uploadDate.setText(items.getUploadDate());
        holder.summary.setText((items.getSummary()));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
