package net.awesomekorean.podo.lesson;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import net.awesomekorean.podo.R;
import net.awesomekorean.podo.VocabularyQuiz;
import net.awesomekorean.podo.lesson.lessons.LessonItem;

import java.util.Arrays;

public class LessonAdapterGroup extends BaseExpandableListAdapter {

    private Context context;

    private String[] groupTitle;

    private String[] groupSubTitle;

    private int[] groupProcess;

    private LessonItem[][] childList;

    private LayoutInflater inflater;

    private ViewHolder holder;


    public LessonAdapterGroup(Context context, String[] groupTitle, String[] groupSubTitle, int[] groupProcess, LessonItem[][] childList) {

        super();

        this.context = context;

        this.inflater = LayoutInflater.from(context);

        this.groupTitle = groupTitle;

        this.groupSubTitle = groupSubTitle;

        this.groupProcess = groupProcess;

        this.childList = childList;
    }


    public class ViewHolder {

        private LinearLayout groupView;

        private TextView lessonCategoryTitle;

        private TextView lessonNumber;

        private ProgressBar lessonProgress;

        private TextView lessonProgressPercent;

        private ImageView lessonComplete;

        private ImageView lessonArrow;

        private RecyclerView lessonRecyclerView;
    }


    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {

        View view = convertView;

        if(view == null) {

            holder = new ViewHolder();

            view = inflater.inflate(R.layout.main_lesson_group, parent, false);

            holder.groupView = view.findViewById(R.id.groupView);

            holder.lessonCategoryTitle = view.findViewById(R.id.lessonCategoryTitle);

            holder.lessonNumber = view.findViewById(R.id.lessonNumber);

            holder.lessonProgress = view.findViewById(R.id.lessonProgress);

            holder.lessonProgressPercent = view.findViewById(R.id.lessonProgressPercent);

            holder.lessonComplete = view.findViewById(R.id.lessonComplete);

            holder.lessonArrow = view.findViewById(R.id.lessonArrow);

            view.setTag(holder);

        } else {

            holder = (ViewHolder) view.getTag();
        }


        if (isExpanded) {

            holder.groupView.setBackground(ContextCompat.getDrawable(context, R.drawable.bg_purple_10_transparent));

            holder.lessonArrow.setImageResource(R.drawable.arrow_down_purple_full);

        } else {

            holder.groupView.setBackground(ContextCompat.getDrawable(context, R.drawable.bg_white_10));

            holder.lessonArrow.setImageResource(R.drawable.arrow_right_grey_full);
        }


        holder.lessonCategoryTitle.setText(groupTitle[groupPosition]);

        holder.lessonNumber.setText(groupSubTitle[groupPosition]);

        holder.lessonCategoryTitle.setTextColor(ContextCompat.getColor(context, R.color.BLACK));

        holder.lessonNumber.setTextColor(ContextCompat.getColor(context, R.color.GREY_DARK));


        // 단어 테스트 타이틀
        if (groupTitle[groupPosition].equals("Quiz")) {

            holder.lessonCategoryTitle.setText("Vocabulary Quiz");

            holder.lessonCategoryTitle.setTextColor(ContextCompat.getColor(context, R.color.PURPLE));

            holder.lessonNumber.setTextColor(ContextCompat.getColor(context, R.color.PURPLE));

            holder.lessonProgress.setVisibility(View.GONE);

            holder.lessonProgressPercent.setVisibility(View.GONE);

            holder.lessonComplete.setVisibility(View.GONE);

            holder.lessonArrow.setVisibility(View.GONE);


        } else if (groupProcess[groupPosition] == 100) {

            holder.lessonProgress.setVisibility(View.GONE);

            holder.lessonProgressPercent.setVisibility(View.GONE);

            holder.lessonComplete.setVisibility(View.VISIBLE);

        } else {

            holder.lessonProgress.setVisibility(View.VISIBLE);

            holder.lessonProgressPercent.setVisibility(View.VISIBLE);

            holder.lessonComplete.setVisibility(View.GONE);

            holder.lessonProgress.setProgress(groupProcess[groupPosition]);

            holder.lessonProgressPercent.setText(groupProcess[groupPosition] + "%");


            if(groupPosition == groupTitle.length - 1) {

                holder.lessonCategoryTitle.setTextColor(ContextCompat.getColor(context, R.color.GREY_DARK));

                holder.lessonProgress.setVisibility(View.GONE);

                holder.lessonProgressPercent.setVisibility(View.GONE);

                holder.lessonComplete.setVisibility(View.GONE);

                holder.lessonArrow.setVisibility(View.GONE);
            }
        }

        return view;
    }


    @Override
    public String getGroup(int groupPosition) {

        return groupTitle[groupPosition];
    }


    @Override
    public int getGroupCount() {

        return groupTitle.length;
    }


    @Override
    public long getGroupId(int groupPosition) {

        return groupPosition;
    }


    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {

        View view = convertView;

        if(view == null) {

            holder = new ViewHolder();

            view = inflater.inflate(R.layout.main_lesson_child, null);

            holder.lessonRecyclerView = view.findViewById(R.id.lessonRecyclerView);

            view.setTag(holder);

        } else {

            holder = (ViewHolder) view.getTag();
        }

        LessonAdapterChild adapter = new LessonAdapterChild(context, childList[groupPosition]);

        holder.lessonRecyclerView.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));

        holder.lessonRecyclerView.setHasFixedSize(true);

        holder.lessonRecyclerView.setAdapter(adapter);

        return view;
    }


    @Override
    public Object getChild(int groupPosition, int childPosition) {

        return childList[groupPosition];
    }


    @Override
    public int getChildrenCount(int groupPosition) {

        return 1;
    }


    @Override
    public long getChildId(int groupPosition, int childPosition) {

        return childPosition;
    }


    @Override
    public boolean hasStableIds() {

        return true;
    }


    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {

        return true;
    }
}
