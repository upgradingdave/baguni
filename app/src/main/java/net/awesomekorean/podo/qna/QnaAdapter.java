package net.awesomekorean.podo.qna;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import net.awesomekorean.podo.R;

import java.util.List;

public class QnaAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public static final int HEADER = 0;
    public static final int CHILD = 1;

    private List<Item> data;

    public QnaAdapter(List<Item> data) {
        this.data = data;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = null;
        Context context = parent.getContext();

        switch (viewType) {
            case HEADER :
                LayoutInflater inflater = (LayoutInflater) parent.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                view = inflater.inflate(R.layout.main_qna_category, parent, false);
                ListHeaderViewHolder header = new ListHeaderViewHolder(view);
                return header;

            case CHILD :
                TextView childTextView = new TextView(context);
                return new RecyclerView.ViewHolder(childTextView) {
                };
        }

        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        final Item item = data.get(position);

        switch (item.type) {
            case HEADER :
                final ListHeaderViewHolder itemController = (ListHeaderViewHolder) holder;
                itemController.category.setText(item.category);


            case CHILD :
                TextView itemTextView = (TextView) holder.itemView;
                itemTextView.setText(data.get(position).category);
                break;
        }
    }

    @Override
    public int getItemViewType(int position) {
        return data.get(position).type;
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    private static class ListHeaderViewHolder extends RecyclerView.ViewHolder {
        public TextView category;

        public ListHeaderViewHolder(View itemView) {
            super(itemView);
            category = itemView.findViewById(R.id.category);
        }
    }

    public static class Item {
        public int type;
        public String category;
        public List<Item> invisibleChildren;

        public Item() {}

        public Item(int type, String text) {
            this.type = type;
            this.category = text;
        }
    }
}


