package net.awesomekorean.podo.lesson.lessonNumber;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import net.awesomekorean.podo.R;
import net.awesomekorean.podo.lesson.lessonNumber.numbers.NumberPractice;

public class LessonNumberMenuAdapter extends BaseAdapter {

    private NumberPractice[] numbers;

    private NumberPractice number;

    private Context context;

    private ViewHolder holder;

    private LayoutInflater inflater;


    public LessonNumberMenuAdapter(Context context, NumberPractice[] numbers) {

        super();

        this.context = context;

        this.numbers = numbers;

        this.inflater = LayoutInflater.from(context);
    }


    public class ViewHolder {

        private ImageView numberImage;

        private TextView numberTitle;

        private ProgressBar numberProgress;

        private TextView numberProgressText;

        private ImageView numberComplete;
    }


    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        View view = convertView;

        if(view == null) {

            holder = new ViewHolder();

            view = inflater.inflate(R.layout.activity_lesson_number_menu_item, parent, false);

            holder.numberImage = view.findViewById(R.id.numberImage);

            holder.numberTitle = view.findViewById(R.id.numberTitle);

            holder.numberProgress = view.findViewById(R.id.numberProgress);

            holder.numberProgressText = view.findViewById(R.id.numberProgressText);

            holder.numberComplete = view.findViewById(R.id.numberComplete);

            view.setTag(holder);

        } else {

            holder = (ViewHolder) view.getTag();
        }

        number = numbers[position];

        holder.numberImage.setImageResource(number.getPracticeImage());

        holder.numberTitle.setText(number.getPracticeTitle());

        int progress = number.getPracticeProgress();

        if(progress == 100) {

            setNumberStatus(holder, View.GONE, View.GONE, View.VISIBLE);

        } else {

            setNumberStatus(holder, View.VISIBLE, View.VISIBLE, View.GONE);

            holder.numberProgress.setProgress(number.getPracticeProgress());

            holder.numberProgressText.setText(number.getPracticeProgress() + "%");
        }

        return view;
    }


    private void setNumberStatus(ViewHolder holder, int progress, int progressText, int complete) {

        holder.numberProgress.setVisibility(progress);

        holder.numberProgressText.setVisibility(progressText);

        holder.numberComplete.setVisibility(complete);
    }


    @Override
    public Object getItem(int position) {
        return numbers[position];
    }


    @Override
    public long getItemId(int position) {
        return position;
    }


    @Override
    public int getCount() {
        return numbers.length;
    }
}
