package net.awesomekorean.podo;


import android.app.Activity;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import net.awesomekorean.podo.lesson.MainLesson;
import net.awesomekorean.podo.reading.MainReading;
import net.awesomekorean.podo.collection.MainCollection;
import net.awesomekorean.podo.qna.MainQna;
import net.awesomekorean.podo.writing.MainWriting;

import static net.awesomekorean.podo.MainActivity.btnCollection;
import static net.awesomekorean.podo.MainActivity.btnLesson;
import static net.awesomekorean.podo.MainActivity.btnReading;
import static net.awesomekorean.podo.MainActivity.btnWriting;

public class ViewPagerAdapter extends FragmentPagerAdapter {

    MainActivity activity;

    public ViewPagerAdapter(FragmentManager fragmentManager, MainActivity activity) {
        super(fragmentManager);
        this.activity = activity;
    }

    @Override
    public int getCount() {
        return 5;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {

            case 0 :
                activity.setMainBtns(btnLesson, R.drawable.lesson_active, R.string.LESSON);
                return MainLesson.newInstance();

            case 1 :
                activity.setMainBtns(btnReading, R.drawable.reading_active, R.string.READING);
                return MainReading.newInstance();

            case 2 :
                activity.setMainBtns(btnWriting, R.drawable.writting_active, R.string.WRITING);
                return  MainWriting.newInstance();

            case 3 :
                activity.setMainBtns(btnCollection, R.drawable.collection_active, R.string.COLLECTION);
                return MainCollection.newInstance();

            case 4 :
                return MainQna.newInstance();

            default :
                return null;
        }
    }

}

