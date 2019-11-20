package net.awesomekorean.podo;


import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

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

            case 4 :
                return null;

            default :
                return null;
        }
    }

}

