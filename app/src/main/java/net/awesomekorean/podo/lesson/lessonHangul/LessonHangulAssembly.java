package net.awesomekorean.podo.lesson.lessonHangul;

import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import net.awesomekorean.podo.MediaPlayerManager;
import net.awesomekorean.podo.R;
import net.awesomekorean.podo.SharedPreferencesInfo;
import net.awesomekorean.podo.UserInformation;
import net.awesomekorean.podo.lesson.lessons.LessonItem;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;

public class LessonHangulAssembly extends AppCompatActivity implements View.OnClickListener, LessonItem {

    private FirebaseStorage storage = FirebaseStorage.getInstance();
    private String lessonId = "H_assembly";
    private String lessonTitle = "assembly";
    private String lessonSubTitle = "";
    private int lessonImage = R.drawable.hangul_menu_assembly;
    private boolean isSpecial = true;
    private boolean isLock = true;
    private MediaPlayerManager mediaPlayerManager;
    private String url;
    private ProgressBar loading;
    private boolean isCompleted;

    TextView textViewIntro;
    TextView assemblyTextView;

    ImageView cvH;
    ImageView cvV;
    ImageView cvcH;
    ImageView cvcV;

    Button btnIntro;
    Button btnConsonant;
    Button btnVowel;
    Button btnBatchim;
    ImageView btnAudio;
    ImageView btnClose;
    ImageView btnBack;

    LinearLayout hangulBox;
    LinearLayout consonantBoxLayout1;
    LinearLayout consonantBoxLayout2;
    LinearLayout consonantBoxLayout3;
    LinearLayout vowelBoxLayout1;
    LinearLayout vowelBoxLayout2;
    LinearLayout vowelBoxLayout3;
    LinearLayout batchimBoxLayout1;
    LinearLayout batchimBoxLayout2;
    LinearLayout batchimBoxLayout3;
    ConstraintLayout layoutIntro;

    Boolean consonantSelected = false;
    Boolean vowelSelected = false;
    Boolean batchimSelected = false;

    String selectedConsonant = null;
    String selectedVowel = null;
    String selectedBatchim = null;

    Boolean consonantBtnClicked = false;
    Boolean vowelBtnClicked = false;
    Boolean batBtnClicked = false;

    String[] consonant = {"ㄱ", "ㄴ", "ㄷ", "ㄹ", "ㅁ", "ㅂ", "ㅅ", "ㅇ", "ㅈ", "ㅊ", "ㅋ", "ㅌ", "ㅍ", "ㅎ", "ㄲ", "ㄸ", "ㅃ", "ㅆ", "ㅉ"};
    String[] vowel = {};
    String[] batchim = {"ㄱ", "ㄴ", "ㄷ", "ㄹ", "ㅁ", "ㅂ", "ㅅ", "ㅇ", "ㅈ", "ㅊ", "ㅋ", "ㅌ", "ㅍ", "ㅎ"};
    String assemblyIntro = "We have finished learning every Hangul!\n" +
                    "Now let's make every character by yourself.\n" +
                    "We have 4 ways to make characters.\n" +
                    "There are 2 rules when making characters.\n" +
                    "1. The first Hangul should be a consonant.\n" +
                    "2. A consonant and vowel should be used alternately.\n" +
                    "Note)\n" +
                    "You'll see some words that have a double Batchim later.\n" +
                    "But don't worry.\n" +
                    "It sounds one of them and we will not learn about this yet.\n" +
                    "You can learn it easily when you start to learn words later.\n";

    public View.OnClickListener hangulBoxBtnSelected;

    Context context;

