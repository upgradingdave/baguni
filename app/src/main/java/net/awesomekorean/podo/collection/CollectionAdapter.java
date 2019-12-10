package net.awesomekorean.podo.collection;

import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import net.awesomekorean.podo.R;

import java.util.ArrayList;
import java.util.List;

public class CollectionAdapter extends BaseAdapter {

    private Context context;
    public static List<CollectionEntity> list;

    MediaPlayer mp;

    public CollectionAdapter(Context context, List<CollectionEntity> list) {

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
            view = inflater.inflate(R.layout.main_collection_item, viewGroup, false);
            holder = new ViewHolder();
            holder.checkBox = view.findViewById(R.id.checkBox);
            holder.btnAudio = view.findViewById(R.id.btnAudio);
            holder.collectionFront = view.findViewById(R.id.collectionFront);
            holder.collectionBack = view.findViewById(R.id.collectionBack);
            view.setTag(holder);

        } else {
            holder = (ViewHolder) view.getTag();
        }

        final CollectionEntity items = list.get(i);

        holder.collectionFront.setText(items.getFront());
        holder.collectionBack.setText(items.getBack());
        holder.checkBox.setChecked(items.getIsChecked());
        if(items.getIsVisible()) { holder.checkBox.setVisibility(View.VISIBLE);
        } else{holder.checkBox.setVisibility(View.INVISIBLE);}
        if(items.getAudio() == null) { holder.btnAudio.setVisibility(View.INVISIBLE);
        } else{holder.btnAudio.setVisibility(View.VISIBLE);}

        holder.checkBox.setTag(i);
        holder.checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // 체크박스 클릭 했을 때 동작
                Integer position = (Integer) holder.checkBox.getTag();

                CollectionEntity items = list.get(position);

                if(items.getIsChecked()) {
                    items.setIsChecked(false);
                    MainCollection.isChecked--;
                    MainCollection.selectAll.setChecked(false);
                    if(MainCollection.isChecked == 0) {
                        MainCollection.btnEnabled(false);
                    }
                } else {
                    items.setIsChecked(true);
                    MainCollection.btnEnabled(true);
                    MainCollection.isChecked++;
                }
            }
        });

        holder.btnAudio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("Audio button clicked");
                String audioFile = items.getAudio();
                playAudio(audioFile);
            }
        });
        return view;
    }

    // 오디오 재생 메소드
    public void playAudio(String audioFile) {
        System.out.println("PATH:"+ audioFile);
        String uriPath = "android.resource://" + context.getPackageName() + "/raw/" + audioFile;
        Uri uri = Uri.parse(uriPath);
        mp = MediaPlayer.create(context, uri);
        try {
            mp.start();
        }
        catch (Exception e) {}
    }



    // 뷰홀더란? 뷰들을 홀더에 꼽아놓듯이 보관하는 객체. 리스트뷰의 성능을 높이기 위해 사용
    static class ViewHolder {
        CheckBox checkBox;
        ImageView btnAudio;
        TextView collectionFront;
        TextView collectionBack;
    }

    public void checkAll(boolean b) {
        CollectionEntity items;
        if(b == true) {
            for(int i=0; i<getCount(); i++) {
                items = list.get(i);
                items.setIsChecked(true);
            }
        } else if(b == false) {
            for(int i=0; i<getCount(); i++) {
                items = list.get(i);
                items.setIsChecked(false);
            }
        }
    }


    // 리스트뷰를 길게 눌렀을 때 체크박스 on/off
    public void longClickOnOff(String onOff) {

        CollectionEntity items;

        if(onOff.equals("on")) {
            for (int i = 0; i < getCount(); i++) {
                items = list.get(i);
                items.setIsVisible(true);
            }

        } else if(onOff.equals("off")) {
            for (int i = 0; i < getCount(); i++) {
                items = list.get(i);
                items.setIsChecked(false);
                items.setIsVisible(false);
            }
        }
    }
}
