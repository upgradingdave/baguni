package net.awesomekorean.podo.purchasePremium;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import net.awesomekorean.podo.R;

public class PurchasePremiumUnlock extends Fragment {

    View view;

    public static PurchasePremiumUnlock newInstance() {
        return new PurchasePremiumUnlock();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.activity_purchase_premium_unlock, container, false);

        return view;
    }
}
