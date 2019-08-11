package net.awesomekorean.baguni.collection;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.TextView;

import net.awesomekorean.baguni.R;

import java.util.ArrayList;

public class CollectionListViewAdapter extends BaseAdapter {

    private Context context;
    public static ArrayList<CollectionItems> list;

    public CollectionListViewAdapter(Context context, ArrayList<CollectionItems> list) {

        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        final ViewHolder holder;

        if(view==null) {

            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.main_collection_listview_item, viewGroup, false);
            holder = new ViewHolder();
            holder.checkBox = view.findViewById(R.id.checkBox);
            holder.btnRecord = view.findViewById(R.id.btnRecord);
            holder.collectionKorean = view.findViewById(R.id.collectionKorean);
            holder.collectionEnglish = view.findViewById(R.id.collectionEnglish);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }

        CollectionItems items = list.get(i);

        holder.collectionKorean.setText(items.getCollectionKorean());
        holder.collectionEnglish.setText(items.getCollectionEnglish());
        holder.checkBox.setChecked(items.getChecked());
        holder.checkBox.setTag(R.integer.btnplusview, view); // 해당 checkbox 가 있는 뷰를 Tag Key 1번으로 할당 (ViewGroup 은 item 별로 구성된 view 묶음을 말하고, 이 ViewGroup 을 convert 한 것을 ListView 기준으로 View 라고 한다.)
        holder.checkBox.setTag(i);
        holder.checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                View tempView = (View) holder.checkBox.getTag(R.integer.btnplusview); // 해당 checkbox 가 포함된 뷰를 가져옴
                TextView collectionKorean = tempView.findViewById(R.id.collectionKorean);
                TextView collectionEnglish = tempView.findViewById(R.id.collectionEnglish);
                Integer position = (Integer) holder.checkBox.getTag();
                System.out.println("Checkbox "+ position + "Clicked. CK is " + collectionKorean + "CE is "+ collectionEnglish);

                CollectionItems items = list.get(position);

                if(items.getChecked()) {
                    items.setChecked(false);
                    System.out.println("HI:"+position+items.getChecked());
                } else {
                    items.setChecked(true);
                    System.out.println("BYE:"+position+items.getChecked());
                }
            }
        });
        return view;
    }

    static class ViewHolder {
        CheckBox checkBox;
        ImageButton btnRecord;
        TextView collectionKorean;
        TextView collectionEnglish;
    }
}
