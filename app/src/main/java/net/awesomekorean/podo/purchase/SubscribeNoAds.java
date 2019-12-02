package net.awesomekorean.podo.purchase;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import net.awesomekorean.podo.R;

public class SubscribeNoAds extends Fragment {

    View view;

    public static SubscribeNoAds newInstance() {
        return new SubscribeNoAds();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.activity_subscribe_noads, container, false);

        return view;
    }
}
