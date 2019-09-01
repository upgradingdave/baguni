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

    public static final String TEXT_ON = "On";
    public static final String TEXT_OFF = "Off";
    public static final int VISIBLE_ON = 0;
    public static final int VISIBLE_OFF = 4;

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

        // 맨 처음에 리스트뷰를 생성해줌
        if(view==null) {

            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.main_collection_listview_item, viewGroup, false);
            holder = new ViewHolder();
            holder.checkBox = view.findViewById(R.id.checkBox);
            holder.btnRecord = view.findViewById(R.id.btnRecord);
            holder.collectionFront = view.findViewById(R.id.collectionFront);
            holder.collectionBack = view.findViewById(R.id.collectionBack);
            view.setTag(holder);

        } else {
            holder = (ViewHolder) view.getTag();
        }

        CollectionItems items = list.get(i);

        holder.collectionFront.setText(items.getCollectionFront());
        holder.collectionBack.setText(items.getCollectionBack());
        holder.checkBox.setChecked(items.getChecked());
        holder.checkBox.setVisibility(items.getVisible());
        holder.checkBox.setTag(R.integer.btnplusview, view); // 해당 checkbox 가 있는 뷰를 Tag Key 1번으로 할당
        holder.checkBox.setTag(i);
        holder.checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // 체크박스 클릭 했을 때 동작
                View tempView = (View) holder.checkBox.getTag(R.integer.btnplusview); // 해당 checkbox 가 포함된 뷰를 가져옴
                TextView collectionFront = tempView.findViewById(R.id.collectionFront);
                TextView collectionBack = tempView.findViewById(R.id.collectionBack);
                Integer position = (Integer) holder.checkBox.getTag();
                System.out.println("Checkbox "+ position + "Clicked. CK is " + collectionFront + "CE is "+ collectionBack);

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

    // 뷰홀더란? 뷰들을 홀더에 꼽아놓듯이 보관하는 객체. 리스트뷰의 성능을 높이기 위해 사용
    static class ViewHolder {
        CheckBox checkBox;
        ImageButton btnRecord;
        TextView collectionFront;
        TextView collectionBack;
    }

    public void checkAll(boolean b) {
        CollectionItems items;
        if(b == true) {
            for(int i=0; i<getCount(); i++) {
                items = list.get(i);
                items.setChecked(true);
            }
        } else if(b == false) {
            for(int i=0; i<getCount(); i++) {
                items = list.get(i);
                items.setChecked(false);
            }
        }
    }


    // 리스트뷰를 길게 눌렀을 때 체크박스 on/off
    public void longClickOnOff(String onOff) {

        CollectionItems items;

        if(onOff == TEXT_ON) {
            for (int i = 0; i < getCount(); i++) {
                items = list.get(i);
                items.setVisible(VISIBLE_ON);
            }
        } else if(onOff == TEXT_OFF) {

            for (int i = 0; i < getCount(); i++) {

                items = list.get(i);
                items.setChecked(false);
                items.setVisible(VISIBLE_OFF);
            }
        }
    }
}
