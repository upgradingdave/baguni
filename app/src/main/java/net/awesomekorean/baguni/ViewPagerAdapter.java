package net.awesomekorean.baguni;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class ViewPagerAdapter extends FragmentPagerAdapter {

    public ViewPagerAdapter(FragmentManager fragmentManager) {
        super(fragmentManager);
    }

    @Override
    public int getCount() {
        return 4;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {

            case 0 :
                return MainLesson.newInstance();

            case 1 :
                return MainReading.newInstance();

            case 2 :
                return  MainWriting.newInstance();

            case 3 :
                return MainCollection.newInstance();

            default :
                return null;
        }
    }

}

