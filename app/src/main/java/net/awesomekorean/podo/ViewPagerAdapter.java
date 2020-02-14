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

    MainActivity mainActivity;

    public ViewPagerAdapter(FragmentManager fragmentManager, MainActivity mainActivity) {
        super(fragmentManager);
        this.mainActivity = mainActivity;
    }

    @Override
    public int getCount() {
        return 4;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {

            case 0 :
                return MainLesson.newInstance(mainActivity);

            case 1 :
                return MainReading.newInstance(mainActivity);

            case 2 :
                return  MainWriting.newInstance(mainActivity);

            case 3 :
                return MainCollection.newInstance(mainActivity);
//
//            case 4 :
//                return MainQna.newInstance();

            default :
                return null;
        }
    }

}

