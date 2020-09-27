package net.awesomekorean.podo.lesson;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import net.awesomekorean.podo.GetRandomPoint;
import net.awesomekorean.podo.R;
import net.awesomekorean.podo.UnlockActivity;
import net.awesomekorean.podo.lesson.lessonHangul.DpToPx;
import net.awesomekorean.podo.lesson.lessonHangul.LessonHangul;
import net.awesomekorean.podo.lesson.lessonHangul.LessonHangulAssembly;
import net.awesomekorean.podo.lesson.lessonNumber.LessonNumber;
import net.awesomekorean.podo.lesson.lessonNumber.LessonNumberMenu;
import net.awesomekorean.podo.lesson.lessons.LessonItem;

import java.io.Serializable;

public class LessonAdapter extends RecyclerView.Adapter<LessonAdapter.ViewHolder> {

    private Context context;
    private LessonItem[] list;
    private Intent intent;

    public LessonAdapter(Context context, LessonItem[] list) {
        this.context = context;
        this.list = list;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ConstraintLayout layout;
        ConstraintLayout layoutItem;
        ConstraintLayout layoutItemSpecial;
        ImageView currentItem;
        LinearLayout layoutGoal;
        TextView tvItemNo;
        TextView tvItemTitle;
        TextView tvItemSubTitle;
        TextView tvItemSpecialTitle;
        TextView tvWeekCount;
        ImageView lineSpecial;
        ImageView lineLeftTop;
        ImageView lineLeftBottom;
        ImageView lineRightTop;
        ImageView lineRightBottom;

        ViewHolder(final View itemView) {
            super(itemView);

            layout = itemView.findViewById(R.id.layout);
            layoutItem = itemView.findViewById(R.id.layoutItem);
            layoutItemSpecial = itemView.findViewById(R.id.layoutItemSpecial);
            currentItem = itemView.findViewById(R.id.currentItem);
            layoutGoal = itemView.findViewById(R.id.layoutGoal);
            tvItemNo = itemView.findViewById(R.id.tvItemNo);
            tvItemTitle = itemView.findViewById(R.id.tvItemTitle);
            tvItemSubTitle = itemView.findViewById(R.id.tvItemSubTitle);
            tvItemSpecialTitle = itemView.findViewById(R.id.tvItemSpecialTitle);
            tvWeekCount = itemView.findViewById(R.id.tvWeekCount);
            lineSpecial = itemView.findViewById(R.id.lineSpecial);
            lineLeftTop = itemView.findViewById(R.id.lineLeftTop);
            lineLeftBottom = itemView.findViewById(R.id.lineLeftBottom);
            lineRightTop = itemView.findViewById(R.id.lineRightTop);
            lineRightBottom = itemView.findViewById(R.id.lineRightBottom);


            // 레슨/리뷰/리워드 클릭 이벤트
            layoutItem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    LessonItem item = list[position];

                    if(item.getIsActive()) {
                        if (!item.getIsLocked()) {
                            String lessonId = item.getLessonId();
                            switch (lessonId) {

                                case "H_consonant":
                                    startLearningHangul(context.getString(R.string.CONSONANT));
                                    break;

                                case "H_vowel":
                                    startLearningHangul(context.getString(R.string.VOWEL));
                                    break;

                                case "H_batchim":
                                    startLearningHangul(context.getString(R.string.BATCHIM));
                                    break;

                                case "H_assembly":
                                    startLearningHangul(context.getString(R.string.ASSEMBLY));
                                    break;

                                case "N_sino":
                                    startLearningNumber(context.getString(R.string.SINO));
                                    break;

                                case "N_native":
                                    startLearningNumber(context.getString(R.string.NATIVE));
                                    break;

                                case "N_practice":
                                    startLearningNumber(context.getString(R.string.PRACTICE));
                                    break;

                                default:
                                    String type = item.getLessonId().split("_")[0];
                                    if (type.equals("LR")) {
                                        intent = new Intent(context, LessonReviewFrame.class);
                                    } else if (type.equals("RW")) {
                                        intent = new Intent(context, GetRandomPoint.class);
                                    } else if (type.equals("IL")) {
                                        //todo: 중급레슨 프레임
                                    } else if (type.equals("AL")) {
                                        //todo: 고급레슨 프레임
                                    } else {
                                        intent = new Intent(context, LessonFrame.class);
                                    }
                                    intent.putExtra(context.getResources().getString(R.string.LESSON), (Serializable) item);
                                    context.startActivity(intent);
                                    break;
                            }

                        // 포인트 사용 확인창 띄우기
                        } else {
                            intent = new Intent(context, UnlockActivity.class);
                            intent.putExtra(context.getResources().getString(R.string.EXTRA_ID), context.getResources().getString(R.string.LESSON));
                            intent.putExtra(context.getResources().getString(R.string.LESSON_ID), item.getLessonId());
                            context.startActivity(intent);
                        }

                    // 활성화되지 않은 레슨을 클릭했을 때
                    } else {
                        Toast.makeText(context, context.getString(R.string.PLEASE_COMPLETE_PREVIOUS_LESSON), Toast.LENGTH_LONG).show();
                    }
                }
            });


            // 스페셜레슨 클릭 이벤트
            layoutItemSpecial.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    LessonItem item = list[position];

                    if(item.getSLesson().getIsActive()) {
                        if (!item.getSLesson().getIsLocked()) {
                            intent = new Intent(context, LessonSpecialFrame.class);
                            intent.putExtra(context.getResources().getString(R.string.LESSON), (Serializable) item.getSLesson());
                            context.startActivity(intent);

                        // 포인트 사용 확인창 띄우기
                        } else {
                            intent = new Intent(context, UnlockActivity.class);
                            intent.putExtra(context.getResources().getString(R.string.EXTRA_ID), context.getResources().getString(R.string.SPECIAL_LESSON));
                            intent.putExtra(context.getResources().getString(R.string.LESSON_ID), item.getSLesson().getLessonId());

                            if(item.getLessonId() != "H_assembly") {
                                intent.putExtra(context.getResources().getString(R.string.LESSON), (Serializable) item.getSLesson());
                            }

                            context.startActivity(intent);
                        }

                    // 활성화되지 않은 스페셜레슨을 클릭했을 때
                    } else {
                        Toast.makeText(context, context.getString(R.string.PLEASE_COMPLETE_PREVIOUS_LESSON), Toast.LENGTH_LONG).show();
                    }
                }
            });
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
        }
        context.startActivity(intent);
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View view = inflater.inflate(R.layout.main_lesson_item, parent, false);
        LessonAdapter.ViewHolder holder = new LessonAdapter.ViewHolder(view);
        return holder;
    }


    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        LessonItem item = list[position];
        holder.tvItemNo.setText(String.valueOf(position + 1));
        if(!item.getIsLocked()) {
            holder.tvItemTitle.setText(item.getLessonTitle());
        } else {
            holder.tvItemTitle.setText("");
        }
        holder.tvItemSubTitle.setText(item.getLessonSubTitle());

        if(item.getSLesson() != null) {
            ConstraintSet set = new ConstraintSet();
            set.clone(holder.layout);
            int gap20 = DpToPx.getDpToPx(context.getResources(), 20);

            // 홀수번째 스페셜레슨 오른쪽에
            if(position%2 == 0) {
                set.connect(R.id.layoutItemSpecial, ConstraintSet.START, R.id.layoutItem, ConstraintSet.END);
                set.connect(R.id.lineSpecial, ConstraintSet.START, R.id.layoutItem, ConstraintSet.END);
                set.connect(R.id.lineSpecial, ConstraintSet.END, R.id.layoutItemSpecial, ConstraintSet.START);
                set.setMargin(R.id.layoutItemSpecial, ConstraintSet.START, gap20);

            // 홀수번째 스페셜레슨 왼쪽에
            } else {
                set.connect(R.id.layoutItemSpecial, ConstraintSet.END, R.id.layoutItem, ConstraintSet.START);
                set.connect(R.id.lineSpecial, ConstraintSet.START, R.id.layoutItemSpecial, ConstraintSet.END);
                set.connect(R.id.lineSpecial, ConstraintSet.END, R.id.layoutItem, ConstraintSet.START);
                set.setMargin(R.id.layoutItemSpecial, ConstraintSet.END, gap20);
            }
            set.applyTo(holder.layout);

            holder.lineSpecial.setVisibility(View.VISIBLE);
            holder.layoutItemSpecial.setVisibility(View.VISIBLE);
            if(!item.getSLesson().getIsLocked()) {
                holder.tvItemSpecialTitle.setText(item.getSLesson().getLessonTitle());
            } else {
                holder.tvItemSpecialTitle.setText("");
            }
            setItemBackground(holder, item.getSLesson());

        } else {
            holder.lineSpecial.setVisibility(View.GONE);
            holder.layoutItemSpecial.setVisibility(View.GONE);
            holder.tvItemSpecialTitle.setVisibility(View.GONE);
        }

        setLines(holder, position);
        setItemBackground(holder, item);
    }


    // 아이템 라인 세팅
    private void setLines(ViewHolder holder, int position) {
        int leftTop = View.GONE;
        int rightTop = View.GONE;
        int leftBottom = View.GONE;
        int rightBottom = View.GONE;

        if(position == 0) {     // 첫 번째 아이템
            rightBottom = View.VISIBLE;
        } else if(position == list.length - 1 && position%2 == 1) {   // 마지막 + 홀수 아이템
            rightTop = View.VISIBLE;
        } else if(position == list.length - 1 && position%2 == 0) {   // 마지막 + 짝수 아이템
            leftTop = View.VISIBLE;
        } else if(position%2 == 1) {    // 홀수 아이템
            rightTop = View.VISIBLE;
            leftBottom = View.VISIBLE;
        } else if(position%2 == 0) {    // 짝수 아이템
            leftTop = View.VISIBLE;
            rightBottom = View.VISIBLE;
        }

        holder.lineLeftTop.setVisibility(leftTop);
        holder.lineRightTop.setVisibility(rightTop);
        holder.lineLeftBottom.setVisibility(leftBottom);
        holder.lineRightBottom.setVisibility(rightBottom);
    }


    // 아이템 백그라운드 세팅
    private void setItemBackground(ViewHolder holder, LessonItem item) {
        String type = item.getLessonId().split("_")[0];
        Drawable drawable = null;

        if(type.equals("RW")) {     // 리워드
            holder.tvItemTitle.setText(R.string.REWARDS);
            holder.tvItemTitle.setMaxLines(1);
            if(item.getIsActive()) {
                drawable = ContextCompat.getDrawable(context, R.drawable.circle_reward_active_beginner);

            } else {
                drawable = ContextCompat.getDrawable(context, R.drawable.circle_reward_inactive_beginner);
            }

        } else {      // 레슨 & 리뷰 & 스페셜레슨
            if(type.equals("LR")) {     // 리뷰
                holder.tvItemTitle.setText(R.string.REVIEW);
                holder.tvItemTitle.setMaxLines(1);
            }
            if(item.getIsActive()) {
                drawable = ContextCompat.getDrawable(context, R.drawable.circle_active_beginner);

            } else if(item.getIsLocked()) {
                drawable = ContextCompat.getDrawable(context, R.drawable.circle_locked_beginner);

            } else {
                drawable = ContextCompat.getDrawable(context, R.drawable.circle_inactive_beginner);
            }
        }

        if(type.equals("SL")) {
            holder.layoutItemSpecial.setBackground(drawable);
        } else {
            holder.layoutItem.setBackground(drawable);
        }

        if(item.getIsCurrent()) {
            holder.currentItem.setVisibility(View.VISIBLE);
        } else {
            holder.currentItem.setVisibility(View.GONE);
        }
    }


    @Override
    public int getItemCount() {
        return list.length;
    }
}
