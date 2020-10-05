package net.awesomekorean.podo.lesson;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Color;
import android.media.AudioManager;
import android.os.Bundle;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.android.flexbox.FlexboxLayout;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import net.awesomekorean.podo.ConfirmQuit;
import net.awesomekorean.podo.LoadingPage;
import net.awesomekorean.podo.MediaPlayerManager;
import net.awesomekorean.podo.PlaySoundPool;
import net.awesomekorean.podo.R;
import net.awesomekorean.podo.lesson.intermediateLessons.I_Lesson;
import net.awesomekorean.podo.lesson.intermediateLessons.I_Lesson00;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class IntermediateFrame extends AppCompatActivity implements View.OnClickListener {

    FirebaseStorage storage = FirebaseStorage.getInstance();

    ImageView btnClose;
    TextView title;
    RecyclerView recyclerView;
    ConstraintLayout layoutAnswer;
    TextView tvAnswer;
    ImageView btnCancel;
    FlexboxLayout flexboxLayout;

    I_Lesson lesson;

    ArrayList<IntermediateItems> list;
    IntermediateAdapter adapter;
    
    public int dialogCount;

    Map<Integer, byte[]> audiosDialog;
    public int dialogLength;
    MediaPlayerManager mediaPlayerManager;

    String[] sentenceSplit;
    Button btnSelector;
    String correctAnswer;
    ArrayList<Button> clickedBtns;
    PlaySoundPool playSoundPool;

    ConstraintLayout layoutCompleted;
    ProgressBar progress;
    ProgressBar progressAudio;
    ImageView btnPrevious;
    ImageView btnPlay;
    ImageView btnPause;
    ImageView btnNext;
    Button btnPlayAgain;
    Button btnFinish;

    public LinearLayout collectResult;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intermediate_frame);

        setVolumeControlStream(AudioManager.STREAM_MUSIC);

        btnClose = findViewById(R.id.btnClose);
        title = findViewById(R.id.title);
        recyclerView = findViewById(R.id.recyclerView);
        layoutAnswer = findViewById(R.id.layoutAnswer);
        tvAnswer = findViewById(R.id.tvAnswer);
        btnCancel = findViewById(R.id.btnCancel);
        flexboxLayout = findViewById(R.id.flexboxLayout);
        layoutCompleted = findViewById(R.id.layoutCompleted);
        progress = findViewById(R.id.progress);
        progressAudio = findViewById(R.id.progressAudio);
        btnPrevious = findViewById(R.id.btnPrevious);
        btnPlay = findViewById(R.id.btnPlay);
        btnPause = findViewById(R.id.btnPause);
        btnNext = findViewById(R.id.btnNext);
        btnPlayAgain = findViewById(R.id.btnPlayAgain);
        btnFinish = findViewById(R.id.btnFinish);
        collectResult = findViewById(R.id.collectResult);
        btnClose.setOnClickListener(this);
        btnCancel.setOnClickListener(this);
        btnPrevious.setOnClickListener(this);
        btnPlay.setOnClickListener(this);
        btnPause.setOnClickListener(this);
        btnNext.setOnClickListener(this);
        btnPlayAgain.setOnClickListener(this);
        btnFinish.setOnClickListener(this);

        mediaPlayerManager = MediaPlayerManager.getInstance();
        playSoundPool = new PlaySoundPool(this);
        clickedBtns = new ArrayList<>();

        layoutAnswer.setVisibility(View.GONE);
        layoutCompleted.setVisibility(View.GONE);

        Intent intent = new Intent(getApplicationContext(), LoadingPage.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
        startActivity(intent);
        lesson = new I_Lesson00();
        title.setText(lesson.getLessonTitle());

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        int size = lesson.getDialog().length;
        progressAudio.setMax(size);
        progress.setMax(size);

        dialogCount = 0;
        getAudios();
    }


    private void answered(int sound, int outline, int textColor) {
        playSoundPool.playSoundLesson(sound);
        tvAnswer.setBackground(ContextCompat.getDrawable(this, outline));
        tvAnswer.setTextColor(textColor);
    }


    // 답변버튼 만들기
    public void makeAnswerBtns() {
        sentenceSplit = correctAnswer.split(" ");
        RandomArray.randomArrayString(sentenceSplit);

        for(int i=0; i<sentenceSplit.length; i++) {
            btnSelector = new Button(this);

            DisplayMetrics dm = getApplicationContext().getResources().getDisplayMetrics();
            int width = dm.widthPixels;

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            params.leftMargin = width / 100;
            params.rightMargin = width / 100;
            params.bottomMargin = width * 2 / 100;

            btnSelector.setLayoutParams(params);
            btnSelector.setBackground(ContextCompat.getDrawable(this, R.drawable.ripple_custom));
            btnSelector.setText(sentenceSplit[i]);
            btnSelector.setOnClickListener(this);
            flexboxLayout.addView(btnSelector);
        }
        layoutAnswer.setVisibility(View.VISIBLE);
    }


    // 정답입력 버튼 초기화
    public void initAnswer() {
        tvAnswer.setTextColor(Color.BLACK);
        clickedBtns.clear();
        tvAnswer.setText(getResources().getString(R.string.SELECT_ANSWER));
        tvAnswer.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.bg_transparent));
    }


    // 리사이클러뷰에 대화 넣기
    public void addDialog() {
        layoutAnswer.setVisibility(View.GONE);
        flexboxLayout.removeAllViews();
        initAnswer();

        IntermediateItems item = new IntermediateItems();
        if(dialogCount < lesson.getDialog().length) {
            item.setDialog(lesson.getDialog()[dialogCount]);
            item.setDialogEng(lesson.getDialogEng()[dialogCount]);
            if (dialogCount % 2 == 0) {
                item.setPeopleImage(lesson.getPeopleImage()[0]);
            } else {
                item.setPeopleImage(lesson.getPeopleImage()[1]);
            }
            list.add(item);
            adapter.notifyDataSetChanged();

            if (dialogCount % 2 == 0) {
                playAudio(0);

            } else {
                layoutAnswer.setVisibility(View.VISIBLE);
                correctAnswer = list.get(dialogCount).getDialog();
                makeAnswerBtns();
            }

        } else if (dialogCount == lesson.getDialog().length) {
            adapter.isFinish = true;
            adapter.notifyDataSetChanged();
            layoutAnswer.setVisibility(View.GONE);
            layoutCompleted.setVisibility(View.VISIBLE);
        }
        recyclerView.smoothScrollToPosition(list.size()-1);
    }


    // 대화오디오 재생하기
    public void playAudio(int playMode) {
        if(dialogCount < audiosDialog.size()) {
            mediaPlayerManager.stopMediaPlayer();
            mediaPlayerManager.playIntermediateAudio(audiosDialog.get(dialogCount), this, playMode);
            if (playMode == 2) {
                progressAudio.setProgress(dialogCount + 1);
            }

        // 오디오 끝! 버튼 초기화
        } else {
            dialogCount = 0;
            progressAudio.setProgress(0);
            btnPlay.setVisibility(View.VISIBLE);
            btnPause.setVisibility(View.GONE);
        }
    }


    // 저장소에서 대화오디오 가져오기
    private void getAudios() {
        audiosDialog = new HashMap<>();
        dialogLength = lesson.getDialog().length;
        final String lessonId = lesson.getLessonId().toLowerCase();
        String[] dialogAudio = new String[dialogLength];
        String folder = "intermediate/" + lessonId;

        for(int i=0; i<dialogLength; i++) {
            final Integer audioIndexDialog = i;
            dialogAudio[i] = lessonId + "_" + i + ".mp3";
            StorageReference storageRef = storage.getReference().child(folder).child(dialogAudio[i]);
            final long ONE_MEGABYTE = 1024 * 1024;
            storageRef.getBytes(ONE_MEGABYTE).addOnSuccessListener(new OnSuccessListener<byte[]>() {
                @Override
                public void onSuccess(byte[] bytes) {
                    System.out.println("오디오를 로드했습니다.");
                    audiosDialog.put(audioIndexDialog, bytes);
                    if(audiosDialog.size() == dialogLength) {
                        LoadingPage loadingPage = (LoadingPage)LoadingPage.activity;
                        loadingPage.finish();

                        list = new ArrayList<>();
                        adapter = new IntermediateAdapter(getApplicationContext(), list, lessonId, audiosDialog);
                        recyclerView.setAdapter(adapter);

                        addDialog();
                    }
                }
            });
        }
    }


    // 오디오 재생/정지 버튼 설정
    public void setAudioButtons(int play, int pause) {
        btnPlay.setVisibility(play);
        btnPause.setVisibility(pause);
        if(play == View.GONE) {
            btnPrevious.setImageResource(R.drawable.play_previous_active);
            btnNext.setImageResource(R.drawable.play_next_active);
            btnPrevious.setClickable(true);
            btnNext.setClickable(true);

        } else {
            btnPrevious.setImageResource(R.drawable.play_previous);
            btnNext.setImageResource(R.drawable.play_next);
            btnPrevious.setClickable(false);
            btnNext.setClickable(false);
        }
    }


    private void stopAudio() {
        setAudioButtons(View.VISIBLE, View.GONE);
        MediaPlayerManager.getInstance().stopMediaPlayer();
        progressAudio.setProgress(0);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.btnClose :
                Intent intent = new Intent(this, ConfirmQuit.class);
                intent.putExtra(getResources().getString(R.string.PROGRESS), 0);
                intent.putExtra(getResources().getString(R.string.LESSON_ID), lesson.getLessonId());
                startActivityForResult(intent, 200);
                break;

            case R.id.btnCancel :
                for(int i=0; i<clickedBtns.size(); i++) {
                    clickedBtns.get(i).setVisibility(View.VISIBLE);
                }
                initAnswer();
                break;

            case R.id.btnPrevious :
                if(btnPlay.getVisibility()==View.GONE) {
                    if (dialogCount > 0) {
                        dialogCount--;
                        playAudio(2);

                    } else {
                        stopAudio();
                    }
                }
                break;

            case R.id.btnPlay :
                setAudioButtons(View.GONE, View.VISIBLE);
                dialogCount = 0;
                playAudio(2);
                break;

            case R.id.btnPause :
                stopAudio();
                break;

            case R.id.btnNext :
                if(btnPlay.getVisibility()==View.GONE) {
                    if (dialogCount < audiosDialog.size() - 1) {
                        dialogCount++;
                        playAudio(2);

                    } else {
                        stopAudio();
                    }
                }
                break;

            case R.id.btnFinish :
                break;

            case R.id.btnPlayAgain :
                list.clear();
                adapter.isFinish = false;
                adapter.notifyDataSetChanged();
                dialogCount = 0;
                layoutCompleted.setVisibility(View.GONE);
                addDialog();
                break;


            // 정답입력 버튼
            default:
                Button selectedBtn = (Button) v;
                if(clickedBtns.size() == 0) {
                    tvAnswer.setText("");
                } else {
                    tvAnswer.append(" ");
                }
                clickedBtns.add(selectedBtn);
                selectedBtn.setVisibility(View.INVISIBLE);
                String selectedBtnText = selectedBtn.getText().toString();
                tvAnswer.append(selectedBtnText);

                if(clickedBtns.size() == sentenceSplit.length) {

                    if(tvAnswer.getText().toString().equals(correctAnswer)) { // 정답
                        answered(0, R.drawable.bg_mint_10_stroke_mint, ContextCompat.getColor(getApplicationContext(), R.color.MINT));
                        progress.setProgress(dialogCount + 1);

                    } else {  // 오답
                        answered(1, R.drawable.bg_red_10_stroke_red, ContextCompat.getColor(getApplicationContext(), R.color.RED));
                    }

                    Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            if(tvAnswer.getText().toString().equals(correctAnswer)) { // 정답
                                playAudio(0);

                            } else {
                                for(int i=0; i<clickedBtns.size(); i++) {
                                    clickedBtns.get(i).setVisibility(View.VISIBLE);
                                }
                                initAnswer();
                            }

                        }
                    }, 2000);
                }
            break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode == RESULT_OK) {
            MediaPlayerManager mediaPlayerManager = MediaPlayerManager.getInstance();
            mediaPlayerManager.stopMediaPlayer();
            finish();
        }
    }
}
