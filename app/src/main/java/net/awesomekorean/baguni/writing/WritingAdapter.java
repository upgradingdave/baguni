package net.awesomekorean.baguni.writing;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import net.awesomekorean.baguni.MainWriting;
import net.awesomekorean.baguni.R;

import java.util.ArrayList;

public class WritingAdapter extends BaseAdapter {

    private Context context;
    public ArrayList<WritingItems> list;

    public static int id; // 삭제 버튼 눌렀을 때 해당 아이템의 id

    public WritingAdapter(Context context, ArrayList<WritingItems> list) {

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
    public View getView(final int i, View view, ViewGroup viewGroup) {

        final ViewHolder holder;

        // 맨 처음에 리스트뷰를 생성해줌
        if(view==null) {

            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.main_writing_listview_item, viewGroup, false);
            holder = new ViewHolder();
            holder.firstDate = view.findViewById(R.id.firstDate);
            holder.lastDate = view.findViewById(R.id.lastDate);
            holder.letters = view.findViewById(R.id.letters);
            holder.article = view.findViewById(R.id.article);
            holder.isCorrected = view.findViewById(R.id.isCorrected);
            holder.btnDelete = view.findViewById(R.id.btnDelete);
            view.setTag(holder);

        } else {
            holder = (ViewHolder) view.getTag();
        }

        final WritingItems items = list.get(i);

        holder.firstDate.setText(items.getFirstDate());
        holder.lastDate.setText(items.getLastDate());
        holder.letters.setText(items.getLetters());
        holder.article.setText(items.getArticle());
        if(items.getIsCorrected()) {holder.isCorrected.setVisibility(View.VISIBLE);
        } else { holder.isCorrected.setVisibility(View.INVISIBLE);}
        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                id = items.getId();
                MainWriting.msgDelete.setVisibility(View.VISIBLE);
            }
        });
        return view;
    }

    // 뷰홀더란? 뷰들을 홀더에 꼽아놓듯이 보관하는 객체. 리스트뷰의 성능을 높이기 위해 사용
    static class ViewHolder {
        TextView firstDate;
        TextView lastDate;
        TextView letters;
        TextView article;
        LinearLayout isCorrected;
        ImageView btnDelete;
    }
}