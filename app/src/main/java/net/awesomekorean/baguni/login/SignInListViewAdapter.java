package net.awesomekorean.baguni.login;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import net.awesomekorean.baguni.R;

public class SignInListViewAdapter extends BaseAdapter {

    private String[] items = {"Korean", "English", "Chinese", "Japanese", "Thai"};

    @Override
    public int getCount() {
        return items.length;
    }

    @Override
    public Object getItem(int i) {
        return items[i];
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        final int position = i;
        final Context context = viewGroup.getContext();

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(R.layout.activity_sign_in_listview_item, viewGroup, false);

        TextView language = view.findViewById(R.id.textLanguage);

        language.setText(items[i]);

        return view;
    }
}
