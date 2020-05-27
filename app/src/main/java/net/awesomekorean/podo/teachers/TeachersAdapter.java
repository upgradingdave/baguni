package net.awesomekorean.podo.teachers;

import android.content.ContentResolver;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import net.awesomekorean.podo.R;

import java.io.IOException;
import java.util.ArrayList;

public class TeachersAdapter extends RecyclerView.Adapter<TeachersAdapter.ViewHolder> {

    FirebaseStorage storage = FirebaseStorage.getInstance();


    // 아이템 클릭 이벤트를 MainLesson 에서 처리하기 위한 인터페이스
    public interface OnItemClickListener {
        void onItemClick(View v, int pos);
    }

    private OnItemClickListener listener = null;

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }


    Context context;

    private ArrayList<TeachersItems> list;

    public TeachersAdapter(Context context, ArrayList<TeachersItems> list) {
        this.context = context;
        this.list = list;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        LinearLayout itemLayout;
        ImageView teacherImage;
        TextView available;
        TextView vacation;
        ImageView btnAudio;
        String pathAudio;
        TextView teacherName;
        TextView teacherTag;
        ImageView teacherCheck;

        MediaPlayer mediaPlayer = new MediaPlayer();

        ViewHolder(View itemView) {
            super(itemView);

            itemLayout = itemView.findViewById(R.id.itemLayout);
            teacherImage = itemView.findViewById(R.id.teacherImage);
            available = itemView.findViewById(R.id.available);
            vacation = itemView.findViewById(R.id.vacation);
            btnAudio = itemView.findViewById(R.id.btnAudio);
            teacherName = itemView.findViewById(R.id.teacherName);
            teacherTag = itemView.findViewById(R.id.teacherTag);
            teacherCheck = itemView.findViewById(R.id.teacherCheck);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    if(position != RecyclerView.NO_POSITION) {
                        TeachersItems item = list.get(position);

                        // 아이템 클릭 이벤트
                        if(listener != null) {

                            for(int i=0; i<list.size(); i++) {
                                list.get(i).setIsChecked(false);
                            }

                            item.setIsChecked(true);
                            notifyDataSetChanged();
                            listener.onItemClick(view, position);
                        }
                    }
               }
            });


            // 오디오 버튼 클릭
            btnAudio.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(!mediaPlayer.isPlaying()) {
                        StorageReference reference = storage.getReference().child(pathAudio);
                        reference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri uri) {
                                try {
                                    mediaPlayer = new MediaPlayer();
                                    mediaPlayer.setDataSource(uri.toString());
                                    mediaPlayer.prepare();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                                mediaPlayer.start();
                            }
                        });
                    } else {
                        mediaPlayer.stop();
                    }
                }
            });
        }
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.activity_teachers_item, parent, false);
        TeachersAdapter.ViewHolder holder = new TeachersAdapter.ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        TeachersItems item = list.get(position);

        if(item.getStatus() == 1) {
            holder.available.setVisibility(View.VISIBLE);
            holder.vacation.setVisibility(View.GONE);
        } else if(item.getStatus() == 2) {
            holder.available.setVisibility(View.GONE);
            holder.vacation.setVisibility(View.VISIBLE);
        }

        if(item.getIsChecked()) {
            holder.itemLayout.setBackground(ContextCompat.getDrawable(context, R.drawable.bg_white_10_stroke_purple));
            holder.teacherCheck.setVisibility(View.VISIBLE);
        } else  {
            holder.itemLayout.setBackground(ContextCompat.getDrawable(context, R.drawable.bg_white_10));
            holder.teacherCheck.setVisibility(View.GONE);
        }

        holder.teacherName.setText(item.getName());
        holder.teacherTag.setText(item.getTag());
        holder.pathAudio = item.getAudio();

        Picasso.with(context).load(R.drawable.image_danny).into(holder.teacherImage);

/*
        // 선생님 이미지 가져오기
        StorageReference reference = storage.getReference().child(item.getImage());
        reference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Picasso.with(context).load(uri).into(holder.teacherImage);
                System.out.println("선생님 이미지 로드를 성공 했습니다: " + uri);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                System.out.println("선생님 이미지 로드를 실패 했습니다: "+e);
            }
        });

 */
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
