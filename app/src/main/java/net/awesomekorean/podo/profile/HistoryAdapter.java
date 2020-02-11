package net.awesomekorean.podo.profile;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import net.awesomekorean.podo.R;
import net.awesomekorean.podo.UnixTimeStamp;
import net.awesomekorean.podo.lesson.LessonAdapter;

import java.util.ArrayList;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.ViewHolder> {

    // 아이템 클릭 이벤트를 MainLesson 에서 처리하기 위한 인터페이스
    public interface OnItemClickListener {
        void onItemClick(View v, int pos);
    }
    private HistoryAdapter.OnItemClickListener listener = null;
    public void setOnItemClickListener(HistoryAdapter.OnItemClickListener listener) {
        this.listener = listener;
    }

    private ArrayList<Requests> list;

    public HistoryAdapter(ArrayList<Requests> list) {
        this.list = list;
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView requestId;
        TextView requestDate;
        TextView answerDate;
        LinearLayout reviewed;
        LinearLayout reviewing;
        LinearLayout rejected;
        TextView writing;
        TextView front;
        TextView back;
        ImageView audio;


        ViewHolder(View itemView) {
            super(itemView);

            requestId = itemView.findViewById(R.id.requestId);
            requestDate = itemView.findViewById(R.id.requestDate);
            answerDate = itemView.findViewById(R.id.answerDate);
            reviewed = itemView.findViewById(R.id.reviewed);
            reviewing = itemView.findViewById(R.id.reviewing);
            rejected = itemView.findViewById(R.id.rejected);
            writing = itemView.findViewById(R.id.writing);
            front = itemView.findViewById(R.id.front);
            back = itemView.findViewById(R.id.back);
            audio = itemView.findViewById(R.id.audio);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    if(position != RecyclerView.NO_POSITION) {
                        Requests item = list.get(position);

                        // 아이템 클릭 이벤트
                        if(listener != null) {
                            listener.onItemClick(view, position);
                        }
                    }
                }
            });
        }
    }

    @Override
    public int getItemViewType(int position) {

        if(list.get(position).getIsCorrection()) { // 쓰기 교정
            return 0;
        } else {  // 컬렉션 녹음
            return 1;
        }
    }


    @Override
    public HistoryAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View view;

        if(viewType == 0) {
            view = inflater.inflate(R.layout.activity_history_item_a, parent, false);
        } else {
            view = inflater.inflate(R.layout.activity_history_item_b, parent, false);
        }

        HistoryAdapter.ViewHolder holder = new HistoryAdapter.ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull HistoryAdapter.ViewHolder holder, int position) {
        Requests items = list.get(position);
        int viewType = holder.getItemViewType();

        if(viewType==0) {
            String guidFirst = items.getRequestId().substring(0,8);
            holder.requestId.setText("Request ID: " + guidFirst);
            holder.requestDate.setText(UnixTimeStamp.unixTimeFormat(items.getRequestDate()));
            if(items.getAnswerDate() != null) {
                holder.answerDate.setText(UnixTimeStamp.unixTimeFormat(items.getAnswerDate()));
            }
            if(items.getStatus() == 1) {
                setStatus(View.VISIBLE, View.GONE, View.GONE, holder);
            } else if(items.getStatus() == 2) {
                setStatus(View.GONE, View.VISIBLE, View.GONE, holder);
            } else if(items.getStatus() == 99) {
                setStatus(View.GONE, View.GONE, View.VISIBLE, holder);
            }
            if(items.getIsCorrection()) {
                holder.writing.setText(items.getWriting());
            } else{
                // 녹음요청내역 세팅하기
            }

        }
    }

    private void setStatus(int reviewing, int reviewed, int rejected, ViewHolder holder) {
        holder.reviewing.setVisibility(reviewing);
        holder.reviewed.setVisibility(reviewed);
        holder.rejected.setVisibility(rejected);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
