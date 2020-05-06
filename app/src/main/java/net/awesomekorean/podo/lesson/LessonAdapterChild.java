package net.awesomekorean.podo.lesson;

import android.content.Context;
import android.content.Intent;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.crashlytics.FirebaseCrashlytics;

import net.awesomekorean.podo.R;
import net.awesomekorean.podo.UnlockActivity;
import net.awesomekorean.podo.lesson.lessonHangul.LessonHangul;
import net.awesomekorean.podo.lesson.lessonHangul.LessonHangulAssembly;
import net.awesomekorean.podo.lesson.lessonNumber.LessonNumber;
import net.awesomekorean.podo.lesson.lessonNumber.LessonNumberMenu;
import net.awesomekorean.podo.lesson.lessons.LessonItem;

import java.io.Serializable;

public class LessonAdapterChild extends RecyclerView.Adapter<LessonAdapterChild.HorizontalViewHolder> {

    private Context context;

    private LessonItem[] lessonItems;

    private LessonItem lessonItem;

    // 화면밀도 구하기
    private DisplayMetrics displayMetrics;

    private int pxWidth;

    private int pxHeight;

    private Intent intent;


    public LessonAdapterChild(Context context, LessonItem[] lessonItems) {

        this.context = context;

        this.lessonItems = lessonItems;

        this.displayMetrics = context.getResources().getDisplayMetrics();

        this.pxWidth = (int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 150, displayMetrics);

        this.pxHeight = (int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 170, displayMetrics);
    }


    public class HorizontalViewHolder extends RecyclerView.ViewHolder {

        private TextView lessonTitle;

        private TextView lessonSubTitle;

        private ImageView lessonComplete;

        private ImageView lessonImage;

        private ProgressBar lessonProgress;

        private ColorMatrixColorFilter filter;


        public HorizontalViewHolder(View view) {

            super(view);

            lessonTitle = view.findViewById(R.id.lessonTitle);

            lessonSubTitle = view.findViewById(R.id.lessonSubTitle);

            lessonComplete = view.findViewById(R.id.lessonComplete);

            lessonImage = view.findViewById(R.id.lessonImage);

            lessonProgress = view.findViewById(R.id.lessonProgress);

            ColorMatrix matrix = new ColorMatrix();

            matrix.setSaturation(0);

            filter = new ColorMatrixColorFilter(matrix);

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    int position = getAdapterPosition();

                    lessonItem = lessonItems[position];

                    if(position != RecyclerView.NO_POSITION) {

                        if(!lessonItem.getIsLock()) {

                            if(lessonItem.getIsSpecial()) {

                                String lessonId = lessonItem.getLessonId();

                                switch (lessonId) {

                                    case "H_consonant" :
                                        startLearningHangul(context.getString(R.string.CONSONANT));
                                        break;

                                    case "H_vowel" :
                                        startLearningHangul(context.getString(R.string.VOWEL));
                                        break;

                                    case "H_batchim" :
                                        startLearningHangul(context.getString(R.string.BATCHIM));
                                        break;

                                    case "H_assembly" :
                                        startLearningHangul(context.getString(R.string.ASSEMBLY));
                                        break;

                                    case "N_sino" :
                                        startLearningNumber(context.getString(R.string.SINO));
                                        break;

                                    case "N_native" :
                                        startLearningNumber(context.getString(R.string.NATIVE));
                                        break;

                                    case "N_practice" :
                                        startLearningNumber(context.getString(R.string.PRACTICE));
                                        break;

                                    default :
                                        intent = new Intent(context, LessonSpecialFrame.class);

                                        intent.putExtra(context.getResources().getString(R.string.LESSON), (Serializable) lessonItem);

                                        context.startActivity(intent);
                                        break;
                                }

                            } else {

                                intent = new Intent(context, LessonFrame.class);

                                intent.putExtra(context.getResources().getString(R.string.LESSON), (Serializable) lessonItem);

                                context.startActivity(intent);
                            }

                        // 포인트 사용 확인창 띄우기
                        } else {

                            intent = new Intent(context, UnlockActivity.class);

                            intent.putExtra(context.getResources().getString(R.string.EXTRA_ID), context.getResources().getString(R.string.SPECIAL_LESSON));

                            intent.putExtra(context.getResources().getString(R.string.LESSON_ID), lessonItem.getLessonId());

                            intent.putExtra(context.getResources().getString(R.string.LESSON), (Serializable) lessonItem);

                            context.startActivity(intent);
                        }
                    }
                }


                // 숫자 레슨 시작하기
                private void startLearningNumber(String number) {

                    if(number.equals(context.getString(R.string.PRACTICE))) {

                        intent = new Intent(context, LessonNumberMenu.class);

                    } else {

                        intent = new Intent(context, LessonNumber.class);
                    }

                    intent.putExtra(context.getString(R.string.EXTRA_ID), number);

                    context.startActivity(intent);
                }


                // 한글 레슨 시작하기
                private void startLearningHangul(String hangul) {

                    if(hangul.equals(context.getString(R.string.ASSEMBLY))) {

                        intent = new Intent(context, LessonHangulAssembly.class);

                    } else {

                        intent = new Intent(context, LessonHangul.class);

                        intent.putExtra(context.getResources().getString(R.string.CONVOWBAT), hangul);

                        intent.putExtra(context.getResources().getString(R.string.LESSON), (Serializable) lessonItem);
                    }

                    context.startActivity(intent);
                }
            });
        }
    }


    @NonNull
    @Override
    public HorizontalViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.main_lesson_child_item, null);

        ViewGroup.LayoutParams params = new RecyclerView.LayoutParams(pxWidth, pxHeight);

        view.setLayoutParams(params);

        return new LessonAdapterChild.HorizontalViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull HorizontalViewHolder holder, int position) {

        LessonItem lessonItem = lessonItems[position];

        holder.lessonTitle.setText(lessonItem.getLessonTitle());

        holder.lessonSubTitle.setText(lessonItem.getLessonSubTitle());

        holder.lessonImage.setImageResource(lessonItem.getLessonImage());

        if(lessonItem.getIsLock()) {

            setLessonStatus(holder, true, View.VISIBLE, View.GONE);

        } else if(lessonItem.getLessonProgress() == 100) {

            setLessonStatus(holder, false, View.VISIBLE, View.GONE);

        } else if(lessonItem.getLessonSubTitle().equals("quiz")) {

            setLessonStatus(holder, false, View.GONE, View.GONE);

        } else {

            holder.lessonProgress.setProgress(lessonItem.getLessonProgress());

            setLessonStatus(holder, false, View.GONE, View.VISIBLE);
        }
    }


    // Lock, complete, progress 이미지 세팅하기
    private void setLessonStatus(HorizontalViewHolder holder, boolean isLock, int imageView, int progressBar) {

        if(isLock) {

            holder.lessonComplete.setImageResource(R.drawable.lock);

            holder.lessonImage.setColorFilter(holder.filter);

        } else {

            holder.lessonComplete.setImageResource(R.drawable.complete);
        }

        holder.lessonComplete.setVisibility(imageView);

        holder.lessonProgress.setVisibility(progressBar);
    }


    @Override
    public int getItemCount() {

        return lessonItems.length;
    }
}
