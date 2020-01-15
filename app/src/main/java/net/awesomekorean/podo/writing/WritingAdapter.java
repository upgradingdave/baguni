package net.awesomekorean.podo.writing;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import net.awesomekorean.podo.R;
import net.awesomekorean.podo.UnixTimeStamp;

import java.util.ArrayList;

public class WritingAdapter extends BaseAdapter {

    private Context context;
    public ArrayList<WritingEntity> list;

    public static String guid; // 삭제 버튼 눌렀을 때 해당 아이템의 guid

    public WritingAdapter(Context context, ArrayList<WritingEntity> list) {

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
            view = inflater.inflate(R.layout.main_writing_item, viewGroup, false);
            holder = new ViewHolder();
            holder.writingDate = view.findViewById(R.id.writingDate);
            holder.letters = view.findViewById(R.id.letters);
            holder.article = view.findViewById(R.id.article);
            holder.corrected = view.findViewById(R.id.corrected);
            holder.reviewing = view.findViewById(R.id.reviewing);
            holder.rejected = view.findViewById(R.id.rejected);
            holder.btnDelete = view.findViewById(R.id.btnDelete);
            view.setTag(holder);

        } else {
            holder = (ViewHolder) view.getTag();
        }

        final WritingEntity items = list.get(i);

        holder.writingDate.setText(UnixTimeStamp.unixTimeFormat(items.getWritingDate()));
        holder.letters.setText(items.getLetters());
        holder.article.setText(items.getContents());

        if(items.getStatus() == 0) {
            setVisibility(holder, View.GONE, View.GONE, View.GONE);
        } else if(items.getStatus() == 1) {
            setVisibility(holder, View.VISIBLE, View.GONE, View.GONE);
        } else if(items.getStatus() == 2) {
            setVisibility(holder, View.GONE, View.VISIBLE, View.GONE);
        } else if(items.getStatus() == 3) {
            setVisibility(holder, View.GONE, View.GONE, View.VISIBLE);
        }

        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                guid = items.getGuid();
                MainWriting.msgDelete.setVisibility(View.VISIBLE);
            }
        });
        return view;
    }

    private void setVisibility(ViewHolder holder, int reviewing, int corrected, int rejected) {
        holder.reviewing.setVisibility(reviewing);
        holder.corrected.setVisibility(corrected);
        holder.rejected.setVisibility(rejected);
    }


    // 뷰홀더란? 뷰들을 홀더에 꼽아놓듯이 보관하는 객체. 리스트뷰의 성능을 높이기 위해 사용
    static class ViewHolder {
        TextView writingDate;
        TextView letters;
        TextView article;
        LinearLayout corrected;
        LinearLayout reviewing;
        LinearLayout rejected;
        ImageView btnDelete;
    }
}