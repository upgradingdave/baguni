package net.awesomekorean.podo.lesson.lessonNumber;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import net.awesomekorean.podo.R;
import net.awesomekorean.podo.lesson.lessonNumber.numbers.NumberPractice;
import net.awesomekorean.podo.lesson.lessons.LessonItem;

public class LessonNumberMenuAdapter extends BaseAdapter {

    private LessonItem[] numbers;
    private LessonItem number;
    private ViewHolder holder;
    private LayoutInflater inflater;


    public LessonNumberMenuAdapter(Context context, LessonItem[] numbers) {
        super();
        this.numbers = numbers;
        this.inflater = LayoutInflater.from(context);
    }


    public class ViewHolder {
        private ImageView numberImage;
        private TextView numberTitle;
    }


    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View view = convertView;

        if(view == null) {
            holder = new ViewHolder();
            view = inflater.inflate(R.layout.activity_lesson_number_menu_item, parent, false);
            holder.numberImage = view.findViewById(R.id.numberImage);
            holder.numberTitle = view.findViewById(R.id.numberTitle);
            view.setTag(holder);

        } else {
            holder = (ViewHolder) view.getTag();
        }

        number = numbers[position];
        //holder.numberImage.setImageResource(number.getLessonImage());
        holder.numberTitle.setText(number.getLessonTitle());

        return view;
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