    String audioFile;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lesson_hangul_assembly);

        context = getApplicationContext();

        mediaPlayerManager = MediaPlayerManager.getInstance();

        hangulBox = findViewById(R.id.hangulBox);
        consonantBoxLayout1 = findViewById(R.id.consonantBox1);
        consonantBoxLayout2 = findViewById(R.id.consonantBox2);
        consonantBoxLayout3 = findViewById(R.id.consonantBox3);
        vowelBoxLayout1 = findViewById(R.id.vowelBox1);
        vowelBoxLayout2 = findViewById(R.id.vowelBox2);
        vowelBoxLayout3 = findViewById(R.id.vowelBox3);
        batchimBoxLayout1 = findViewById(R.id.batchimBox1);
        batchimBoxLayout2 = findViewById(R.id.batchimBox2);
        batchimBoxLayout3 = findViewById(R.id.batchimBox3);
        layoutIntro = findViewById(R.id.layoutIntro);
        assemblyTextView = findViewById(R.id.assemblyTextView);
        btnClose = findViewById(R.id.btnClose);
        btnBack = findViewById(R.id.btnBack);
        btnIntro = findViewById(R.id.btnIntro);
        btnAudio = findViewById(R.id.btnAudio);
        cvH = findViewById(R.id.cvH);
        cvV =findViewById(R.id.cvV);
        cvcH = findViewById(R.id.cvcH);
        cvcV = findViewById(R.id.cvcV);
        btnConsonant = findViewById(R.id.btnConsonant);
        btnVowel = findViewById(R.id.btnVowel);
        btnBatchim = findViewById(R.id.btnBatchim);
        loading = findViewById(R.id.loading);
        btnAudio.setOnClickListener(this);
        cvH.setOnClickListener(this);
        cvV.setOnClickListener(this);
        cvcH.setOnClickListener(this);
        cvcV.setOnClickListener(this);
        btnConsonant.setOnClickListener(this);
        btnVowel.setOnClickListener(this);
        btnBatchim.setOnClickListener(this);
        btnIntro.setOnClickListener(this);
        btnClose.setOnClickListener(this);
        btnBack.setOnClickListener(this);

        hangulBoxBtnSelected = new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                audioFile = null;
                Button selectedBtn = (Button) view;
                String selectedHangul = selectedBtn.getText().toString();

                if (consonantBtnClicked) {
                    displayHangul(selectedHangul, consonant);
                } else if (vowelBtnClicked) {
                    displayHangul(selectedHangul, vowel);
                } else if (batBtnClicked) {
                    displayHangul(selectedHangul, batchim);
                }

                if(consonantSelected == true && vowelSelected == true && batchimSelected == false) {
                    HangulUniCode hangul = new HangulUniCode(selectedConsonant, selectedVowel);
                    audioFile = hangul.getAudioFile();
                    playAudioAssy(audioFile);
                    assemblyTextView.setText(hangul.getAssembledHangul());
                } else if(consonantSelected == true && vowelSelected == true && batchimSelected == true) {
                    HangulUniCode hangul = new HangulUniCode(selectedConsonant, selectedVowel, selectedBatchim);
                    audioFile = hangul.getAudioFile();
                    playAudioAssy(audioFile);
                    assemblyTextView.setText(hangul.getAssembledHangul());
                } else {
                    assemblyTextView.setText(selectedHangul);
                }
            }
        };
        cvH.callOnClick();
    }


    public void playAudioAssy(String audioFile) {

        loading.setVisibility(VISIBLE);

        if(audioFile != null) {

            if (mediaPlayerManager != null) {
                StorageReference storageRef = storage.getReference().child("hangul/assy").child(audioFile);
                storageRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        url = uri.toString();
                        mediaPlayerManager.setMediaPlayer(false, url);
                        loading.setVisibility(GONE);

                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getApplicationContext(), getString(R.string.FAIL_LOAD_AUDIO), Toast.LENGTH_LONG).show();
                        loading.setVisibility(GONE);
                    }
                });
            }
        }
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.btnAudio :
                if(audioFile != null) {
                    mediaPlayerManager.playMediaPlayer(false);
                }
                break;

            case R.id.cvH :

                vowel = new String[] {"ㅏ", "ㅑ", "ㅓ", "ㅕ", "ㅣ", "ㅐ", "ㅒ"};

                initialization();

                setAssemblyBtns(cvH, R.drawable.assembly1_active);

                btnBatchim.setVisibility(GONE);

                makeHangulBox(consonant, vowel, batchim);

                break;

            case R.id.cvV :

                vowel = new String[] {"ㅗ", "ㅛ", "ㅜ", "ㅠ", "ㅡ", "ㅘ", "ㅙ", "ㅚ", "ㅝ", "ㅞ", "ㅟ", "ㅢ"};

                initialization();

                setAssemblyBtns(cvV, R.drawable.assembly2_active);

                btnBatchim.setVisibility(GONE);

                makeHangulBox(consonant, vowel, batchim);

                break;

            case R.id.cvcH :

                vowel = new String[] {"ㅏ", "ㅑ", "ㅓ", "ㅕ", "ㅣ", "ㅐ", "ㅒ"};

                initialization();

                setAssemblyBtns(cvcH, R.drawable.assembly3_active);

                btnBatchim.setVisibility(VISIBLE);

                makeHangulBox(consonant, vowel, batchim);

                break;

            case R.id.cvcV :

                vowel = new String[] {"ㅗ", "ㅛ", "ㅜ", "ㅠ", "ㅡ", "ㅘ", "ㅙ", "ㅚ", "ㅝ", "ㅞ", "ㅟ", "ㅢ"};

                initialization();

                setAssemblyBtns(cvcV, R.drawable.assembly4_active);

                btnBatchim.setVisibility(VISIBLE);

                makeHangulBox(consonant, vowel, batchim);

                break;

            case R.id.btnConsonant :

                conVowBtnClicked(true, false, false);

                hangulBoxVisible(VISIBLE, GONE, GONE);

                break;

            case R.id.btnVowel :

                conVowBtnClicked(false, true, false);

                hangulBoxVisible(GONE, VISIBLE, GONE);

                break;

            case R.id.btnBatchim :

                conVowBtnClicked(false, false, true);

                hangulBoxVisible(GONE, GONE, VISIBLE);

                break;

            case R.id.btnIntro :
                textViewIntro = findViewById(R.id.textViewIntro);
                textViewIntro.setText(assemblyIntro);
                textViewIntro.setMovementMethod(new ScrollingMovementMethod());
                layoutIntro.setVisibility(VISIBLE);
                break;

            case R.id.btnClose :
                layoutIntro.setVisibility(GONE);
                break;

            case R.id.btnBack :
                setLessonComplete();
                break;
        }
    }


    private void setLessonComplete() {
        // 레슨완료리스트에 업데이트
        UserInformation userInformation = SharedPreferencesInfo.getUserInfo(context);
        userInformation.updateCompleteList(context, "H_assembly", false);
        finish();
    }

    private void setAssemblyBtns(ImageView assembly, int active) {
        cvH.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.assembly1));
        cvV.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.assembly2));
        cvcH.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.assembly3));
        cvcV.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.assembly4));
        assembly.setBackground(ContextCompat.getDrawable(getApplicationContext(), active));
    }

    public void initialization() {
        assemblyTextView.setText("");
        btnConsonant.callOnClick();
        consonantSelected = false;
        vowelSelected = false;
        batchimSelected = false;
    }

    public void conVowBtnClicked(boolean con, boolean vow, boolean bat) {
        if(con==true) {
            setConVowBtns(btnConsonant);

        } else if(vow==true) {
            setConVowBtns(btnVowel);

        } else if(bat==true) {
            setConVowBtns(btnBatchim);
        }
        consonantBtnClicked = con;
        vowelBtnClicked = vow;
        batBtnClicked = bat;
    }

    private void setConVowBtns(Button conVow) {
        btnConsonant.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.bg_white_10));
        btnVowel.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.bg_white_10));
        btnBatchim.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.bg_white_10));
        btnConsonant.setTextColor(Color.BLACK);
        btnVowel.setTextColor(Color.BLACK);
        btnBatchim.setTextColor(Color.BLACK);

        conVow.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.bg_purple_10));
        conVow.setTextColor(Color.WHITE);
    }

    public void displayHangul(String selectedHangul, String[] conVow) {

        for(String string : conVow) {
            if (selectedHangul.equals(string)) {
                assemblyTextView.setText(selectedHangul);
            }
        }

        if (conVow == consonant) {
            selectedConsonant = selectedHangul;
            consonantSelected = true;

        } else if (conVow == vowel) {
            selectedVowel = selectedHangul;
            vowelSelected = true;

        } else if (conVow == batchim) {
            selectedBatchim = selectedHangul;
            batchimSelected = true;
        }
    }


    public void makeHangulBox(String[] consonant, String[] vowel, String[] batchim) {
        removeAllView();
        makeHangulBoxIterate(consonant, consonantBoxLayout1, consonantBoxLayout2, consonantBoxLayout3);
        makeHangulBoxIterate(vowel, vowelBoxLayout1, vowelBoxLayout2, vowelBoxLayout3);
        makeHangulBoxIterate(batchim, batchimBoxLayout1, batchimBoxLayout2, batchimBoxLayout3);
    }

    private void removeAllView() {
        consonantBoxLayout1.removeAllViews();
        consonantBoxLayout2.removeAllViews();
        consonantBoxLayout3.removeAllViews();
        vowelBoxLayout1.removeAllViews();
        vowelBoxLayout2.removeAllViews();
        vowelBoxLayout3.removeAllViews();
        batchimBoxLayout1.removeAllViews();
        batchimBoxLayout2.removeAllViews();
        batchimBoxLayout3.removeAllViews();
    }


    private void makeHangulBoxIterate(String[] hangul, LinearLayout hangulBoxLayout1, LinearLayout hangulBoxLayout2, LinearLayout hangulBoxLayout3) {
        int numberOfBox = 0;

        for(String addHangul : hangul) {
            Button hangulBoxBtn = new Button(this);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(0, DpToPx.getDpToPx(getResources(), 40));
            params.rightMargin = DpToPx.getDpToPx(getResources(),10);
            params.weight = 1;
            hangulBoxBtn.setLayoutParams(params);
            hangulBoxBtn.setBackground(ContextCompat.getDrawable(this, R.drawable.bg_white_10));
            hangulBoxBtn.setText(addHangul);
            hangulBoxBtn.setOnClickListener(hangulBoxBtnSelected);
            numberOfBox++;

            if (numberOfBox <= 7) {
                hangulBoxLayout1.addView(hangulBoxBtn);

            } else if (numberOfBox <= 14) {
                hangulBoxLayout2.addView(hangulBoxBtn);

            } else if (numberOfBox <= 21) {
                hangulBoxLayout3.addView(hangulBoxBtn);
            }
        }
    }


    public void hangulBoxVisible(int consonantBoxLayout, int vowelBoxLayout, int batchimBoxLayout) {

        consonantBoxLayout1.setVisibility(consonantBoxLayout);
        consonantBoxLayout2.setVisibility(consonantBoxLayout);
        consonantBoxLayout3.setVisibility(consonantBoxLayout);

        vowelBoxLayout1.setVisibility(vowelBoxLayout);
        vowelBoxLayout2.setVisibility(vowelBoxLayout);
        vowelBoxLayout3.setVisibility(vowelBoxLayout);

        batchimBoxLayout1.setVisibility(batchimBoxLayout);
        batchimBoxLayout2.setVisibility(batchimBoxLayout);
        batchimBoxLayout3.setVisibility(batchimBoxLayout);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();

        if(mediaPlayerManager != null) {
            mediaPlayerManager.releaseMediaPlayer();
        }
    }


    @Override
    public void onBackPressed() {
        setLessonComplete();
    }


    @Override
    public String getLessonId() {
        return lessonId;
    }

    @Override
    public String getLessonTitle() {
        return lessonTitle;
    }

    @Override
    public int getLessonImage() {
        return lessonImage;
    }

    @Override
    public boolean getIsSpecial() {
        return isSpecial;
    }

    @Override
    public boolean getIsLock() {
        return isLock;
    }

    @Override
    public void setIsLocked(boolean isLock) {
        this.isLock = isLock;
    }

    @Override
    public String getLessonSubTitle() {
        return lessonSubTitle;
    }

    @Override
    public boolean getIsCompleted() {
        return isCompleted;
    }

    @Override
    public void setIsCompleted(boolean b) {
        isCompleted = b;
    }
}




