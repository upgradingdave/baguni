package net.awesomekorean.podo.purchasePremium;


import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import net.awesomekorean.podo.MainLesson;
import net.awesomekorean.podo.MainReading;
import net.awesomekorean.podo.MainWriting;

public class PurchasePremiumViewPagerAdapter extends FragmentPagerAdapter {

    public PurchasePremiumViewPagerAdapter(FragmentManager fragmentManager) {
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
                return PurchasePremiumNoAds.newInstance();

            case 1 :
                return PurchasePremiumUnlock.newInstance();

            case 2 :
                return  PurchasePremiumUnlimit.newInstance();

            default :
                return null;
        }
    }

}

