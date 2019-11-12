package net.awesomekorean.baguni.lesson;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import net.awesomekorean.baguni.R;

import java.util.ArrayList;

public class LessonAdapter extends BaseAdapter {

    private Context context;
    public static ArrayList<LessonItems> list;

    public LessonAdapter(Context context, ArrayList<LessonItems> list) {

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
            view = inflater.inflate(R.layout.main_lesson_listview_item, viewGroup, false);
            holder = new ViewHolder();
            holder.title = view.findViewById(R.id.title);
            holder.subTitle = view.findViewById(R.id.subTitle);
            holder.lessonImage = view.findViewById(R.id.lessonImage);
            holder.textSpecial = view.findViewById(R.id.textSpecial);
            holder.iconLock = view.findViewById(R.id.iconLock);
            holder.layoutCompleted = view.findViewById(R.id.layoutCompleted);
            view.setTag(holder);

        } else {
            holder = (ViewHolder) view.getTag();
        }

        LessonItems items = list.get(i);

        holder.title.setText(items.getTitle());
        holder.subTitle.setText(items.getSubTitle());
        holder.lessonImage.setImageResource(items.getLessonImage());
        if(items.getIsSpecial()) { holder.textSpecial.setVisibility(View.VISIBLE);
        } else{holder.textSpecial.setVisibility(View.INVISIBLE);}
        if(items.getIsLock()) { holder.iconLock.setVisibility(View.VISIBLE);
        } else{holder.iconLock.setVisibility(View.INVISIBLE);}
        if(items.getIsCompleted()) { holder.layoutCompleted.setVisibility(View.VISIBLE);
        } else{holder.layoutCompleted.setVisibility(View.INVISIBLE);}

        return view;
    }


    // 뷰홀더란? 뷰들을 홀더에 꼽아놓듯이 보관하는 객체. 리스트뷰의 성능을 높이기 위해 사용
    static class ViewHolder {
        TextView title;
        TextView subTitle;
        ImageView lessonImage;
        TextView textSpecial;
        ImageView iconLock;
        LinearLayout layoutCompleted;
    }
}
