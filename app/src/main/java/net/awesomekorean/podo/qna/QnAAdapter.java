package net.awesomekorean.podo.qna;

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
import net.awesomekorean.podo.writing.MainWriting;
import net.awesomekorean.podo.writing.WritingEntity;

import java.util.ArrayList;

public class QnAAdapter extends BaseAdapter {

    private Context context;
    public ArrayList<QnAEntity> list;

    public static String guid; // 삭제 버튼 눌렀을 때 해당 아이템의 guid

    public QnAAdapter(Context context, ArrayList<QnAEntity> list) {

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
            view = inflater.inflate(R.layout.main_qna_item, viewGroup, false);
            holder = new ViewHolder();
            holder.dateQuestion = view.findViewById(R.id.dateQuestion);
            holder.layoutReplied = view.findViewById(R.id.layoutReplied);
            holder.layoutReviewing = view.findViewById(R.id.layoutReviewing);
            holder.layoutReturned = view.findViewById(R.id.layoutReturned);
            holder.tvQuestion = view.findViewById(R.id.tvQuestion);
            holder.btnDelete = view.findViewById(R.id.btnDelete);
            view.setTag(holder);

        } else {
            holder = (ViewHolder) view.getTag();
        }

        final QnAEntity items = list.get(i);

        holder.dateQuestion.setText(UnixTimeStamp.unixTimeFormat(items.getDateQuestion()));
        holder.tvQuestion.setText(items.getQuestion());

        if(items.getStatus() == 0) {
            setVisibility(holder, View.GONE, View.GONE, View.GONE);
        } else if(items.getStatus() == 1) {
            setVisibility(holder, View.VISIBLE, View.GONE, View.GONE);
        } else if(items.getStatus() == 2) {
            setVisibility(holder, View.GONE, View.VISIBLE, View.GONE);
        } else if(items.getStatus() == 99) {
            setVisibility(holder, View.GONE, View.GONE, View.VISIBLE);
        }

        holder.btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                guid = items.getGuid();
                MainQnA.layoutDelete.setVisibility(View.VISIBLE);
            }
        });
        return view;
    }

    private void setVisibility(ViewHolder holder, int reviewing, int replied, int returned) {
        holder.layoutReviewing.setVisibility(reviewing);
        holder.layoutReplied.setVisibility(replied);
        holder.layoutReturned.setVisibility(returned);
    }


    // 뷰홀더란? 뷰들을 홀더에 꼽아놓듯이 보관하는 객체. 리스트뷰의 성능을 높이기 위해 사용
    static class ViewHolder {
        TextView dateQuestion;
        LinearLayout layoutReplied;
        LinearLayout layoutReviewing;
        LinearLayout layoutReturned;
        TextView tvQuestion;
        ImageView btnDelete;
    }
}