package net.awesomekorean.baguni.login;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import net.awesomekorean.baguni.R;
import java.util.ArrayList;

public class SelectLanguageAdapter extends RecyclerView.Adapter<SelectLanguageAdapter.ViewHolder> {

    private ArrayList<SelectLanguageItem> list;

    public SelectLanguageAdapter(ArrayList<SelectLanguageItem> list) {
        this.list = list;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView flag;
        TextView nation;
        ImageView checked;

        ViewHolder(View itemView) {
            super(itemView);

            flag = itemView.findViewById(R.id.flag);
            nation = itemView.findViewById(R.id.nation);
            checked = itemView.findViewById(R.id.checked);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    if(position != RecyclerView.NO_POSITION) {
                        SelectLanguageItem item = list.get(position);

                    }
                }
            });
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.activity_sign_in_listview_item, parent, false);
        SelectLanguageAdapter.ViewHolder holder = new SelectLanguageAdapter.ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        SelectLanguageItem item = list.get(position);

        holder.flag.setImageDrawable(item.getFlag());
        holder.nation.setText(item.getNation());

    }


    @Override
    public int getItemCount() {
        return list.size();
    }
}
