package net.awesomekorean.podo.lesson;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ToggleButton;

import androidx.recyclerview.widget.RecyclerView;

import net.awesomekorean.podo.CollectResult;
import net.awesomekorean.podo.ConfirmQuit;
import net.awesomekorean.podo.DownloadAudio;
import net.awesomekorean.podo.MediaPlayerManager;
import net.awesomekorean.podo.R;
import net.awesomekorean.podo.collection.CollectionRepository;

import java.util.ArrayList;
import java.util.Map;

public class IntermediateAdapter extends RecyclerView.Adapter<IntermediateAdapter.ViewHolder> {

    private ArrayList<IntermediateItems> list;
    public boolean isFinish;
    private Map<Integer, byte[]> audiosDialog;
    private String lessonId;
    private Context context;

    public IntermediateAdapter(Context context, ArrayList<IntermediateItems> list, String lessonId, Map<Integer, byte[]> audiosDialog) {
        this.context = context;
        this.list = list;
        this.isFinish = false;
        this.lessonId = lessonId;
        this.audiosDialog = audiosDialog;
    }


    int position;


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView tvDialog;
        LinearLayout layoutOption;
        ImageView btnAudio;
        ImageView btnCollect;
        ImageView btnTranslate;
        ImageView peopleImage;

        ViewHolder(final View itemView) {
            super(itemView);

            tvDialog = itemView.findViewById(R.id.tvDialog);
            layoutOption = itemView.findViewById(R.id.layoutOption);
            btnAudio = itemView.findViewById(R.id.btnAudio);
            btnCollect = itemView.findViewById(R.id.btnCollect);
            btnTranslate = itemView.findViewById(R.id.btnTranslate);
            peopleImage = itemView.findViewById(R.id.peopleImage);
            btnAudio.setOnClickListener(this);
            btnCollect.setOnClickListener(this);
            btnTranslate.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            switch (v.getId()) {
                case R.id.btnAudio:
                    MediaPlayerManager.getInstance().playIntermediateAudio(audiosDialog.get(position), null, 1);
                    break;

                case R.id.btnCollect:
                    String front = list.get(position).getDialog();
                    String back = list.get(position).getDialogEng();
                    String folder = "intermediate/" + lessonId;
                    String audio = lessonId + "_" + position + ".mp3";

                    Intent intent = new Intent(context, CollectResult.class);
                    intent.putExtra(context.getResources().getString(R.string.COLLECT_FRONT), front);
                    intent.putExtra(context.getResources().getString(R.string.COLLECT_BACK), back);
                    intent.putExtra(context.getResources().getString(R.string.COLLECT_FOLDER), folder);
                    intent.putExtra(context.getResources().getString(R.string.COLLECT_AUDIO), audio);
                    context.startActivity(intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
                    break;

                case R.id.btnTranslate :
                    ImageView imageView = (ImageView) v;
                    TextView textView = (TextView) ((LinearLayout) v.getParent().getParent().getParent()).getChildAt(0);
                    if(textView.getText().equals(list.get(position).getDialog())) {
                        textView.setText(list.get(position).getDialogEng());
                        imageView.setImageResource(R.drawable.toggle_en);
                    } else {
                        textView.setText(list.get(position).getDialog());
                        imageView.setImageResource(R.drawable.toggle_kr);
                    }
                    break;
            }
        }
    }

    @Override
    public int getItemViewType(int position) {

        if(position%2 == 0) { // a타입
            return 0;
        } else {  // b타입
            return 1;
        }
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View view;

        if(viewType == 0) {
            view = inflater.inflate(R.layout.activity_intermediate_dialog_a, parent, false);
        } else {
            view = inflater.inflate(R.layout.activity_intermediate_dialog_b, parent, false);
        }

        IntermediateAdapter.ViewHolder holder = new IntermediateAdapter.ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        this.position = position;
        IntermediateItems items = list.get(position);

        // 현재 리스트의 가장 마지막 아이템
        if(position == list.size() - 1 && isFinish == false) {
            items.setIsExpanded(false);

            if(list.size()%2 == 0) {
                holder.tvDialog.setText("...");
            } else {
                holder.tvDialog.setText(items.getDialog());
            }

        } else {
            holder.tvDialog.setText(items.getDialog());
            items.setIsExpanded(true);
        }

        holder.peopleImage.setImageResource(items.getPeopleImage());
        if(items.getIsExpanded()) {
            holder.layoutOption.setVisibility(View.VISIBLE);
        } else {
            holder.layoutOption.setVisibility(View.GONE);
        }
    }


    @Override
    public int getItemCount() {
        return list.size();
    }
}
