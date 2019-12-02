package net.awesomekorean.podo.purchase;


import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class SubscribeViewPagerAdapter extends FragmentPagerAdapter {

    public SubscribeViewPagerAdapter(FragmentManager fragmentManager) {
        super(fragmentManager);
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {

            case 0 :
                return SubscribeNoAds.newInstance();

            case 1 :
                return SubscribeUnlock.newInstance();

            case 2 :
                return  SubscribeUnlimit.newInstance();

            default :
                return null;
        }
    }

}

