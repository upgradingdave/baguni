package net.awesomekorean.podo.message;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import net.awesomekorean.podo.R;

import java.util.ArrayList;

public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.ViewHolder> {

    private ArrayList<MessageItems> list;

    public MessageAdapter(ArrayList<MessageItems> list) {
        this.list = list;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView peopleImage;
        TextView message;
        TextView messageDate;
        ImageView isNew;

        ViewHolder(View itemView) {
            super(itemView);

            peopleImage = itemView.findViewById(R.id.peopleImage);
            message = itemView.findViewById(R.id.tvMessage);
            messageDate = itemView.findViewById(R.id.messageDate);
            isNew = itemView.findViewById(R.id.isNew);
        }
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View view = inflater.inflate(R.layout.activity_message_item, parent, false);
        MessageAdapter.ViewHolder holder = new MessageAdapter.ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        MessageItems items = list.get(position);

        holder.peopleImage.setImageResource(items.getPeopleImage());
        holder.message.setText(items.getMessage());
        holder.messageDate.setText(items.getMessageDate());
        if(items.getIsNew()) { holder.isNew.setVisibility(View.VISIBLE);
        } else{holder.isNew.setVisibility(View.INVISIBLE);}
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
