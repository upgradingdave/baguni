package net.awesomekorean.baguni.writing;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import net.awesomekorean.baguni.R;

import java.util.ArrayList;

public class WritingListViewAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<WritingItems> list;

    public WritingListViewAdapter(Context context, ArrayList<WritingItems> list) {

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
            view = inflater.inflate(R.layout.main_writing_listview_item, viewGroup, false);
            holder = new ViewHolder();
            holder.date = view.findViewById(R.id.date);
            holder.letters = view.findViewById(R.id.letters);
            holder.article = view.findViewById(R.id.article);
            holder.isCorrected = view.findViewById(R.id.isCorrected);
            holder.btnDelete = view.findViewById(R.id.btnDelete);
            view.setTag(holder);

        } else {
            holder = (ViewHolder) view.getTag();
        }

        WritingItems items = list.get(i);

        holder.date.setText(items.getDate());
        holder.letters.setText(items.getLetters());
        holder.article.setText(items.getArticle());
        holder.isCorrected.setVisibility(items.getIsCorrected());
        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // 삭제버튼 클릭 했을 때 동작
            }
        });

        return view;
    }

    // 뷰홀더란? 뷰들을 홀더에 꼽아놓듯이 보관하는 객체. 리스트뷰의 성능을 높이기 위해 사용
    static class ViewHolder {
        TextView date;
        TextView letters;
        TextView article;
        ImageView isCorrected;
        Button btnDelete;
    }
}
